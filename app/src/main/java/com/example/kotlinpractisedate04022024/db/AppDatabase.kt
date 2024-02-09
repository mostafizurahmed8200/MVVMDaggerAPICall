package com.example.kotlinpractisedate04022024.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinpractisedate04022024.di.mvvm.model.Products

@Database(entities = [Products::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductsDao
}