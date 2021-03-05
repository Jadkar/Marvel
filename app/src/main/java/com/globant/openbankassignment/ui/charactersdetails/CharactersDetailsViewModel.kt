package com.globant.openbankassignment.ui.charactersdetails

import androidx.lifecycle.MutableLiveData
import com.openbank.domain.uimodel.CharacterDetailsUiModel
import com.openbank.domain.usecase.MarvelCharacterDetailsUseCaseImpl
import com.globant.openbankassignment.ui.base.BaseViewModel
import com.google.gson.Gson
import com.openbank.domain.usecase.MarvelCharactersDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailsViewModel @Inject constructor(private val useCaseImpl: MarvelCharactersDetailsUseCase) :
    BaseViewModel() {

    private lateinit var disposableCharacterDetails: DisposableObserver<List<CharacterDetailsUiModel>>

    var characterDetails: MutableLiveData<List<CharacterDetailsUiModel>> = MutableLiveData()
    var getCharacterDetailsFailure: MutableLiveData<String> = MutableLiveData()

    fun getCharactersDetails(characterId: Long) {

        disposableCharacterDetails = object : DisposableObserver<List<CharacterDetailsUiModel>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<CharacterDetailsUiModel>) {
                var jsonCharaterList= Gson().toJson(t)
                characterDetails.postValue(t)
            }

            override fun onError(e: Throwable) {
                getCharacterDetailsFailure.postValue(e.message)
            }
        }
        useCaseImpl.getCharactersDetailsList(characterId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableCharacterDetails)
    }

}