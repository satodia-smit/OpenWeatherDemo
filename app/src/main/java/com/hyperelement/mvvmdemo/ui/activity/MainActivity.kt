package com.hyperelement.mvvmdemo.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.R.layout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_help -> {
                val navController =
                    Navigation.findNavController(this, R.id.navHostFragment)
                navController.navigateUp()
                navController.navigate(R.id.fragmentHelp)
            }
        }
        return true
    }
}
