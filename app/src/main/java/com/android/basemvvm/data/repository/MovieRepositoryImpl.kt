package com.android.basemvvm.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.android.basemvvm.data.local.dao.MovieDao
import com.android.basemvvm.data.local.dao.MovieEntry
import com.android.basemvvm.data.remote.MovieNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val movieNetworkDataSource: MovieNetworkDataSource
) : MovieRepository {

    init {
        // repository has no lifecycle, so use observeForever
        movieNetworkDataSource.downloadedMovie.observeForever { newMovie ->
            // persist
        }
    }

    override suspend fun getMovie(): LiveData<out MovieEntry> {
        // returns a value compared to launch, launch instead returns a job
        return withContext(Dispatchers.IO) {
            initMovie()
            return@withContext movieDao.getMovieEntry()
        }
    }

    // persists data in database
    private fun persistFetchedMovie(fetchedMovie: MovieEntry) {
        // you can't use this in a fragment, since fragments are bound to be destroyed
        GlobalScope.launch(Dispatchers.IO) {
            // Dispatchers.IO used for handling massive amounts of threads
            movieDao.upsert(fetchedMovie)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initMovie() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchMovie()
        }
    }

    private suspend fun fetchMovie() {
        // updates LiveData downloadedMovie in init
        movieNetworkDataSource.fetchMovie("Avengers End Game")
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                ZonedDateTime.now().minusMinutes(30)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}