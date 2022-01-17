package com.lcyer.swit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcyer.swit.data.User
import com.lcyer.swit.data.UserDao

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "swit-database"

        fun buildDataBase(
            context: Context
        ): AppDataBase = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}
