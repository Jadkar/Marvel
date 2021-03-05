package com.openbank.data.data.mapper

import com.openbank.data.data.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterDetailsUiModel

interface CharactersDetailsMapper {
    fun getCharactersDetailUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterDetailsUiModel>
}