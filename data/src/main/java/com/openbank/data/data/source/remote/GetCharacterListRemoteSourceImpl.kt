package com.openbank.data.data.source.remote

import com.openbank.data.data.mapper.CharacterListMapper
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterListRemoteSourceImpl @Inject constructor(
    private val service: MarvelApi,
    private val characterListMapper: CharacterListMapper
) : GetCharacterListRemoteSource {
    override fun getMarvelCharacterList(offset: Int): Observable<List<CharacterListUiModel>> {
        return  service.getCharactersList(offset).map {
             characterListMapper.getCharactersListUiModel(it)
         }
    }
}