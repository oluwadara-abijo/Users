package com.dara.users.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dara.users.data.User
import com.dara.users.data.UserDetails

/**
 * Provides methods to interact with data in the database
 */
@Dao
interface UserDao {

    // Get list of all users to display in the list
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    // Get details of selected user
    @Query("SELECT * FROM UserDetails WHERE id = :userId")
    fun getUserDetails(userId: String): LiveData<UserDetails>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserDetails(userDetails: UserDetails)

    @Query("SELECT * FROM user WHERE id=:userId")
    fun getUserById(userId: String): User?

}