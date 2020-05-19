package com.example.meditation.view.main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.meditation.R
import com.example.meditation.service.MusicService
import com.example.meditation.service.MusicServiceHelper
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
    private var musicServiceHelper : MusicServiceHelper? = null


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
        musicServiceHelper = MusicServiceHelper(this)
        musicServiceHelper?.bindService()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        musicServiceHelper?.stopBgm()
        finish()
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
                    musicServiceHelper?.startBgm()
                }
                PlayStatus.PAUSE -> {
                    musicServiceHelper?.stopBgm()
                    btmNav.visibility = View.INVISIBLE


                }
                PlayStatus.END -> {
                    musicServiceHelper?.stopBgm()
                    musicServiceHelper?.ringFinalGong()
                }
            }
        })

        viewModel.volume.observe(this, Observer { volume ->
            musicServiceHelper?.setVolume(volume)
        })

    }
}
