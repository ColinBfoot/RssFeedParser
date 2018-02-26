package com.sas.rssfeedparser.util

import com.sas.rssfeedparser.data.model.Channel

/**
 * Created by colin on 2/25/18.
 */
class FeedUtil {
    companion object {

        fun areFeedItemsTheSame(item1: Channel.Item, item2: Channel.Item): Boolean {
            return item1.guid == item2.guid
        }

        fun areFeedContentsTheSame(item1: Channel.Item, item2: Channel.Item): Boolean {
            return item1.toString() == item2.toString()
        }
    }
}