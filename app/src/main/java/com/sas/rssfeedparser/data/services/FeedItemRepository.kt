package com.sas.rssfeedparser.data.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sas.rssfeedparser.data.model.RSS
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

    fun getFeed(feed: String): LiveData<RSS> {
        val data = MutableLiveData<RSS>()

        feedService.getFeed(feed).enqueue(object : Callback<RSS> {
            override fun onResponse(call: Call<RSS>, response: Response<RSS>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<RSS>, t: Throwable) {

            }
        })

        return data
    }
}