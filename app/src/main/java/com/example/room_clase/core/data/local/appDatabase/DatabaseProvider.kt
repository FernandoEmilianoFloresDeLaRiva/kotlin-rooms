package com.example.room_clase.core.data.local.appDatabase

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var appDataBase: AppDataBase? = null

    fun getAppDataBase(ctx : Context): AppDataBase {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(
                ctx.applicationContext,
                AppDataBase::class.java,
                "app_database"
                // Para evitar problemas con cambios en la estructura
            ).fallbackToDestructiveMigration()
                .build()
        }
        return appDataBase!!
    }

    fun destroyDataBase() {
        appDataBase = null
    }
}