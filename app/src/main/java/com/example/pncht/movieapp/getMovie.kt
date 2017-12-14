package com.example.pncht.movieapp

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface MovieService {

    @GET("get_movie_avaiable")
    fun callMovies(): Call<Result>

}