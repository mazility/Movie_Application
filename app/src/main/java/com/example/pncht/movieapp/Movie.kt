package com.example.pncht.movieapp

import com.google.gson.annotations.SerializedName
import java.util.*
/**
 * Created by PS on 11/14/2017 AD.
 */
data class Result(
        var movies: ArrayList<Movie> = ArrayList()
)

data class Movie(
        @SerializedName("id") var id: Int = 0,
        @SerializedName("title_en") var title: String = "",
        @SerializedName("genre") var genre: String = "",
        @SerializedName("release_date") var date: String = "",
        @SerializedName("poster_ori") var picture: String = "",
        @SerializedName("synopsis_en") var detail: String = ""
)

