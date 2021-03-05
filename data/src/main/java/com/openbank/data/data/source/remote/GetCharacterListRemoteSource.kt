package com.openbank.data.data.source.remote

import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface GetCharacterListRemoteSource {

 fun getMarvelCharacterList(offset:Int): Observable<List<CharacterListUiModel>>
}