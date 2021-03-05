package com.openbank.data.data.mapper



import com.openbank.data.data.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterListUiModel

interface CharacterListMapper {
    fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListUiModel>
}