package com.openbank.domain.repository

import com.openbank.domain.entity.MarvelCharactersResponse
import io.reactivex.Observable


interface GetCharactersRepository {
    fun getCharacters(offset:Int): Observable<MarvelCharactersResponse>
}