package com.openbank.domain.usecase


import com.openbank.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharacterDetailsUseCaseImpl @Inject constructor(
    val repository: GetCharactersDetailsRepository
) : MarvelCharactersDetailsUseCase {

    override fun getCharactersDetailsList(characterId: Long): Observable<List<CharacterDetailsUiModel>> {

        return repository.getCharactersDetailsById(characterId)

    }


}