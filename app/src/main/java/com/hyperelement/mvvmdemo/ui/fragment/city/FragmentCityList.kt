package com.hyperelement.mvvmdemo.ui.fragment.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityEntity
import com.hyperelement.mvvmdemo.databinding.FragmentCityBinding
import com.hyperelement.mvvmdemo.utilities.ErrorState
import com.hyperelement.mvvmdemo.utilities.MsgState
import com.hyperelement.mvvmdemo.utilities.SuccessState
import kotlinx.android.synthetic.main.fragment_city.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnCustomViewEventListener
import smartadapter.viewevent.listener.OnLongClickEventListener
import smartadapter.viewevent.model.ViewEvent
import timber.log.Timber

//fragment to display the list of cities
class FragmentCityList :
    BaseFragment<CityVM>(R.layout.fragment_city, CityVM::class) {

    private lateinit var adapter: SmartRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        adapter.clear()
        viewModel.loadCityFromDB()

        super.onResume()

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentCityBinding>()?.viewModel = viewModel

        viewModel.mState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is SuccessState -> {
                    Timber.d("SuccessState")
                    adapter.clear()
                    viewModel.loadCityFromDB()
                }
                is ErrorState -> {
                    Timber.d("ERROR STATE")
                }
                is MsgState -> {
                    Timber.d("MsgState")
                }
            }
        })

        adapter = SmartRecyclerAdapter.empty()
            .add(OnClickEventListener {
                val cityEntity = adapter?.getItem(it.position) as? CityEntity
                val action =
                    FragmentCityListDirections.actionFragmentCityListToFragmentWeather(cityEntity!!.name!!)
                findNavController().navigate(action)
            })
            .add(OnClickEventListener(FragmentCityVH::class, R.id.ivCancel) { event: ViewEvent.OnClick ->
                val cityEntity = adapter?.getItem(event.position) as? CityEntity
                cityEntity?.let { it1 -> viewModel.deleteCity(it1) }
                adapter.notifyDataSetChanged()
            })
            .map(CityEntity::class, FragmentCityVH::class)
            .into(rvCity)

        viewModel.cityList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addItems(it)
            }
        })

        fabSearch.setOnClickListener {
            val action =
                FragmentCityListDirections.actionFragmentCityListToFragmentCitySelection()
            findNavController().navigate(action)
        }
    }
}