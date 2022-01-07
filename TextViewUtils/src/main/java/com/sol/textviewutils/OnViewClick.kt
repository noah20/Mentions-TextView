package com.sol.textviewutils

import java.util.regex.Pattern

interface OnViewClick {
    val hashPattern: Pattern
        get() = Pattern.compile("#\\w+")
    val mentionPattern:Pattern
    get() = Pattern.compile("@\\w+")

    fun onHashClick(tag:String)
    fun onMentionClick(mention:String)

}