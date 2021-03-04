package com.globant.openbankassignment.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stories {
    @SerializedName("available")
    @Expose
    var available: Long = 0

    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null

    @SerializedName("items")
    @Expose
    var storiesItems: List<StoriesItem>? = null

    @SerializedName("returned")
    @Expose
    var returned: Long = 0

}