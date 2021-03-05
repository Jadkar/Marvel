package com.openbank.domain.usecase


import com.globant.openbankassignment.data.mapper.datasource.DetailsMapperDataSource
import com.globant.openbankassignment.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import io.reactivex.Observable
import javax.inject.Inject

class MarvelCharacterDetailsUseCaseImpl @Inject constructor(
    val repository: GetCharactersDetailsRepository,
    private val characterDetailsMapper: DetailsMapperDataSource
) : MarvelCharactersDetailsUseCase {

    override fun getCharactersDetailsList(characterId: Long): Observable<List<CharacterDetailsUiModel>> {

        return repository.getCharactersDetailsById(characterId).map {
            characterDetailsMapper.getCharactersDetailUiModel(it)
        }
    }


}