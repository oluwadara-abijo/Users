package com.dara.users.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a User object from the server
 */

@Parcelize
data class User(
    val id: String,
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