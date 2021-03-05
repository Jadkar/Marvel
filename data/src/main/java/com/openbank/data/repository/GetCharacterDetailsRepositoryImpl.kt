package com.openbank.data.repository

import com.openbank.data.remote.GetCharacterDetailsRemoteSource
import com.openbank.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailsRepositoryImpl @Inject constructor(private val getCharacterDetailsRemoteSource: GetCharacterDetailsRemoteSource) :
    GetCharactersDetailsRepository {
    override fun getCharactersDetailsById(characterId: Long):  Observable<List<CharacterDetailsUiModel>> {
        return getCharacterDetailsRemoteSource.getMarvelCharacterDetails(characterId)
    }
}