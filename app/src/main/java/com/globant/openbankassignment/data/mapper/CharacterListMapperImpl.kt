package com.globant.openbankassignment.data.mapper

import com.globant.openbankassignment.data.entity.MarvelCharactersResponse

import com.openbank.domain.uimodel.CharacterListUiModel
import javax.inject.Inject

class CharacterListMapperImpl @Inject constructor():
    CharacterListMapper {

    override fun getCharactersListUiModel(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListUiModel>{
        var characterListMapperArray = ArrayList<CharacterListUiModel>()
        if (marvelCharactersResponse.data?.results?.size!! > 0) {
            for (result in marvelCharactersResponse.data?.results!!) {
                var characterListMapper =
                   CharacterListUiModel()
                characterListMapper.characterName = result.name
                characterListMapper.characterId = result.id
                characterListMapper.characterDescription = result.description
                characterListMapper.characterUrl =
                    result?.thumbnail?.path + "." + result?.thumbnail?.extension

                characterListMapperArray.add(characterListMapper)
            }
        }
        return characterListMapperArray
    }
}