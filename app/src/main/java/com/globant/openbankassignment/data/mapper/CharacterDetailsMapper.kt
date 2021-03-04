package com.globant.openbankassignment.data.mapper

import com.globant.openbankassignment.domain.entity.*
import com.globant.openbankassignment.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.domain.uimodel.CharactersDeatilsType
import javax.inject.Inject

class CharacterDetailsMapper @Inject constructor(){

    fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterDetailsUiModel>{
        var characterDetailsMapper = ArrayList<CharacterDetailsUiModel>()

        // Comics
        if (marvelCharactersResponse?.data?.results?.get(0)?.comics?.items != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.comics?.items?.size!! > 0
        ) {
            var characterComicsTitle: String? = CharactersDeatilsType.COMICS.value

            var itemComics: Comics = marvelCharactersResponse?.data?.results?.get(0)?.comics!!

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

            var itemSeries: Series = marvelCharactersResponse?.data?.results?.get(0)?.series!!

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

            var itemStories: Stories = marvelCharactersResponse?.data?.results?.get(0)?.stories!!

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

            var itemEvents: Events = marvelCharactersResponse?.data?.results?.get(0)?.events!!

            var characterDetailsEvents = CharacterDetailsUiModel()
            characterDetailsEvents.title = characterEventsTitle
            characterDetailsEvents.events = itemEvents

            characterDetailsMapper.add(characterDetailsEvents)
        }

        //Details Link URLS
        var characterUrlsTitle: String? = CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value

        var itemUrl: List<Url> = marvelCharactersResponse?.data?.results?.get(0)?.urls!!

        var characterDetailsUrls = CharacterDetailsUiModel()
        characterDetailsUrls.title = characterUrlsTitle
        characterDetailsUrls.urls = itemUrl

        characterDetailsMapper.add(characterDetailsUrls)


        return characterDetailsMapper
    }
}