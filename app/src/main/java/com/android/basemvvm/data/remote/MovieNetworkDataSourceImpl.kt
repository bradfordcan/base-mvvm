package com.android.basemvvm.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.basemvvm.data.SampleApiService
import com.android.basemvvm.data.model.Movie
import com.android.basemvvm.util.catchException

class MovieNetworkDataSourceImpl(private val apiService: SampleApiService) : MovieNetworkDataSource {
    private val _downloadedMovie = MutableLiveData<Movie>()
    override val downloadedMovie: LiveData<Movie>
        get() = _downloadedMovie

    override suspend fun fetchMovie(movieName: String) {
        catchException {
            val fetchedMovie = apiService.getMovie(movieName).await()
            _downloadedMovie.postValue(fetchedMovie)
        }
    }

}