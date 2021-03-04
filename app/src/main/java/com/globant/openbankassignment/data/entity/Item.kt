package com.globant.openbankassignment.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}