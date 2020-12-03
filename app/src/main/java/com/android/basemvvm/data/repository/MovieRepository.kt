package com.android.basemvvm.data.repository

import androidx.lifecycle.LiveData
import com.android.basemvvm.data.local.dao.MovieEntry

interface MovieRepository {
    suspend fun getMovie(): LiveData<out MovieEntry>
}