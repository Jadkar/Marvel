package com.globant.openbankassignment.data.repository

import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.openbank.domain.entity.MarvelCharactersResponse
import com.openbank.domain.repository.GetCharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCharactersRepositoryImpl @Inject constructor(private val service: MarvelApi) :
    GetCharactersRepository {
    override fun getCharacters(offset: Int): Observable<MarvelCharactersResponse> {
        return service.getCharactersList(offset)
    }
}