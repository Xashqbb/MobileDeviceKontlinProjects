package com.example.travelmate.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class UserDao {
    @Dao
    interface WishlistDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertWishlist(wishlist: Wishlist)

        @Query("SELECT * FROM wishlist WHERE userId = :userId")
        fun getWishlistForUser(userId: Int): LiveData<List<Wishlist>>

        @Query("SELECT id FROM users WHERE email = :email LIMIT 1")
        fun getUserIdByEmail(email: String): Int?
    }

}