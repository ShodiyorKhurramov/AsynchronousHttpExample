package com.example.asynchronoushttpexample.network

import android.content.Context
import com.example.asynchronoushttpexample.model.Poster
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import java.io.File
import java.io.FileNotFoundException

class AsyncHttp {
    companion object {
        val IS_TESTER = false
        val SERVER_DEVELOPMENT = "https://623303066de3467dbac5d129.mockapi.io/"
        val SERVER_PRODUCTION = "https://api.thecatapi.com/v1/images/"
        private val client = getAsyncHttpClient()

        private fun getAsyncHttpClient(): AsyncHttpClient {
            val client = AsyncHttpClient()
            client.addHeader("x-api-key", "de5fecc9-673e-4b5c-b040-7eef6fde30e5")
            return client
        }

        private fun server(url: String): String {
            if (IS_TESTER)
                return SERVER_DEVELOPMENT + url
            return SERVER_PRODUCTION + url
        }

        fun get(url: String, params: RequestParams, responseHandler: AsyncHttpResponseHandler) {
            client.get(server(url), params, responseHandler)
        }

        fun post(context: Context, url: String, body: RequestParams, responseHandler: AsyncHttpResponseHandler) {
            client.post(context, server(url), body, responseHandler)
        }

        fun put(context: Context, url: String, body: RequestParams, responseHandler: AsyncHttpResponseHandler) {
            client.post(context, server(url), body, responseHandler)
        }

        fun del(url: String, responseHandler: AsyncHttpResponseHandler) {
            client.delete(server(url), responseHandler)
        }

        /**
         *  Request Api`s
         */

        var API_LIST_POST = "search?"
        var API_SINGLE_POST = "posts/" //id
        var API_CREATE_POST = "posts"
        var API_UPDATE_POST = "posts/" //id
        var API_DELETE_POST = "posts/" //id

        /**
         *  Request Param`s
         */

        fun paramsEmpty(): RequestParams {
            val params = RequestParams()
            params.put("limit",20)
            params.put("page",1)
            return params
        }

        fun paramsCreate(poster: Poster): RequestParams {
            val params = RequestParams()
            params.put("title", poster.title)
            params.put("body", poster.body)
            params.put("userId", poster.userId)
            return params
        }

        fun paramsUpdate(poster: Poster): RequestParams {
            val params = RequestParams()
            params.put("title", poster.title)
            params.put("body", poster.body)
            params.put("userID", poster.userId)
            params.put("id", poster.id)
            return params
        }

        fun paramsMultipart(file: File): RequestParams {
            val params = RequestParams()
            try {
                params.put("file", file)
            } catch (e: FileNotFoundException) {
            }
            return params
        }

    }

}