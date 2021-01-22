package com.dara.users.database

import android.content.Context
import androidx.room.*
import com.dara.users.data.User
import com.dara.users.data.UserDetails

@Database(entities = [User::class, UserDetails::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {
            // Create the database if INSTANCE is not null
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, UsersDatabase::class.java, "users_database"
                ).build()
                INSTANCE = instance
                // Return instance
                instance
            }
        }
    }
}