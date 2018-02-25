package com.sas.rssfeedparser.di

import com.sas.rssfeedparser.viewmodel.FeedItemListViewModel

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun feedItemListViewModel(): FeedItemListViewModel
}
