package com.openbank.domain.uimodel



object DetailCharacterConvertor {

     fun convertComicsItem(comicsItem: ComicsUIModel):List<ItemUiModel>{

        var convertedItemList = ArrayList<ItemUiModel>()
        for (item in comicsItem.items!!) {
            // body of loop
            val listItem=ItemUiModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }

        return convertedItemList
    }

     fun convertSeriesItem(seriesItem: SeriesUiModel):List<ItemUiModel>{
        var convertedItemList = ArrayList<ItemUiModel>()
        for (item in seriesItem.items!!) {
            // body of loop
            val listItem=ItemUiModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertStoriesItem(storiesItem: StoriesUiModel):List<ItemUiModel>{
        var convertedItemList = ArrayList<ItemUiModel>()
        for (item in storiesItem.storiesItems!!) {
            // body of loop
            val listItem=ItemUiModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertEventsItem(eventsItem: EventsUiModel):List<ItemUiModel>{
        var convertedItemList = ArrayList<ItemUiModel>()
        for (item in eventsItem.items!!) {
            // body of loop
            val listItem=ItemUiModel()
            listItem.name=item.name
            listItem.resourceURI=item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

     fun convertUrlsItem(urlsItem:List<UrlUiModel>):List<ItemUiModel>{
        var convertedItemList = ArrayList<ItemUiModel>()
        for (item in urlsItem) {
            // body of loop
            val listItem=ItemUiModel()
            listItem.name=item.type
            listItem.resourceURI=item.url
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

}