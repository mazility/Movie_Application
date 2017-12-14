package com.example.pncht.movieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kimkevin.cachepot.CachePot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_infos.*

class MovieInfos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_infos)

        val movie = CachePot.getInstance().pop<Movie>(Movie::class.java)
        Picasso.with(applicationContext).load(movie.picture).into(picture)
        movie_name.text = movie.title
        date.text = movie.date
        genre.text = movie.genre
        detail.text = movie.detail
    }
}
