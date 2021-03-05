package com.globant.openbankassignment.ui.characterslist

import com.openbank.domain.model.CharacterListModel

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListModel?)
}