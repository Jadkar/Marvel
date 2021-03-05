package com.globant.openbankassignment.ui.characterslist

import androidx.lifecycle.MutableLiveData
import com.openbank.domain.usecase.MarvelCharactersListUseCase
import com.globant.openbankassignment.ui.base.BaseViewModel
import com.openbank.domain.uimodel.CharacterListUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(private val useCaseCharactersList: MarvelCharactersListUseCase) :
    BaseViewModel() {

    lateinit var disposableObserverCharacters: DisposableObserver<List<CharacterListUiModel>>
    var getCharactersFailure: MutableLiveData<String> = MutableLiveData()

    var charactersResponse: MutableLiveData<List<CharacterListUiModel>> = MutableLiveData()

    fun getCharactersList(offSet: Int) {

        disposableObserverCharacters = object : DisposableObserver<List<CharacterListUiModel>>() {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                getCharactersFailure.postValue(e.message)
            }

            override fun onNext(t: List<CharacterListUiModel>) {
                charactersResponse.postValue(t)

            }
        }
        useCaseCharactersList.getCharactersList(offSet)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserverCharacters)

    }


}