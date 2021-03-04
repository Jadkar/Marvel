package com.globant.openbankassignment.utils

import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.text.SimpleDateFormat
import java.util.*

class DateHelperTest  {

    @Test
    fun testGetCurrentTimestampNotNull() {
        assertNotNull(DateHelper.getCurrentTimestamp())
    }

    @Test
    fun testGetCurrentTimestamp(){
        var result=getFakeCurrentTimeStamp()
        Assertions.assertEquals(result, DateHelper.getCurrentTimestamp())
    }
    @Test
    fun testCalculateHash() {

        var haskKey="testMarvelkeyAssigmnent"
        var dummyResult="177707ff0d3e69b918de8c40a3747a77"

        Assertions.assertEquals(dummyResult, DateHelper.calculateHash(haskKey))
    }

    private fun getFakeCurrentTimeStamp():String{
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(
            Calendar
                .getInstance().time
        )
    }



}