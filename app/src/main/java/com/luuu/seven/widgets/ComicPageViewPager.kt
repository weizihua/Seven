package com.luuu.seven.widgets

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class ComicPageViewPager : ViewPager {

    private var isLocked = false

    constructor(context: Context): super(context) {
        isLocked = false
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        isLocked = false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!isLocked) {
            return try {
                super.onInterceptTouchEvent(ev)
            } catch (e: IllegalArgumentException) {
                false
            }

        }
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return !isLocked && super.onTouchEvent(ev)
    }

    fun setLocked(isLocked: Boolean) {
        this.isLocked = isLocked
    }
}