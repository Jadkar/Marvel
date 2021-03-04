package com.globant.openbankassignment.ui.characterslist

import com.globant.openbankassignment.domain.uimodel.CharacterListMapper

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListMapper?)
}