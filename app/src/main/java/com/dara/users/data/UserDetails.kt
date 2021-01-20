package com.dara.users.data

data class UserDetails(
    val id: String,
    val phone: String,
    val lastName: String,
    val firstName: String,
    val location: Location,
    val email: String,
    val gender: String,
    val title: String,
    val registerDate: String,
    val picture: String,
    val dateOfBirth: String
)

data class Location(
    val state: String,
    val street: String,
    val city: String,
    val timezone: String,
    val country: String
)
