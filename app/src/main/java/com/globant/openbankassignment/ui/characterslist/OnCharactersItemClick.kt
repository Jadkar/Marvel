package com.globant.openbankassignment.ui.characterslist

import com.globant.openbankassignment.data.entity.Result
import com.globant.openbankassignment.data.mapper.CharacterListMapper

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListMapper?)
}