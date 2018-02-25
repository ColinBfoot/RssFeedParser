package com.sas.rssfeedparser.util

import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by colin on 2/25/18.
 */
class Util {

    companion object {
        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo

            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}