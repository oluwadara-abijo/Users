package com.dara.users.utils

import com.dara.users.data.User

class TestUtil {

    companion object {
        fun createUser(): User {
            return User(
                "123",
                "Harris",
                "Kamala",
                "kamala.harris@email.com",
                "Mrs.",
                "https://pictureurl.com"
            )

        }
    }

}

