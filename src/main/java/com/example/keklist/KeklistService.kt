package com.example.keklist

import com.example.keklist.models.*
import okhttp3.OkHttpClient
import retrofit2.Call;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface KeklistService {

    @POST("login")
    fun login(@Body user: User): Call<UserToken>

    @GET("obyavlenie")
    fun getAllAds() : Call<List<Ad>>

    @GET("myObyavlenie")
    fun getUserAds(@Header("Authorization") token: String) : Call<List<Ad>>

    @GET ("obyavlenie/{id}")
    fun getAdbyID(@Path("id") id: Int) : Call<Ad>

    @GET ("user")
    fun getUser(@Header("Authorization") token: String) : Call<UserInfo>

    @PUT ("obyavlenie")
    fun postAd(@Header("Authorization") token: String, @Body post: AdToSend) : Call<FormCallback>

    @DELETE ("obyavlenie/{id}")
    fun deleteAd(@Header("Authorization") token: String, @Path("id") id: Int) : Call<FormCallback>

    companion object Factory {
        fun create(): KeklistService{

            val okHTTP = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHTTP)
                .baseUrl("http://keklist.std-276.ist.mospolytech.ru/api/v0/")
                .build()

            return retrofit.create(KeklistService::class.java)
        }
    }

}