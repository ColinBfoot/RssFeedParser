package com.sas.rssfeedparser.di

import com.sas.rssfeedparser.view.ui.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by colin on 2/24/18.
 */

@Module
abstract class FeedActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    internal abstract fun contributeFeedActivity(): FeedActivity
}