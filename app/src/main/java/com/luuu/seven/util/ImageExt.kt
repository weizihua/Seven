package com.luuu.seven.util

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.luuu.seven.http.Api
import com.luuu.seven.util.img.GlideStrategy
import com.luuu.seven.util.img.ImgLoad

fun ImageView.get(url: String?) = Glide.with(context).load(url)
fun ImageView.get(bitmap: Bitmap?) = Glide.with(context).load(bitmap)
fun ImageView.get(resource: Int?) = Glide.with(context).load(resource)
fun ImageView.get(glideUrl: GlideUrl) = Glide.with(context).load(glideUrl)

fun ImageView.load(url: String) {
    get(url).into(this)
}

fun ImageView.loadWithHead(url: String, head: String = Api.DMZJ) {
//    get(GlideUrl(url, LazyHeaders.Builder().addHeader("Referer", head).build())).into(this)
    ImgLoad.instance.with(context).setLoadType(url).setHeader("Referer", head).build(GlideStrategy()).into(this)
}