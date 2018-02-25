package com.sas.rssfeedparser.data.services

import com.sas.rssfeedparser.data.model.RSS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by colin on 2/23/18.
 */

interface FeedService {
    companion object {
        const val FEED_BASE_URL = "http://feeds.feedburner.com/"
    }

    @GET
    fun getFeed(@Url feed: String): Call<RSS>
}