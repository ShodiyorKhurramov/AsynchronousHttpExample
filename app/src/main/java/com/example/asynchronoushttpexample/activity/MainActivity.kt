package com.example.asynchronoushttpexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.asynchronoushttpexample.R
import com.example.asynchronoushttpexample.adapter.CatsAdapter
import com.example.asynchronoushttpexample.model.WelcomeElement
import com.example.asynchronoushttpexample.network.AsyncHttp
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CatsAdapter
    private lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            initViews()


    }

    private fun initViews(){
        recyclerView = findViewById(R.id.rv_allcats)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = CatsAdapter(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    apiGetAllCats()
                }
            }
        })
        apiGetAllCats()
    }


    private fun apiGetAllCats(){
        AsyncHttp.get(AsyncHttp.API_LIST_POST,AsyncHttp.paramsEmpty(), object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val postArray = Gson().fromJson(String(responseBody!!), Array<WelcomeElement>::class.java)
                Log.d("@@@",postArray.toString())
                adapter.addPhotos(postArray)
                recyclerView.adapter = adapter

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(
                    "@@@",
                    "$statusCode" +
                            "$headers" +
                            "$responseBody"
                +"$error"
                )


            }


        } )
    }

}


































//    private fun apiCreatePoster(){
//
//        val poster=Poster("SHodiyor","Xurramov","12","")
//        Log.d("@@@",poster.toString())
//
//        AsyncHttp.post(this,AsyncHttp.API_CREATE_POST,AsyncHttp.paramsCreate(poster),object : AsyncHttpResponseHandler(){
//
//           override fun onSuccess(
//               statusCode: Int,
//               headers: Array<out Header>?,
//               responseBody: ByteArray?
//           ) {
//
//
//           }
//
//           override fun onFailure(
//               statusCode: Int,
//               headers: Array<out Header>?,
//               responseBody: ByteArray?,
//               error: Throwable?
//           ) {
//               Log.d(
//                   "@@@",
//                   "$statusCode" +
//                           "$headers" +
//                           "$responseBody"
//                           +"$error"
//               )
//
//           }
//
//       })
//
//    }
