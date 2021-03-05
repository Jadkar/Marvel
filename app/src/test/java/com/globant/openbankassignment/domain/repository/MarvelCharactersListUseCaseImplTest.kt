package com.globant.openbankassignment.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openbank.data.data.mapper.CharacterListMapperImpl
import com.openbank.data.data.repository.GetCharactersRepositoryImpl
import com.openbank.domain.usecase.MarvelCharactersListUseCaseImpl
import com.openbank.domain.uimodel.CharacterListUiModel
import com.globant.openbankassignment.testutil.RxImmediateSchedulerRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.reflect.Type


internal class MarvelCharactersListUseCaseImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var marvelCharactersListUseCaseImpl: MarvelCharactersListUseCaseImpl

    @MockK
    private lateinit var getCharactersRepositoryImpl: GetCharactersRepositoryImpl

    @MockK
    private lateinit var mapperImpl: CharacterListMapperImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getCharactersRepositoryImpl= mockkClass(GetCharactersRepositoryImpl::class)
        mapperImpl= mockkClass(CharacterListMapperImpl::class)
        marvelCharactersListUseCaseImpl =
            MarvelCharactersListUseCaseImpl(
                getCharactersRepositoryImpl,mapperImpl
            )

    }
   /* @BeforeEach
    fun setUp() {
        var deatilData="[{\"comics\":{\"available\":3,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\"items\":[{\"name\":\"Hulk (2008) #53\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40632\"},{\"name\":\"Hulk (2008) #54\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40630\"},{\"name\":\"Hulk (2008) #55\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40628\"}],\"returned\":3},\"title\":\"Comics\"},{\"series\":{\"available\":2,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/series\",\"items\":[{\"name\":\"FREE COMIC BOOK DAY 2013 1 (2013)\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/17765\"},{\"name\":\"Hulk (2008 - 2012)\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/3374\"}],\"returned\":2},\"title\":\"Series\"},{\"stories\":{\"available\":7,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\"returned\":7,\"items\":[{\"name\":\"Hulk (2008) #55\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92078\",\"type\":\"cover\"},{\"name\":\"Interior #92079\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92079\",\"type\":\"interiorStory\"},{\"name\":\"Hulk (2008) #54\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92082\",\"type\":\"cover\"},{\"name\":\"Interior #92083\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92083\",\"type\":\"interiorStory\"},{\"name\":\"Hulk (2008) #53\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92086\",\"type\":\"cover\"},{\"name\":\"Interior #92087\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92087\",\"type\":\"interiorStory\"},{\"name\":\"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/105929\",\"type\":\"cover\"}]},\"title\":\"Stories\"},{\"title\":\"Character Info\",\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/characters/76/a-bomb?utm_campaign\\u003dapiRef\\u0026utm_source\\u003daaf8c52b225738170b254090a7f12cf3\"},{\"type\":\"comiclink\",\"url\":\"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign\\u003dapiRef\\u0026utm_source\\u003daaf8c52b225738170b254090a7f12cf3\"}]}]"
    }*/

    @Test
    fun getCharactersList() {

        var exceptedData="[{\"characterDescription\":\"\",\"characterId\":1011334,\"characterName\":\"3-D Man\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg\"},{\"characterDescription\":\"Rick Jones has been Hulk\\u0027s best bud since day one, but now he\\u0027s more than a friend...he\\u0027s a teammate! Transformed by a Gamma energy explosion, A-Bomb\\u0027s thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\"characterId\":1017100,\"characterName\":\"A-Bomb (HAS)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg\"},{\"characterDescription\":\"AIM is a terrorist organization bent on destroying the world.\",\"characterId\":1009144,\"characterName\":\"A.I.M.\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg\"},{\"characterDescription\":\"\",\"characterId\":1010699,\"characterName\":\"Aaron Stack\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.\",\"characterId\":1009146,\"characterName\":\"Abomination (Emil Blonsky)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg\"},{\"characterDescription\":\"\",\"characterId\":1016823,\"characterName\":\"Abomination (Ultimate)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"\",\"characterId\":1009148,\"characterName\":\"Absorbing Man\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7.jpg\"},{\"characterDescription\":\"\",\"characterId\":1009149,\"characterName\":\"Abyss\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/9/30/535feab462a64.jpg\"},{\"characterDescription\":\"\",\"characterId\":1010903,\"characterName\":\"Abyss (Age of Apocalypse)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548.jpg\"},{\"characterDescription\":\"\",\"characterId\":1011266,\"characterName\":\"Adam Destine\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.\",\"characterId\":1010354,\"characterName\":\"Adam Warlock\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg\"},{\"characterDescription\":\"\",\"characterId\":1010846,\"characterName\":\"Aegis (Trey Rollins)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/5/e0/4c0035c9c425d.gif\"},{\"characterDescription\":\"\",\"characterId\":1011297,\"characterName\":\"Agent Brand\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/4/60/52695285d6e7e.jpg\"},{\"characterDescription\":\"Originally a partner of the mind-altering assassin Black Swan, Nijo spied on Deadpool as part of the Swan\\u0027s plan to exact revenge for Deadpool falsely taking credit for the Swan\\u0027s assassination of the Four Winds crime family, which included Nijo\\u0027s brother.\",\"characterId\":1011031,\"characterName\":\"Agent X (Nijo)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"\",\"characterId\":1009150,\"characterName\":\"Agent Zero\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/f/60/4c0042121d790.jpg\"},{\"characterDescription\":\"\",\"characterId\":1011198,\"characterName\":\"Agents of Atlas\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/9/a0/4ce18a834b7f5.jpg\"},{\"characterDescription\":\"\",\"characterId\":1011175,\"characterName\":\"Aginar\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"\",\"characterId\":1011136,\"characterName\":\"Air-Walker (Gabriel Lan)\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg\"},{\"characterDescription\":\"\",\"characterId\":1011176,\"characterName\":\"Ajak\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/2/80/4c002f35c5215.jpg\"},{\"characterDescription\":\"\",\"characterId\":1010870,\"characterName\":\"Ajaxis\",\"characterUrl\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/70/4c0035adc7d3a.jpg\"}]"
        val groupListType: Type =
            object : TypeToken<ArrayList<CharacterListUiModel>>() {}.type
        val mockResponse:List<CharacterListUiModel> = Gson().fromJson(exceptedData, groupListType)

        val observableMock = Observable.just(mockResponse)

       // coEvery { marvelCharactersListUseCaseImpl.getCharactersList(0) } coAnswers Observable.just(mockResponse)
        every { marvelCharactersListUseCaseImpl.getCharactersList(0) } returns Observable.just(mockResponse)

        var result = marvelCharactersListUseCaseImpl.getCharactersList(0)

        verify { marvelCharactersListUseCaseImpl.getCharactersList(0) }

        Assert.assertEquals(observableMock, result)
    }
}