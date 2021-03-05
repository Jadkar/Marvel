package com.globant.openbankassignment.data.repository

import com.globant.openbankassignment.data.source.remote.GetCharacterListRemoteSource
import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.openbank.domain.repository.GetCharactersRepository
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharactersRepositoryImpl @Inject constructor(private val getCharacterListRemoteSource: GetCharacterListRemoteSource) :
    GetCharactersRepository {
    override fun getCharacters(offset: Int): Observable<List<CharacterListUiModel>> {
        return getCharacterListRemoteSource.getMarvelCharacterList(offset)
    }

}