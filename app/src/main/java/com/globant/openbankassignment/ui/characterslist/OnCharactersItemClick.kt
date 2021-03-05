package com.globant.openbankassignment.ui.characterslist

import com.openbank.domain.uimodel.CharacterListUiModel

interface OnCharactersItemClick {

    fun onCharacterSelected(result: CharacterListUiModel?)
}