package com.sol.textviewutils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.clearSpans
import java.util.regex.Matcher

class SocialTextView : AppCompatTextView, OnViewClick, View.OnClickListener {

    private lateinit var _hashList: ArrayList<String>
    private lateinit var _mentionList: ArrayList<String>
    private var _onViewClick: OnViewClick? = null ?: this
    private var _onClickListener: OnClickListener? = null ?: this
    private var _preventClick = false
    var hashColor: Int = Color.parseColor("#00B0FF")
    var mentionColor: Int = Color.parseColor("#FF3D00")
    var highLightColor: Int = Color.TRANSPARENT
    var type = FLAG_ALL
    var boldHashText = false
    var boldMentionText = false

    companion object {
        val FLAG_NONE = 0
        val FLAG_MENTION = 1
        val FLAG_HASH = 2
        val FLAG_ALL = 3
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, att: AttributeSet) : super(context, att) {

        val typedArray = context.obtainStyledAttributes(att, R.styleable.SocialTextView)
        hashColor = typedArray.getColor(
            R.styleable.SocialTextView_hashColor, hashColor)

        mentionColor = typedArray.getColor(
            R.styleable.SocialTextView_mentionColor, mentionColor)
        type = typedArray.getInteger(R.styleable.SocialTextView_tagType, FLAG_ALL)
        boldHashText = typedArray.getBoolean(R.styleable.SocialTextView_boldHashText, false)
        boldMentionText = typedArray.getBoolean(R.styleable.SocialTextView_boldHashText, false)
        typedArray.recycle()
    }

    constructor(context: Context, att: AttributeSet, deffStyle: Int) : super(
        context,
        att,
        deffStyle
    ) {

        val typedArray = context.obtainStyledAttributes(att, R.styleable.SocialTextView)
        hashColor = typedArray.getColor(
            R.styleable.SocialTextView_hashColor, hashColor
        )
        mentionColor = typedArray.getColor(
            R.styleable.SocialTextView_mentionColor, mentionColor
        )

        type = typedArray.getInteger(R.styleable.SocialTextView_tagType, FLAG_ALL)
        boldHashText = typedArray.getBoolean(R.styleable.SocialTextView_boldHashText, false)
        boldMentionText = typedArray.getBoolean(R.styleable.SocialTextView_boldHashText, false)
        typedArray.recycle()

    }


    override fun setText(text: CharSequence?, type: BufferType?) {
        highlightColor = highLightColor
        super.setText(setSpannableText(text), type)
    }


    private fun setSpannableText(txt: CharSequence?): SpannableString {
        val spannable = SpannableString(txt)
        spannable.clearSpans()
        when (type) {
            0 -> {}
            1 -> {
                findHash(spannable)
            }
            2 -> {
                findMention(spannable)
            }
            3 -> {
                findHash(spannable)
                findMention(spannable)
            }
        }
        return spannable
    }

    private fun findHash(spannable: SpannableString) {
        val matcher: Matcher = hashPattern.matcher(spannable)
        _hashList = ArrayList()

        while (matcher.find()) {
            val hash = matcher.group()
            _hashList.add(hash)
            val click = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    _preventClick = true
                    _onViewClick?.onHashClick(hash)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = hashColor
                    ds.typeface = if (boldHashText) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
                }
            }
            spannable.setSpan(
                click,
                matcher.start(),
                matcher.end(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    private fun findMention(spannable: SpannableString) {
        val matcher: Matcher = mentionPattern.matcher(spannable)
        _mentionList = ArrayList()
        while (matcher.find()) {
            val hash = matcher.group()
            _mentionList.add(hash)
            val click = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    _preventClick = true
                    _onViewClick?.onMentionClick(hash)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = mentionColor
                    ds.typeface = if (boldMentionText) Typeface.DEFAULT_BOLD else Typeface.DEFAULT


                }
            }
            spannable.setSpan(
                click,
                matcher.start(),
                matcher.end(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }


    fun getHashList(): ArrayList<String> {
        return _hashList
    }

    fun getMentionList(): ArrayList<String> {
        return _mentionList
    }

    fun setSocialCallback(callback: OnViewClick) {
        movementMethod = LinkMovementMethod.getInstance()
        _onViewClick = callback
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this._onClickListener = l
        super.setOnClickListener(this)
    }

    override fun onHashClick(tag: String) {}
    override fun onMentionClick(mention: String) {}

    override fun onClick(v: View?) {
        if (_preventClick)
            _preventClick = false
        else if (_onClickListener != null)
            _onClickListener?.onClick(v)
    }


}