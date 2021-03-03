package com.globant.openbankassignment.data.mapper

import com.globant.openbankassignment.data.entity.*

object DeatilsItemtypeconvertor {



     fun convertComicsItem(comicsItem:Comics):List<Item>{

        var convertedItemList = ArrayList<Item>()
        for (item in comicsItem.items!!) {
            // body of loop
            val listItem=Item()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }

        return convertedItemList
    }

     fun convertSeriesItem(seriesItem:Series):List<Item>{
        var convertedItemList = ArrayList<Item>()
        for (item in seriesItem.items!!) {
            // body of loop
            val listItem=Item()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertStoriesItem(storiesItem:Stories):List<Item>{
        var convertedItemList = ArrayList<Item>()
        for (item in storiesItem.storiesItems!!) {
            // body of loop
            val listItem=Item()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertEventsItem(eventsItem:Events):List<Item>{
        var convertedItemList = ArrayList<Item>()
        for (item in eventsItem.items!!) {
            // body of loop
            val listItem=Item()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertUrlsItem(urlsItem:List<Url>):List<Item>{
        var convertedItemList = ArrayList<Item>()
        for (item in urlsItem) {
            // body of loop
            val listItem=Item()
            listItem.name=item.type
            listItem.resourceURI=item.url
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

}