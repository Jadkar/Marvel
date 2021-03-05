package com.openbank.data.mapper



import com.openbank.data.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterListUiModel

interface CharacterListMapper {
    fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListUiModel>
}