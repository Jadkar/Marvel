package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterListRemoteSource
import com.openbank.domain.repository.GetCharactersRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class GetCharactersRepositoryImplTest {

    @Mock
    lateinit var getCharacterListRemoteSource: GetCharacterListRemoteSource

    private lateinit var getCharactersRepository: GetCharactersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCharactersRepository = GetCharactersRepositoryImpl(getCharacterListRemoteSource)
    }

    @Test
    fun testGetCharactersSuccessful() {
        getCharactersRepository.getCharacters(0)

        Mockito.verify(getCharacterListRemoteSource).getMarvelCharacterList(0)
    }


}