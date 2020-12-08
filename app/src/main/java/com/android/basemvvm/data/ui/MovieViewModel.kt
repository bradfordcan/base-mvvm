package com.android.basemvvm.data.ui

import androidx.lifecycle.ViewModel
import com.android.basemvvm.data.internal.lazyDeferred
import com.android.basemvvm.data.repository.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    // preservation of ViewModels is a job for a ViewModelProvider
    // we only pass the factory into the provider
    // factories work as usual, creating new instances of objects
    val movie by lazyDeferred {
        movieRepository.getMovie()
    }

}