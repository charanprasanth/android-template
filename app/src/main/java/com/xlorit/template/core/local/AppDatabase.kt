package com.xlorit.template.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xlorit.template.core.local.dao.UserDao
import com.xlorit.template.core.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}