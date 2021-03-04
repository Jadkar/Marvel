package com.globant.openbankassignment.domain.usecase

import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface MarvelCharactersListUseCase {

     fun getCharactersList(offSet:Int): Observable<List<CharacterListUiModel>>
}