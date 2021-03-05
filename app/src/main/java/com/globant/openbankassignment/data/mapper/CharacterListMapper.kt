package com.globant.openbankassignment.data.mapper



import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import com.openbank.domain.uimodel.CharacterListUiModel

interface CharacterListMapper {
    fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListUiModel>
}