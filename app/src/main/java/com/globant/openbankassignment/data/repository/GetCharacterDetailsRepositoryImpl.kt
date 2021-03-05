package com.globant.openbankassignment.data.repository

import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.globant.openbankassignment.domain.entity.MarvelCharactersResponse
import com.globant.openbankassignment.domain.repository.GetCharactersDetailsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailsRepositoryImpl @Inject constructor(private val service: MarvelApi) :
    GetCharactersDetailsRepository {
    override fun getCharactersDetailsById(characterId: Long): Observable<MarvelCharactersResponse> {
        return service.getCharactersById(characterId)
    }
}