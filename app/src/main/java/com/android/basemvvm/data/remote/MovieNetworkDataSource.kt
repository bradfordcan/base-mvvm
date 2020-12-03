package com.android.basemvvm.data.remote

import androidx.lifecycle.LiveData
import com.android.basemvvm.data.SampleApiService
import com.android.basemvvm.data.model.Movie

interface MovieNetworkDataSource {
    val downloadedMovie: LiveData<Movie>

    // Updates liveData
    // Asynchronous
    suspend fun fetchMovie(movieName: String)
}