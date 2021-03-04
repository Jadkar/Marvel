package com.globant.openbankassignment.domain.repository

import com.globant.openbankassignment.domain.entity.MarvelCharactersResponse
import io.reactivex.Observable

interface GetCharactersRepository {
    fun getCharacters(offset:Int): Observable<MarvelCharactersResponse>
}