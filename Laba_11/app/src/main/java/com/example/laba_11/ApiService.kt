package com.example.laba_11


import com.example.laba_11.network.NetworkPost
import retrofit2.Response
import retrofit2.http.*

data class Post(
    val id: Int,
    val title: String,
    val body: String
)


interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<NetworkPost>>
}
