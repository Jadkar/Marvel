package com.openbank.domain.datasource

import com.openbank.domain.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterListUiModel

interface CharacterListDataSource {
    fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListUiModel>
}