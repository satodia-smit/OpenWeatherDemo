package com.hyperelement.mvvmdemo.ui.fragment.help

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.databinding.FragmentCityBinding
import com.hyperelement.mvvmdemo.databinding.FragmentHelpBinding
import kotlinx.android.synthetic.main.fragment_help.*
import smartadapter.SmartRecyclerAdapter


//Fragment to open the link in webview
class FragmentHelp :
    BaseFragment<HelpVM>(R.layout.fragment_help, HelpVM::class) {

    private lateinit var adapter: SmartRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentHelpBinding>()?.viewModel = viewModel
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://satodia-smit.github.io/resume/")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }


}