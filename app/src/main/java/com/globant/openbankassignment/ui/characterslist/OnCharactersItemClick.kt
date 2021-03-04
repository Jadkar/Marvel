package com.globant.openbankassignment.ui.characterslist

import com.globant.openbankassignment.domain.repository.uimodel.CharacterListMapper

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListMapper?)
}