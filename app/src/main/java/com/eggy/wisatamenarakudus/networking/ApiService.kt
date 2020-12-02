package com.eggy.wisatamenarakudus.networking

import com.eggy.wisatamenarakudus.model.ResponseAction
import com.eggy.wisatamenarakudus.model.ResponseWisata
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //get data
    @GET("getAll.php")
    fun getData(): Call<ResponseWisata>


    //insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("asal_kota") asal_kota: String,
        @Field("alamat") alamat: String,
        @Field("tanggal_kunjungan") tanggal_kunjungan: String,


        ): Call<ResponseAction>


    //Update data
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("nohp") nohp: String,
        @Field("asal_kota") asal_kota: String,
        @Field("alamat") alamat: String,
        @Field("tanggal_kunjungan") tanggal_kunjungan: String,

        ): Call<ResponseAction>


    //delete data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(
        @Field("id") id: String

    ): Call<ResponseAction>


}