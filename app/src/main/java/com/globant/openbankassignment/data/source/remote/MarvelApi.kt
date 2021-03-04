package com.globant.openbankassignment.data.source.remote

import com.globant.openbankassignment.domain.entity.MarvelCharactersResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharactersList(@Query("offset") offset: Int): Observable<MarvelCharactersResponse>

    @GET("/v1/public/characters/{characterId}")
    fun getCharactersById(@Path("characterId") characterId: Long): Observable<MarvelCharactersResponse>
}