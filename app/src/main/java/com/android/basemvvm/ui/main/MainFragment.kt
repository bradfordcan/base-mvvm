package com.android.basemvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.basemvvm.data.ui.MovieViewModel
import com.android.basemvvm.data.ui.MovieViewModelFactory
import com.android.basemvvm.ui.base.ScopedFragment
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainFragment : ScopedFragment(), KodeinAware {

    // private val viewModelFactory = MovieViewModelFactory()
    override val kodein by closestKodein()
    private val viewModelFactory: MovieViewModelFactory by instance()

    private lateinit var viewModel: MovieViewModel

    /*companion object {
        fun newInstance() = MainFragment()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
        /*val apiService = SampleApiService(ConnectivityInterceptorImpl(this.context!!))
        val movieNetworkDataSource = MovieNetworkDataSourceImpl(apiService)

        movieNetworkDataSource.downloadedMovie.observe(viewLifecycleOwner, Observer {
            // Display results
        })

        GlobalScope.launch(Dispatchers.Main) {
            movieNetworkDataSource.fetchMovie("Avengers End Game")
        }*/
        bindUi()
    }

    private fun bindUi() = launch {
        // cant call await from a non suspending function
        val movie = viewModel.movie.await()
        movie.observe(viewLifecycleOwner, Observer {
            if(it == null) return@Observer
            // Set value to view it.toString()
        })
    }

}