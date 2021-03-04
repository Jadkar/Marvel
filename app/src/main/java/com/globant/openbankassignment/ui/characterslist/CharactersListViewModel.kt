package com.globant.openbankassignment.ui.characterslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import com.globant.openbankassignment.domain.uimodel.CharacterListUiModel
import com.globant.openbankassignment.data.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.domain.usecase.MarvelCharactersListUseCase
import com.globant.openbankassignment.domain.usecase.MarvelCharactersListUseCaseImpl
import com.globant.openbankassignment.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(val useCaseCharactersList: MarvelCharactersListUseCaseImpl) :
    BaseViewModel() {

    lateinit var disposableObserverCharacters: DisposableObserver<List<CharacterListUiModel>>
    var getCharactersFailure: MutableLiveData<String> = MutableLiveData()

    var charactersResponse: MutableLiveData<List<CharacterListUiModel>> = MutableLiveData()

    fun getCharactersList(offSet: Int) {

        disposableObserverCharacters = object : DisposableObserver<List<CharacterListUiModel>>() {
            override fun onComplete() {
                Log.d("MarvelCharactersRes", "onComplete")
            }
            override fun onError(e: Throwable) {
                Log.d("MarvelCharactersRes", e.toString())
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