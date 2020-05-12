package com.example.meditation

enum class FragmentTag {
    MEISO,
    THEME_SELECT,
    LEVEL_SELECT,
    TIME_SELECT
}
object ThemeId {
    const val SILENT : Int = 0
    const val CAVE : Int = 1
    const val MT_SPRING : Int = 2
    const val MT_SUMMER : Int = 3
    const val RICE_FIELD : Int = 4
    const val STREAM :Int = 5
    const val WIND_BELLS : Int = 6
    const val FIRE_WORKS : Int = 7
}
object PlayStatus {
    const val BEFORE_START  = 0
    const val ON_START  = 1
    const val RUNNING = 2
    const val PAUSE = 3
    const val END  = 4
}
object LevelId {
    const val EASY  = 0
    const val NORMAL = 1
    const val MID = 2
    const val HIGH = 3
}
const val NO_BGM = 0
const val NOTIFICATION_ID = 999
const val NOTIFICATION_CHANNEL_ID = "Channel_1"
const val PQ_PENDING_INENT = 100

