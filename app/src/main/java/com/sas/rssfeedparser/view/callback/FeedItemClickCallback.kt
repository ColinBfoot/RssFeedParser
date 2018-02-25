package com.sas.rssfeedparser.view.callback

import com.sas.rssfeedparser.data.model.Channel

interface FeedItemClickCallback {
    fun onClick(feedItem: Channel.Item)
    fun onPlayButtonClick(feedItem: Channel.Item)
}
