package com.globant.openbankassignment.domain.repository

import com.openbank.domain.entity.MarvelCharactersResponse
import io.reactivex.Observable

interface GetCharactersDetailsRepository {
    fun getCharactersDetailsById(characterId:Long): Observable<MarvelCharactersResponse>
}