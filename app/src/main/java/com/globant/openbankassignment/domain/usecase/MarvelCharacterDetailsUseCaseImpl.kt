package com.globant.openbankassignment.domain.usecase

import com.globant.openbankassignment.data.mapper.CharacterDetailsMapper
import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.data.repository.GetCharacterDetailsRepositoryImpl
import com.globant.openbankassignment.data.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.domain.repository.GetCharactersDetailsRepository
import com.globant.openbankassignment.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharacterDetailsUseCaseImpl @Inject constructor(
    val repository: GetCharacterDetailsRepositoryImpl,
    private val characterDetailsMapper: CharacterDetailsMapper
) : MarvelCharactersDetailsUseCase {

    override fun getCharactersDetailsList(characterId: Long): Observable<List<CharacterDetailsUiModel>> {

        return repository.getCharactersDetailsById(characterId).map {
            characterDetailsMapper.getCharactersDetailUiModel(it)
        }
    }


}