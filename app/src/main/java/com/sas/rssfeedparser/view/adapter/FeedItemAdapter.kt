package com.sas.rssfeedparser.view.adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import com.sas.rssfeedparser.R
import com.sas.rssfeedparser.data.model.Channel
import com.sas.rssfeedparser.databinding.FeedItemBinding
import com.sas.rssfeedparser.util.ViewUtils
import com.sas.rssfeedparser.view.callback.FeedItemClickCallback


class FeedItemAdapter(private val feedItemClickCallback: FeedItemClickCallback?) : RecyclerView.Adapter<FeedItemAdapter.FeedItemViewHolder>() {

    companion object {
        const val ANIMATED_ITEMS_COUNT = 10
    }

    private var mFeedList: List<Channel.Item>? = null
    private var lastAnimatedPosition: Int = -1

    fun setFeedItems(feedList: List<Channel.Item>) {
        if (mFeedList == null) {
            mFeedList = feedList
            notifyItemRangeInserted(0, feedList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return feedList.size
                }

                override fun getNewListSize(): Int {
                    return feedList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return feedList[oldItemPosition].guid === feedList[newItemPosition].guid
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val feedItem = feedList[newItemPosition]
                    val old = feedList[oldItemPosition]
                    return feedItem.toString() == old.toString()
                }
            })
            mFeedList = feedList
            result.dispatchUpdatesTo(this)
        }
    }

    private fun runSlideInAnimation(view: View, position: Int) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position
            view.translationY = ViewUtils.getScreenHeight(view.context as Activity)
            view.animate()
                    .translationY(0.toFloat())
                    .setInterpolator(DecelerateInterpolator(3f))
                    .setDuration(700)
                    .start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        val binding = DataBindingUtil
                .inflate<FeedItemBinding>(LayoutInflater.from(parent.context), R.layout.feed_item,
                        parent, false)

        binding.callback = feedItemClickCallback

        return FeedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        holder.binding.feedItem = mFeedList!![position]
        holder.binding.executePendingBindings()

        runSlideInAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return if (mFeedList == null) 0 else mFeedList!!.size
    }

    class FeedItemViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root)
}
