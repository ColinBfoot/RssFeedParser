package com.sas.rssfeedparser.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.sas.rssfeedparser.R
import com.sas.rssfeedparser.data.model.Channel
import com.sas.rssfeedparser.di.Injectable
import com.sas.rssfeedparser.view.adapter.FeedItemAdapter
import com.sas.rssfeedparser.view.callback.FeedItemClickCallback
import com.sas.rssfeedparser.viewmodel.FeedItemListViewModel
import kotlinx.android.synthetic.main.fragment_feed.view.*
import javax.inject.Inject

class FeedListFragment : Fragment(), FeedItemClickCallback, Injectable {

    companion object {
        const val TAG = "FeedItemListFragment"
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFeedSwipeRefresh: SwipeRefreshLayout
    private lateinit var mFeedItemAdapter: FeedItemAdapter
    private lateinit var mLoadingProgressBar: ContentLoadingProgressBar
    private lateinit var mEmptyText: TextView

    lateinit var mViewModel: FeedItemListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(FeedItemListViewModel::class.java)

        observeViewModel(mViewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        mRecyclerView = view.rv_feed_list
        mLoadingProgressBar = view.pb_feeds_loading
        mEmptyText = view.tv_empty_text

        loadSwipeToRefresh(view)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        mRecyclerView.layoutManager = layoutManager

        mFeedItemAdapter = FeedItemAdapter(this)
        mRecyclerView.adapter = mFeedItemAdapter

        mLoadingProgressBar.show()

        return view
    }

    private fun observeViewModel(viewModel: FeedItemListViewModel) {
        viewModel.feedItemListObservable.observe(this, Observer { it ->
            mLoadingProgressBar.hide()
            mFeedSwipeRefresh.isRefreshing = false

            if (it != null && it.isNotEmpty()) {
                mFeedItemAdapter.setFeedItems(it)
                showEmptyText(false)
            } else {
                showEmptyText(true)
            }
        })
    }

    private fun showEmptyText(showEmpty: Boolean) {
        if (showEmpty) {
            mEmptyText.visibility = VISIBLE
            mRecyclerView.visibility = GONE
            mLoadingProgressBar.visibility = GONE
        } else {
            mRecyclerView.visibility = VISIBLE
            mEmptyText.visibility = GONE
            mLoadingProgressBar.visibility = GONE
        }
    }

    override fun onClick(feedItem: Channel.Item) {
        launchLink(Uri.parse(feedItem.link))
    }

    private fun launchLink(linkUri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, linkUri)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context, "No app found to open this URL.", LENGTH_SHORT).show()
        }
    }

    override fun onPlayButtonClick(feedItem: Channel.Item) {
        playMedia(Uri.parse(feedItem.content.url), feedItem.content.type)
    }

    private fun playMedia(mediaUri: Uri, type: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(mediaUri, type)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context, "No audio player apps found.", LENGTH_SHORT).show()
        }
    }

    protected fun loadSwipeToRefresh(view: View) {
        mFeedSwipeRefresh = view.srl_feed_refresh
        mFeedSwipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { mViewModel.updateFeedItems() })
    }
}
