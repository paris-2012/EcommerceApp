package com.example.ecommerceapp.model.local

import androidx.room.*

@Dao
interface CartDao {
    @Insert
    fun insertCartItem(vararg note: CartItem)
    @Query("Select * FROM MyCart")
    fun getAllCartItems(): List<CartItem>
    @Update
    fun updateCartItem(vararg note: CartItem)
    @Delete
    fun deleteCartItem(vararg note: CartItem)
}