package com.sas.rssfeedparser.view.adapter

import android.databinding.BindingAdapter
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView

import com.sas.rssfeedparser.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null && "" != url) {
        val context = imageView.context
        val res = context.resources
        val imageDimensions = res.getDimension(R.dimen.feed_thumbnail_size).toInt()

        Picasso.with(imageView.context)
                .load(url)
                .resize(imageDimensions, imageDimensions)
                .into(imageView)

        imageView.visibility = VISIBLE
    } else {
        imageView.visibility = GONE
    }
}