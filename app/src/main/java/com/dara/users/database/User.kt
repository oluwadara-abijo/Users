package com.dara.users.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a User object from the server
 */

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: String,
    val lastName: String,
    val firstName: String,
    val picture: String
)