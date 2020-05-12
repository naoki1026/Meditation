package com.example.meditation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.minpro.meditation.model.UserSettings

class MainViewModel : ViewModel() {
    var msgUpperSmall = MutableLiveData<String>()
    var msgLowerLarge = MutableLiveData<String>()
    var themePicFilResId = MutableLiveData<Int>()
    var txtTheme = MutableLiveData<String>()
    var txtLevel = MutableLiveData<String>()

    var remainedTimeSeconds = MutableLiveData<Int>()
    var displayTimeSeconds = MutableLiveData<String>()

    var playStatus = MutableLiveData<Int>()

    private val userSettingsRepository = UserSettingRepository()
    private lateinit var userSettings: UserSettings
    fun initParameters() {
        userSettings = userSettingsRepository.loadUserSettings()
        msgUpperSmall.value = ""
        msgLowerLarge.value = ""
        themePicFilResId.value = userSettings.themeResId
        txtTheme.value = userSettings.themeName
        txtLevel.value = userSettings.levelName
        remainedTimeSeconds.value = userSettings.time * 60
        displayTimeSeconds.value  = changeTimeFormat(remainedTimeSeconds.value!!)
        playStatus.value = PlayStatus.BEFORE_START
    }
    private fun changeTimeFormat(timeSeconds: Int): String? {
        val minutes = timeSeconds / 60
        val minutesString =  if(minutes < 10) "0" + minutes.toString() else minutes.toString()
        val seconds = timeSeconds - (minutes * 60)
        val secondsString = if (seconds < 10) "0" + seconds.toString() else seconds.toString()
        return minutesString + ":" + secondsString
    }

}