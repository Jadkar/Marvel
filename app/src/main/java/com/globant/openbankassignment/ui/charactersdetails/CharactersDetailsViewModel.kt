package com.globant.openbankassignment.ui.charactersdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.globant.openbankassignment.data.mapper.CharacterDetailsMapper
import com.globant.openbankassignment.data.mapper.CharactersDeatilsType
import com.globant.openbankassignment.data.entity.*
import com.globant.openbankassignment.data.repository.GetCharacterDetailsRepositoryImpl
import com.globant.openbankassignment.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailsViewModel @Inject constructor(private val repository: GetCharacterDetailsRepositoryImpl) :
    BaseViewModel() {

    private lateinit var disposableCharacterDetails: DisposableObserver<MarvelCharactersResponse>

    var characterDetails: MutableLiveData<List<CharacterDetailsMapper>> = MutableLiveData()
    var getCharacterDetailsFailure: MutableLiveData<String> = MutableLiveData()

    fun getCharactersDetails(characterId: Long) {

        disposableCharacterDetails = object : DisposableObserver<MarvelCharactersResponse>() {
            override fun onComplete() {
                Log.d("MarvelCharactersRes", "onComplete")
            }

            override fun onNext(t: MarvelCharactersResponse) {
                Log.d("MarvelCharactersRes", t.toString())
                characterDetails.postValue(getCharactersDetailsMapper(t))
            }

            override fun onError(e: Throwable) {
                getCharacterDetailsFailure.postValue(e.message)
                Log.d("MarvelCharactersRes", e.toString())
            }
        }
        repository.getCharactersDetailsById(characterId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableCharacterDetails)

    }

   private fun getCharactersDetailsMapper(marvelCharactersResponse: MarvelCharactersResponse): List<CharacterDetailsMapper> {


        var characterDetailsMapper = ArrayList<CharacterDetailsMapper>()

        // Comics
        if (marvelCharactersResponse?.data?.results?.get(0)?.comics?.items != null && marvelCharactersResponse?.data?.results?.get(
                0
            )?.comics?.items?.size!! > 0
        ) {
            var characterComicsTitle: String? = CharactersDeatilsType.COMICS.value

            var itemComics: Comics = marvelCharactersResponse?.data?.results?.get(0)?.comics!!

            var characterDetailsComics = CharacterDetailsMapper()
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

            var characterDetailsSeries = CharacterDetailsMapper()
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

            var characterDetailsStories = CharacterDetailsMapper()
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

            var characterDetailsEvents = CharacterDetailsMapper()
            characterDetailsEvents.title = characterEventsTitle
            characterDetailsEvents.events = itemEvents

            characterDetailsMapper.add(characterDetailsEvents)
        }

        //Details Link URLS
        var characterUrlsTitle: String? = CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value

        var itemUrl: List<Url> = marvelCharactersResponse?.data?.results?.get(0)?.urls!!

        var characterDetailsUrls = CharacterDetailsMapper()
        characterDetailsUrls.title = characterUrlsTitle
        characterDetailsUrls.urls = itemUrl

        characterDetailsMapper.add(characterDetailsUrls)


        return characterDetailsMapper
    }


    fun disposeElements() {
        if (::disposableCharacterDetails.isInitialized && !disposableCharacterDetails.isDisposed) {
            disposableCharacterDetails.dispose()
        }


        if (::disposableCharacterDetails.isInitialized && !disposableCharacterDetails.isDisposed) {
            disposableCharacterDetails.dispose()
        }
    }

}