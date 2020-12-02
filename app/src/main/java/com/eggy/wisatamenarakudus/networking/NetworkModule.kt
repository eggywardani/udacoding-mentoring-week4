package com.eggy.wisatamenarakudus.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun getRetrofit(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/pengunjung-menara-kudus/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)

    }
}