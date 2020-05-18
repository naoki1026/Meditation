package com.example.meditation.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.meditation.R
import com.example.meditation.util.FragmentTag
import com.example.meditation.util.PlayStatus
import com.example.meditation.view.dialog.ThemeSelectDialog
import com.example.meditation.view.dialog.TimeSelectDialog
import com.example.meditation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import net.minpro.meditation.view.dialog.LevelSelectDialog

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.screen_container,
                    MainFragment()
                )
                .commit()
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observerViewModel()

        btmNav.setOnNavigationItemReselectedListener {item ->
            when(item.itemId) {
                R.id.item_select_level -> {
                    LevelSelectDialog().show(supportFragmentManager, FragmentTag.LEVEL_SELECT.name)
                    true
                }
                R.id.item_select_time -> {
                    TimeSelectDialog().show(supportFragmentManager, FragmentTag.TIME_SELECT.name)
                    true
                }
                R.id.item_select_theme -> {
                    ThemeSelectDialog().show(supportFragmentManager, FragmentTag.THEME_SELECT.name)
                    true
                }
                else -> {false}
            }
        }
    }

    private fun observerViewModel() {
        viewModel.playStatus.observe(this, Observer { status ->
            when(status) {
                PlayStatus.BEFORE_START -> {
                    btmNav.visibility = View.VISIBLE
                }
                PlayStatus.ON_START  -> {
                    btmNav.visibility = View.INVISIBLE

                }
                PlayStatus.RUNNING -> {
                    btmNav.visibility = View.INVISIBLE
                }
                PlayStatus.PAUSE -> {

                }
                PlayStatus.END -> {

                }
            }

        })
    }
}
