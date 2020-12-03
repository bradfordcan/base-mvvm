package com.android.basemvvm.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.basemvvm.data.SampleApiService
import com.android.basemvvm.data.remote.ConnectivityInterceptorImpl
import com.android.basemvvm.data.remote.MovieNetworkDataSourceImpl
import com.android.basemvvm.empty.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = SampleApiService(ConnectivityInterceptorImpl(this))
        val movieNetworkDataSource = MovieNetworkDataSourceImpl(apiService)

        movieNetworkDataSource.downloadedMovie.observe(this, Observer {
            // Display results
        })

        GlobalScope.launch(Dispatchers.Main) {
            movieNetworkDataSource.fetchMovie("Avengers End Game")
        }

    }
}