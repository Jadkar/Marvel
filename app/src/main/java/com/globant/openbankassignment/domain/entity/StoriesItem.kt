package com.globant.openbankassignment.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoriesItem {
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

}