package com.globant.openbankassignment.domain.repository

import com.globant.openbankassignment.data.model.MarvelCharactersResponse
import com.globant.openbankassignment.data.source.remote.MarvelApi
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailsRepositoryImpl @Inject constructor(private val service: MarvelApi) :
    GetCharactersDetailsRepository {
    override fun getCharactersDetailsById(characterId: Int): Observable<MarvelCharactersResponse> {
        return service.getCharactersById(characterId)
    }
}