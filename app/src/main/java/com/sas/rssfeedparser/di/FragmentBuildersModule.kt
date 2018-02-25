package com.sas.rssfeedparser.di

import com.sas.rssfeedparser.view.ui.FeedListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by colin on 2/24/18.
 */

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeFeedListFragment(): FeedListFragment
}