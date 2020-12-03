package com.android.basemvvm.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val MOVIE_ID = 0

@Entity(tableName = "movie")
data class MovieEntry(
    @SerializedName("movie_name")
    val movieName: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = MOVIE_ID
}