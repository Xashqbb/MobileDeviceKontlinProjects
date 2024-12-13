package com.example.travelmate.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlist(wishlist: Wishlist)

    @Query("SELECT * FROM wishlist WHERE userId = :userId")
    fun getWishlistForUser(userId: String): LiveData<List<Wishlist>>

}

