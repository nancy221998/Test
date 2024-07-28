package com.example.machinetestdemo.api

import com.example.machinetestdemo.model.Feed
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("ws/RSS/topsongs/limit=25/xml")
   suspend fun getXmlData(): Response<Feed>
}

