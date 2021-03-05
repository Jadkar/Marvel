package com.openbank.domain.usecase


import com.openbank.domain.repository.GetCharactersRepository
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharactersListUseCaseImpl @Inject constructor(
    val repository: GetCharactersRepository
) : MarvelCharactersListUseCase {

    override fun getCharactersList(offSet: Int): Observable<List<CharacterListUiModel>> {
        return repository.getCharacters(offSet)
    }


}