package com.luuu.seven.repository

import com.luuu.seven.bean.ComicIntroBean
import com.luuu.seven.bean.ReadHistoryBean
import com.luuu.seven.db.CollectDao
import com.luuu.seven.db.ReadHistoryDao
import com.luuu.seven.http.HttpManager
import com.luuu.seven.http.TaskData
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


class IntroRepository {

    suspend fun getComicIntro(comicId: Int): ComicIntroBean {
        return HttpManager.getInstance.getService().getComicIntro(comicId)
    }

    suspend fun getReadHistory(comicId: Int): List<ReadHistoryBean> {
        return ReadHistoryDao.get().queryByComicId(comicId)
    }

    suspend fun favoriteComic(comicId: Int, comicTitle: String, comicAuthors: String, comicCover: String, time: Long): Boolean {
        return CollectDao.get().insert(comicId, comicTitle, comicAuthors, comicCover, time)
    }

    suspend fun isFavorite(comicId: Int): Boolean {
        return CollectDao.get().queryById(comicId)
    }

    suspend fun unFavoriteComic(comicId: Int): Boolean {
        return CollectDao.get().cancelCollect(comicId)
    }
}