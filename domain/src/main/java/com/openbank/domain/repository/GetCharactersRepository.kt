package com.openbank.domain.repository

import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable


interface GetCharactersRepository {
    fun getCharacters(offset:Int): Observable<List<CharacterListUiModel>>
}