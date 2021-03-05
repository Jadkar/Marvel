package com.openbank.domain.repository
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable

interface GetCharactersDetailsRepository {
    fun getCharactersDetailsById(characterId:Long):  Observable<List<CharacterDetailsUiModel>>
}