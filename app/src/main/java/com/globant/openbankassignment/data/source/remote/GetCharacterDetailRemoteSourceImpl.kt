package com.globant.openbankassignment.data.source.remote

import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.data.mapper.CharactersDetailsMapper
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterDetailRemoteSourceImpl @Inject constructor(
    private val service: MarvelApi,
    private val charactersDetailsMapper: CharactersDetailsMapper
) : GetCharacterDetailsRemoteSource {
    override fun getMarvelCharacterDetails(characterId: Long): Observable<List<CharacterDetailsUiModel>> {
        return service.getCharactersById(characterId).map {
            charactersDetailsMapper.getCharactersDetailUiModel(it)
        }
    }

}