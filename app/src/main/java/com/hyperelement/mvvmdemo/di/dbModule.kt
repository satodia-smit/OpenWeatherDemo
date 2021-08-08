package com.hyperelement.mvvmdemo.di

import android.app.Application
import androidx.room.Room
import com.hyperelement.mvvmdemo.data.datasources.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {

    single { provideRoomDatabase(androidApplication()) }

    factory { get<AppDatabase>().cityDao() }

}


fun provideRoomDatabase(app: Application): AppDatabase {
    return Room.databaseBuilder(app, AppDatabase::class.java, " weather.db").build()
}