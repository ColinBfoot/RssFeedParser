package com.sas.rssfeedparser


import com.sas.rssfeedparser.data.model.Channel
import com.sas.rssfeedparser.util.FeedUtil
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FeedUtilTest {

    private lateinit var feedItems: ArrayList<Channel.Item>

    @Before
    fun feedItemSetup() {
        feedItems = ArrayList<Channel.Item>()

        val testItem1: Channel.Item = Channel.Item()
        testItem1.title = "Test title 1"
        testItem1.link = "http://testlink1.com"
        testItem1.guid = "abcdef"
        testItem1.pubDate = "Feb 25th 2018"

        feedItems.add(testItem1)

        val testItem2: Channel.Item = Channel.Item()
        testItem2.title = "Test title 2"
        testItem2.link = "http://testlink2.com"
        testItem2.guid = "abcdef"
        testItem2.pubDate = "Feb 24th 2018"

        feedItems.add(testItem2)

        val testItem3: Channel.Item = Channel.Item()
        testItem3.title = "Test title 1"
        testItem3.link = "http://testlink1.com"
        testItem3.guid = "ghijkl"
        testItem3.pubDate = "Feb 25th 2018"

        feedItems.add(testItem3)

    }

    @Test
    fun testFeedItemsAreTheSameByGuid_true() {
        assertTrue(FeedUtil.areFeedItemsTheSame(feedItems[0], feedItems[1]))
    }

    @Test
    fun testFeedItemsAreTheSameByGuid_false() {
        assertFalse(FeedUtil.areFeedItemsTheSame(feedItems[0], feedItems[2]))
    }

    @Test
    fun testFeedItemsAreTheSameByContents_true() {
        assertTrue(FeedUtil.areFeedContentsTheSame(feedItems[0], feedItems[2]))
    }

    @Test
    fun testFeedItemsAreTheSameByContents_false() {
        assertFalse(FeedUtil.areFeedContentsTheSame(feedItems[0], feedItems[1]))
    }
}
