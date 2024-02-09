package com.example.kotlinpractisedate04022024.app

import android.app.Application
import androidx.room.Room
import com.example.kotlinpractisedate04022024.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "ProductDB"
        ).build()
    }
}