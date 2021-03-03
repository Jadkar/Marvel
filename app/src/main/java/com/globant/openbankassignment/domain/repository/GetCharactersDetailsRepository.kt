package com.globant.openbankassignment.domain.repository

import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import io.reactivex.Observable

interface GetCharactersDetailsRepository {
    fun getCharactersDetailsById(characterId:Int): Observable<MarvelCharactersResponse>
}