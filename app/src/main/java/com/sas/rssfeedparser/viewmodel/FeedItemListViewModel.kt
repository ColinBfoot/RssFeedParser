package com.sas.rssfeedparser.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.sas.rssfeedparser.data.model.Channel
import com.sas.rssfeedparser.data.services.FeedItemRepository
import javax.inject.Inject

/**
 * Created by colin on 2/23/18.
 */

class FeedItemListViewModel @Inject
constructor(var feedItemRepository: FeedItemRepository, app: Application) : AndroidViewModel(app) {

    companion object {
        const val FEED_URL = "http://feeds.feedburner.com/blogspot/AndroidDevelopersBackstage?format=xml"
    }

    val feedItemListObservable: MutableLiveData<List<Channel.Item>> = feedItemRepository.getFeed(FEED_URL)

    fun updateFeedItems() {
        feedItemRepository.updateFeed(FEED_URL, feedItemListObservable)
    }

    fun loadFeedItemsForSearchString(searchString: String) {
        feedItemRepository.updateFeedWithSearchString(FEED_URL, searchString, feedItemListObservable)
    }

}