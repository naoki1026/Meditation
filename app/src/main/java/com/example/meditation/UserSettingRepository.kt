package com.example.meditation

import android.content.Context
import android.content.SharedPreferences
import net.minpro.meditation.model.UserSettings
import net.minpro.meditation.model.UserSettingsPrefKey

class UserSettingRepository {
    private val context : Context = MyApplication.appContext
    private val pref: SharedPreferences = context.getSharedPreferences(
        UserSettings.PREF_USERSETTINGS_NAME, Context.MODE_PRIVATE)

    fun loadUserSettings(): UserSettings {
        return UserSettings(
            levelId = pref.getInt(UserSettingsPrefKey.LEVEL_ID.name, LevelId.EASY),
            levelName = context.getString(
                pref.getInt(UserSettingsPrefKey.LEVEL_NAME_STR_ID.name, R.string.level_easy_header)),
            themeId = pref.getInt(UserSettingsPrefKey.THEME_ID.name, 0),
            themeName = context.getString(
                pref.getInt(UserSettingsPrefKey.THEME_NAME_STR_ID.name, R.string.theme_silent)),
            themeResId = pref.getInt(UserSettingsPrefKey.THEME_RES_ID.name, R.drawable.pic_nobgm),
            themeSoundId = pref.getInt(UserSettingsPrefKey.THEME_SOUND_ID.name, 0),
            time = pref.getInt(UserSettingsPrefKey.TIME.name, 30)
        )
    }

}