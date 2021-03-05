package com.openbank.domain.entity

class Series {

    var available: Long = 0
    var collectionURI: String? = null

    var items: List<Item>? =
        null

    var returned: Long = 0

}