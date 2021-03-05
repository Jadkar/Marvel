package com.openbank.data.data.mapper


import com.openbank.data.data.entity.MarvelCharactersResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.openbank.domain.uimodel.*
import java.lang.reflect.Type
import javax.inject.Inject

class CharactersDetailsMapperImpl @Inject constructor() :
    CharactersDetailsMapper {

    override fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse): List<CharacterDetailsUiModel> {
        var characterDetailsMapper = ArrayList<CharacterDetailsUiModel>()

        // Comics
        if (marvelCharactersResponse?.data?.results?.get(0)?.comics?.items != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.comics?.items?.size!! > 0
        ) {
            var characterComicsTitle: String? = CharactersDeatilsType.COMICS.value

            val jsonInString: String =
                Gson().toJson(marvelCharactersResponse?.data?.results?.get(0)?.comics!!)
            var itemComics: ComicsUIModel = Gson().fromJson(jsonInString, ComicsUIModel::class.java)

            var characterDetailsComics = CharacterDetailsUiModel()
            characterDetailsComics.title = characterComicsTitle
            characterDetailsComics.comics = itemComics
            characterDetailsMapper.add(characterDetailsComics)
        }

        //Series
        if (marvelCharactersResponse?.data?.results?.get(0)?.series?.items != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.series?.items?.size!! > 0
        ) {
            var characterSeriesTitle: String? = CharactersDeatilsType.SERIES.value

            val jsonInitemSeries: String =
                Gson().toJson(marvelCharactersResponse?.data?.results?.get(0)?.series!!)

            var itemSeries: SeriesUiModel =
                Gson().fromJson(jsonInitemSeries, SeriesUiModel::class.java)

            var characterDetailsSeries = CharacterDetailsUiModel()
            characterDetailsSeries.title = characterSeriesTitle
            characterDetailsSeries.series = itemSeries

            characterDetailsMapper.add(characterDetailsSeries)
        }

        //Stories
        if (marvelCharactersResponse?.data?.results?.get(0)?.stories?.storiesItems != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.stories?.storiesItems?.size!! > 0
        ) {
            var characterStoriesTitle: String? = CharactersDeatilsType.STORIES.value

            val jsonInitemStories: String =
                Gson().toJson(marvelCharactersResponse?.data?.results?.get(0)?.stories!!)

            var itemStories: StoriesUiModel =
                Gson().fromJson(jsonInitemStories, StoriesUiModel::class.java)

            var characterDetailsStories = CharacterDetailsUiModel()
            characterDetailsStories.title = characterStoriesTitle
            characterDetailsStories.stories = itemStories

            characterDetailsMapper.add(characterDetailsStories)
        }

        //Events
        if (marvelCharactersResponse?.data?.results?.get(0)?.events?.items != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.events?.items?.size!! > 0
        ) {
            var characterEventsTitle: String? = CharactersDeatilsType.EVENTS.value

            val jsonInitemEvents: String =
                Gson().toJson(marvelCharactersResponse?.data?.results?.get(0)?.events!!)

            var itemEvents: EventsUiModel =
                Gson().fromJson(jsonInitemEvents, EventsUiModel::class.java)

            var characterDetailsEvents = CharacterDetailsUiModel()
            characterDetailsEvents.title = characterEventsTitle
            characterDetailsEvents.events = itemEvents

            characterDetailsMapper.add(characterDetailsEvents)
        }

        //Details Link URLS
        var characterUrlsTitle: String? = CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value

        val jsonInitemUrl =
            Gson().toJsonTree(marvelCharactersResponse?.data?.results?.get(0)?.urls!!)

        val type: Type = object : TypeToken<List<UrlUiModel?>?>() {}.getType()

        var itemUrl: List<UrlUiModel> = Gson().fromJson(jsonInitemUrl, type)

        var characterDetailsUrls = CharacterDetailsUiModel()
        characterDetailsUrls.title = characterUrlsTitle
        characterDetailsUrls.urls = itemUrl

        characterDetailsMapper.add(characterDetailsUrls)


        return characterDetailsMapper
    }
}