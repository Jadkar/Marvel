package com.globant.openbankassignment.domain.usecase

import com.globant.openbankassignment.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface MarvelCharactersDetailsUseCase {
     fun getCharactersDetailsList(characterId:Long): Observable<List<CharacterDetailsUiModel>>
}