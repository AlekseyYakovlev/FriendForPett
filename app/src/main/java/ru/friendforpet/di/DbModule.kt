package ru.friendforpet.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.friendforpet.data.db.AppDb
import ru.friendforpet.data.db.daos.PetsDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Provides
    @Singleton
    fun provideAppDb(
        @ApplicationContext context: Context
    ): AppDb =
        Room
            .databaseBuilder(
                context,
                AppDb::class.java,
                AppDb.DATA_BASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesArticlesDao(
        db: AppDb
    ): PetsDao =
        db.petsDao()
}
