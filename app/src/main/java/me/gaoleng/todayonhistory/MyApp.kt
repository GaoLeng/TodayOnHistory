package me.gaoleng.todayonhistory

import android.app.Application
import com.lzy.okgo.OkGo

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        OkGo.getInstance().init(this)
    }
}