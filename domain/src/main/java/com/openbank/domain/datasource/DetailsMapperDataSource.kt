package com.globant.openbankassignment.data.mapper.datasource

import com.openbank.domain.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterDetailsUiModel

interface DetailsMapperDataSource {
    fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterDetailsUiModel>
}