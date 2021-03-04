package com.globant.openbankassignment.ui.characterslist

import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListUiModel?)
}