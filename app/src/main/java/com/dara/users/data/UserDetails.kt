package com.dara.users.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class UserDetails(
    @PrimaryKey val id: String,
    val phone: String,
    val lastName: String,
    val firstName: String,
    @Embedded val location: Location,
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
