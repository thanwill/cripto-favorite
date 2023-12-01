package com.app.criptofavorite.model

import androidx.room.Entity

@Entity(tableName = "users")
data class User (
    val name: String,
    val email: String,
    val phone: String,
    val newsletter: Boolean,
    val imageProfile: String = ""
)