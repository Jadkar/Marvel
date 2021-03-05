package com.openbank.domain.usecase

import com.openbank.domain.uimodel.CharacterDetailsUiModel
import io.reactivex.Observable

interface MarvelCharactersDetailsUseCase {
     fun getCharactersDetailsList(characterId:Long): Observable<List<CharacterDetailsUiModel>>
}