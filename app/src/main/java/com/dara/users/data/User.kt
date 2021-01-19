package com.dara.users.data

/**
 * Data class representing a User object from the server
 */

data class User(
    val id: String,
    val lastName: String,
    val firstName: String,
    val email: String,
    val title: String,
    val picture: String
)