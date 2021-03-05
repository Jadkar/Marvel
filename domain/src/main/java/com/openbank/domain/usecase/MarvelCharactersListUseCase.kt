package com.openbank.domain.usecase

import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface MarvelCharactersListUseCase {

     fun getCharactersList(offSet:Int): Observable<List<CharacterListUiModel>>
}