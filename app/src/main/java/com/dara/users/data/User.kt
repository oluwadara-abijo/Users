package com.dara.users.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a User object from the server
 */

@Parcelize
@Entity
data class User(
    @PrimaryKey val id: String,
    val lastName: String,
    val firstName: String,
    val email: String,
    val title: String,
    val picture: String
) : Parcelable

/**
 *  Concatenates user's first and last name
 *  @param user User whose full name is to be returned
 *  @return User's full name
 */
fun fullName(user: User): String {
    return "${user.firstName} ${user.lastName}"
}