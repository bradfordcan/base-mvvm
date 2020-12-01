package com.android.basemvvm.data

import com.android.basemvvm.data.model.Movie
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ee4ee4f9edmsh7f3e8a833aadcd6p178cc0jsnc889d34413eb"

//https://movie-database-imdb-alternative.p.rapidapi.com/

interface SampleApiService {

    @GET
    fun getMovie(
        @Query("s") movieName: String
    ) : Deferred<Movie>

    // like static method
    companion object {
        // operator function
        operator fun invoke() : SampleApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SampleApiService::class.java)
        }
    }


}