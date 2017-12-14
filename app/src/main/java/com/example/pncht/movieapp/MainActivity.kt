package com.example.pncht.movieapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*


class MainActivity : AppCompatActivity() {

    val Url = "http://www.majorcineplex.com/apis/"
    var movList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { listOfMovie() }
        //btn2.setOnClickListener { movieInfo}
    }


    private fun listOfMovie() {
        var retrofit = Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var MovieService = retrofit.create(MovieService::class.java)
        var res = MovieService.callMovies()

        res.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                Toast.makeText(applicationContext, "response", Toast.LENGTH_LONG).show()
                val movieResult = response?.body() ?: Result()
                val mov = movieResult?.movies
                Log.i("check", "Movies size " + mov?.size)
                if (mov != null) {
                    for (i in mov) {
                        movList.add(Movie(i.id,i.title,i.genre,i.date,"http://www.majorcineplex.com" +i.picture))
                    }
                    listview.adapter = MovieAdapter(applicationContext, movList)
                }
            }
        })
    }

    inner class MovieAdapter : BaseAdapter {

        var context: Context? = null
        var mList = ArrayList<Movie>()

        constructor(context: Context?, movieList: ArrayList<Movie>) : super() {
            this.context = context
            this.mList = movieList
        }

        override fun getView(i: Int, j: View?, k: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(this.context)
            var r = layoutInflater.inflate(R.layout.movie_row, null)
            var current = this.mList[i]

            r.date.text = current.date
            r.genre.text = current.genre
            r.movie_name.text = current.title
            r.setOnClickListener { movieInfo(current) }
            Picasso.with(this.context).load(current.picture).into(r.image)

            return r
        }

        override fun getItem(i: Int): Any {
            return this.mList[i]
        }

        override fun getItemId(i: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return this.mList.size
        }

        fun movieInfo(movie: Movie) {
            Toast.makeText(this.context, "Cicked on: " + movie.id, Toast.LENGTH_LONG).show()

            val i = Intent(this.context, MovieInfo::class.java)
            this.context?.startActivity(i)

        }
    }
}
