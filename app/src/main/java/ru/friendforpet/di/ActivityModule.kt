package ru.friendforpet.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.friendforpet.ui.RootActivity

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {

    @Provides
    fun providesActivity(
        activity: Activity
    ): RootActivity = activity as RootActivity
}