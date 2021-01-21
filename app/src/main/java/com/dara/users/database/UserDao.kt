package com.dara.users.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dara.users.data.User

/**
 * Provides methods to interact with data in the database
 */
@Dao
interface UserDao {

    // Gets list of all users to display in the list
    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

}