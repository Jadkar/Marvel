package com.globant.openbankassignment.domain.repository

import com.globant.openbankassignment.domain.entity.MarvelCharactersResponse
import io.reactivex.Observable

interface GetCharactersDetailsRepository {
    fun getCharactersDetailsById(characterId:Long): Observable<MarvelCharactersResponse>
}