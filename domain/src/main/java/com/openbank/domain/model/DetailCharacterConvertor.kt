package com.openbank.domain.model



object DetailCharacterConvertor {

     fun convertComicsItem(comicsItem: ComicsModel):List<ItemModel>{

        var convertedItemList = ArrayList<ItemModel>()
        for (item in comicsItem.items!!) {
            // body of loop
            val listItem= ItemModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }

        return convertedItemList
    }

     fun convertSeriesItem(seriesItem: SeriesModel):List<ItemModel>{
        var convertedItemList = ArrayList<ItemModel>()
        for (item in seriesItem.items!!) {
            // body of loop
            val listItem= ItemModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertStoriesItem(storiesItem: StoriesModel):List<ItemModel>{
        var convertedItemList = ArrayList<ItemModel>()
        for (item in storiesItem.storiesItems!!) {
            // body of loop
            val listItem= ItemModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertEventsItem(eventsItem: EventsModel):List<ItemModel>{
        var convertedItemList = ArrayList<ItemModel>()
        for (item in eventsItem.items!!) {
            // body of loop
            val listItem= ItemModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertUrlsItem(urlsItem:List<UrlModel>):List<ItemModel>{
        var convertedItemList = ArrayList<ItemModel>()
        for (item in urlsItem) {
            // body of loop
            val listItem= ItemModel()
            listItem.name=item.type
            listItem.resourceURI=item.url
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

}