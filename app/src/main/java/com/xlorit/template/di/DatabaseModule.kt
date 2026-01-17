package com.xlorit.template.di

import com.xlorit.template.core.local.AppDatabase
import com.xlorit.template.core.local.dao.UserDao

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()

    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao = db.userDao()
}
