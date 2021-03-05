package com.openbank.data.data.repository

import com.openbank.data.data.source.remote.GetCharacterDetailsRemoteSource
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