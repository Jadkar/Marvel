package com.globant.openbankassignment.domain.usecase

import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.data.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharactersListUseCaseImpl @Inject constructor(
    val repository: GetCharactersRepositoryImpl,
    val characterListMapper: CharacterListMapper
) : MarvelCharactersListUseCase {

    override fun getCharactersList(offSet: Int): Observable<List<CharacterListUiModel>> {
        return repository.getCharacters(offSet).map {
            characterListMapper.getCharactersListUiModel(it)
        }
    }


}