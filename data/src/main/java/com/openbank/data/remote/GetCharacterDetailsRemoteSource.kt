package com.openbank.data.remote

import com.openbank.domain.uimodel.CharacterDetailsUiModel
import io.reactivex.Observable

interface GetCharacterDetailsRemoteSource {

 fun getMarvelCharacterDetails(characterId:Long): Observable<List<CharacterDetailsUiModel>>
}