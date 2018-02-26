package com.sas.rssfeedparser.util

/**
 * Created by colin on 2/25/18.
 */

class StringUtils {
    companion object {
        fun isNullOrEmpty(string: String?): Boolean {
            return string == null || "" == string
        }
    }
}