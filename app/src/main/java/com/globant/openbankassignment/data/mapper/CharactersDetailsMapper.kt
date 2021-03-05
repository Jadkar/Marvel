package com.globant.openbankassignment.data.mapper

import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterDetailsUiModel

interface CharactersDetailsMapper {
    fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterDetailsUiModel>
}