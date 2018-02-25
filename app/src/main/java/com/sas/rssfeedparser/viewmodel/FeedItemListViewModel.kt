package com.sas.rssfeedparser.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.sas.rssfeedparser.data.model.RSS
import com.sas.rssfeedparser.data.services.FeedItemRepository
import javax.inject.Inject

/**
 * Created by colin on 2/23/18.
 */

class FeedItemListViewModel @Inject
constructor(feedItemRepository: FeedItemRepository, app: Application) : AndroidViewModel(app) {

    companion object {
        const val FEED_URL = "http://feeds.feedburner.com/blogspot/AndroidDevelopersBackstage?format=xml"
    }

    val feedItemListObservable: LiveData<RSS> = feedItemRepository.getFeed(FEED_URL)

}