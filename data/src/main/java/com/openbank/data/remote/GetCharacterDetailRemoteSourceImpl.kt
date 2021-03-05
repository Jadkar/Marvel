package com.openbank.data.remote

import com.openbank.data.mapper.CharactersDetailsMapper
import com.openbank.domain.uimodel.CharacterDetailsUiModel
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