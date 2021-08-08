package com.hyperelement.mvvmdemo.ui.fragment.city

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityEntity
import com.hyperelement.mvvmdemo.databinding.ItemCityBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.viewholder.SmartViewHolder

class FragmentCityVH(var parentView: ViewGroup) : SmartViewHolder<CityEntity>(
    parentView.inflate<ItemCityBinding>(R.layout.item_city).root
) {
    override fun bind(item: CityEntity) {
        val binding = DataBindingUtil.getBinding<ItemCityBinding>(itemView)
        binding?.dataItem = item
    }
}