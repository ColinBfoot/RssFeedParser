package com.sas.rssfeedparser.data.services

import android.arch.lifecycle.MutableLiveData
import com.sas.rssfeedparser.data.model.Channel
import com.sas.rssfeedparser.data.model.RSS
import com.sas.rssfeedparser.util.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by colin on 2/23/18.
 */

@Singleton
class FeedItemRepository @Inject
constructor(private val feedService: FeedService) {

    fun getFeed(feedUrl: String): MutableLiveData<List<Channel.Item>> {
        val data = MutableLiveData<List<Channel.Item>>()

        updateFeed(feedUrl, data)

        return data
    }

    fun updateFeed(feedUrl: String, data: MutableLiveData<List<Channel.Item>>) {
        feedService.getFeed(feedUrl).enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                data.value = response.body().channel.itemList
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                data.value = null
            }
        })
    }

    fun updateFeedWithSearchString(feedUrl: String, searchString: String, data: MutableLiveData<List<Channel.Item>>) {
        feedService.getFeed(feedUrl).enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                if (StringUtils.isNullOrEmpty(searchString))
                    data.postValue(response.body().channel.itemList)
                else
                    data.postValue(response.body().channel.itemList.filter { it.title.contains(searchString, true) })
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {
                data.value = null
            }
        })
    }
}