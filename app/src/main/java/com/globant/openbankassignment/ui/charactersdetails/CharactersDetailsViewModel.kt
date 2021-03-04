package com.globant.openbankassignment.ui.charactersdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.globant.openbankassignment.domain.uimodel.CharacterDetailsUiModel
import com.globant.openbankassignment.domain.usecase.MarvelCharacterDetailsUseCaseImpl
import com.globant.openbankassignment.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailsViewModel @Inject constructor(private val useCaseImpl: MarvelCharacterDetailsUseCaseImpl) :
    BaseViewModel() {

    private lateinit var disposableCharacterDetails: DisposableObserver<List<CharacterDetailsUiModel>>

    var characterDetails: MutableLiveData<List<CharacterDetailsUiModel>> = MutableLiveData()
    var getCharacterDetailsFailure: MutableLiveData<String> = MutableLiveData()

    fun getCharactersDetails(characterId: Long) {

        disposableCharacterDetails = object : DisposableObserver<List<CharacterDetailsUiModel>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<CharacterDetailsUiModel>) {
                characterDetails.postValue(t)
            }

            override fun onError(e: Throwable) {
                getCharacterDetailsFailure.postValue(e.message)
                Log.d("MarvelCharactersRes", e.toString())
            }
        }
        useCaseImpl.getCharactersDetailsList(characterId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableCharacterDetails)
    }

}