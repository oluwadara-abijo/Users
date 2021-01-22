package com.dara.users

import com.dara.users.utils.DateUtils
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

class DateUtilsTest {
    @Test
    fun dateOfBirth_formatsCorrectly() {
        val formattedDate = DateUtils().formatDate("2021-01-22T21:15:08.878Z")
        assertThat(formattedDate, equalTo("22 Jan 2021"))
    }

}