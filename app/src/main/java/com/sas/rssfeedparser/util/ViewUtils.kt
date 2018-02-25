package com.sas.rssfeedparser.util

import android.app.Activity
import android.util.DisplayMetrics


/**
 * Created by colin on 2/24/18.
 */
class ViewUtils {

    companion object {

        fun getScreenHeight(activity: Activity): Float {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels

            return height.toFloat()
        }

        fun getScreenWidth(activity: Activity): Int {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)

            return displayMetrics.heightPixels
        }
    }
}