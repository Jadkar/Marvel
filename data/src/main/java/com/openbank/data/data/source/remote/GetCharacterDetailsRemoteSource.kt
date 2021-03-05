package com.openbank.data.data.source.remote

import com.openbank.domain.uimodel.CharacterDetailsUiModel
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface GetCharacterDetailsRemoteSource {

 fun getMarvelCharacterDetails(characterId:Long): Observable<List<CharacterDetailsUiModel>>
}