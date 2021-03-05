package com.globant.openbankassignment.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.domain.entity.MarvelCharactersResponse
import com.globant.openbankassignment.data.repository.GetCharacterDetailsRepositoryImpl
import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.google.gson.Gson
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.*

internal class GetCharacterDeatilsRepositoryImplTest  {
   /* @Rule
    @JvmField
    var instantTaskExecutorRule = InstantExecutorExtension()*/
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var getCharacterDetailsRepositoryImpl: GetCharacterDetailsRepositoryImpl

    @Mock
    private lateinit var service:MarvelApi

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service= Mockito.mock(MarvelApi::class.java)
        getCharacterDetailsRepositoryImpl =
            GetCharacterDetailsRepositoryImpl(
                service
            )

    }

    @Test
    fun testGetCharacters() {

        var characterId=1017100L
        // Excepted Result
        val gson = Gson()
        val mockList = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"© 2021 MARVEL\",\"attributionText\":\"Data provided by Marvel. © 2021 MARVEL\",\"attributionHTML\":\"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>\",\"etag\":\"20cf4cd43cc9cd35833392e0964888958f576824\",\"data\":{\"offset\":0,\"limit\":20,\"total\":1,\"count\":1,\"results\":[{\"id\":1017100,\"name\":\"A-Bomb (HAS)\",\"description\":\"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\"modified\":\"2013-09-18T15:54:04-0400\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\",\"extension\":\"jpg\"},\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1017100\",\"comics\":{\"available\":3,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40632\",\"name\":\"Hulk (2008) #53\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40630\",\"name\":\"Hulk (2008) #54\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40628\",\"name\":\"Hulk (2008) #55\"}],\"returned\":3},\"series\":{\"available\":2,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/series\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/17765\",\"name\":\"FREE COMIC BOOK DAY 2013 1 (2013)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/3374\",\"name\":\"Hulk (2008 - 2012)\"}],\"returned\":2},\"stories\":{\"available\":7,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92078\",\"name\":\"Hulk (2008) #55\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92079\",\"name\":\"Interior #92079\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92082\",\"name\":\"Hulk (2008) #54\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92083\",\"name\":\"Interior #92083\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92086\",\"name\":\"Hulk (2008) #53\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92087\",\"name\":\"Interior #92087\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/105929\",\"name\":\"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\"type\":\"cover\"}],\"returned\":7},\"events\":{\"available\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/events\",\"items\":[],\"returned\":0},\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=aaf8c52b225738170b254090a7f12cf3\"},{\"type\":\"comiclink\",\"url\":\"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=aaf8c52b225738170b254090a7f12cf3\"}]}]}}"
        val mockListResponse = gson.fromJson(mockList, MarvelCharactersResponse::class.java)

        val mockedCharactersDetailResponse=Observable.just(mockListResponse)
        // every { mockedFile.getCurrentWeatherByZipCode(options as HashMap<String, String>)) } returns responseWeather
        Mockito.`when`(getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId))
            .thenReturn(
                mockedCharactersDetailResponse)

        val exceptedResult=getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId)
        //When getting the user name
        getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId).test().assertComplete()
        getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId).test().assertNoTimeout()
        getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId).test().assertValue(mockListResponse)
        getCharacterDetailsRepositoryImpl.getCharactersDetailsById(characterId).test().assertResult(mockListResponse)

        Assert.assertEquals(mockedCharactersDetailResponse, exceptedResult)
    }
}