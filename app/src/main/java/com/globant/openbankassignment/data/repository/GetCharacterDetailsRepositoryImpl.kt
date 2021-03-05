package com.globant.openbankassignment.data.repository

import com.globant.openbankassignment.data.source.remote.GetCharacterDetailsRemoteSource
import com.globant.openbankassignment.data.source.remote.MarvelApi
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