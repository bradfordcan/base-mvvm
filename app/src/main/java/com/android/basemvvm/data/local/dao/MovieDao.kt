package com.android.basemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.basemvvm.data.model.Movie

@Dao
interface MovieDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(movie: Movie)
}