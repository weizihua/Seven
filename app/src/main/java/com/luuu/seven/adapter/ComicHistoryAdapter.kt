package com.luuu.seven.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.luuu.seven.R
import com.luuu.seven.bean.ReadHistoryBean
import com.luuu.seven.util.ifNotNull
import com.luuu.seven.util.loadImg

/**
 * Created by lls on 2017/8/9.
 * 历史阅读界面列表适配器
 */
class ComicHistoryAdapter(layoutResId: Int, data: List<ReadHistoryBean>) :
        BaseQuickAdapter<ReadHistoryBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: ReadHistoryBean?) {
        ifNotNull(helper, item, { helper, item ->
            helper.setText(R.id.tv_shelf_title, item.comicTitle)
            helper.setText(R.id.tv_shelf_other, "看到 ${item.chapterTitle}")
            helper.getView<ImageView>(R.id.iv_shelf_cover).loadImg(item.comicCover)
        })

    }
}