package com.example.materialtest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by 咸鱼至尊 on 2021/11/10
 */
class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}