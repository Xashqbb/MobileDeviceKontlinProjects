package com.example.laba_11


import retrofit2.Call
import retrofit2.http.*

data class Post(
    val id: Int,
    val title: String,
    val body: String
)


interface ApiService {

    // CREATE
    @POST("posts")
    suspend fun createPost(@Body post: Post): Call<Post>

}
