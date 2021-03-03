package com.globant.openbankassignment.domain.repository

import com.globant.openbankassignment.data.model.MarvelCharactersResponse
import com.globant.openbankassignment.data.source.remote.MarvelApi
import io.reactivex.Observable
import javax.inject.Inject

class GetCharactersRepositoryImpl @Inject constructor(private val service: MarvelApi) :
    GetCharactersRepository {
    override fun getCharacters(offset: Int): Observable<MarvelCharactersResponse> {
        return service.getCharactersList(offset)
    }
}