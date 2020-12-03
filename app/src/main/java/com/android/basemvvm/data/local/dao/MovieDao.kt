package com.android.basemvvm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.basemvvm.data.model.Movie

@Dao
interface MovieDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(movieEntry: MovieEntry)

     @Query("select * from movie where id = $MOVIE_ID")
     fun getMovieEntry(): LiveData<MovieEntry>

}