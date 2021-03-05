package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterListRemoteSource
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