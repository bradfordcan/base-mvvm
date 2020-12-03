package com.android.basemvvm

import android.app.Application
import com.android.basemvvm.data.SampleApiService
import com.android.basemvvm.data.local.dao.MovieDatabase
import com.android.basemvvm.data.remote.ConnectivityInterceptor
import com.android.basemvvm.data.remote.ConnectivityInterceptorImpl
import com.android.basemvvm.data.remote.MovieNetworkDataSource
import com.android.basemvvm.data.remote.MovieNetworkDataSourceImpl
import com.android.basemvvm.data.repository.MovieRepository
import com.android.basemvvm.data.repository.MovieRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


// @HiltAndroidApp

class BaseApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication)) // provides context or other various services

        // bindings
        bind() from singleton { MovieDatabase(instance()) } // pass in instance to get context from androidXModule
        bind() from singleton { instance<MovieDatabase>().movieDao() } // bind all daos defined from database class
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) } // bind from ConnectivityInterceptor with a singleton instance of ConnectivityInterceptorImpl
        bind() from singleton { SampleApiService(instance()) } // gets instance from above line
        bind<MovieNetworkDataSource>() with singleton { MovieNetworkDataSourceImpl(instance()) } // gets instance from above line
        bind<MovieRepository>() with singleton { MovieRepositoryImpl(instance(), instance()) }
    }
}