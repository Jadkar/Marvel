package com.globant.openbankassignment.ui.characterslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.globant.openbankassignment.data.entity.MarvelCharactersResponse
import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.domain.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersListViewModel @Inject constructor( val repository: GetCharactersRepositoryImpl):BaseViewModel() {

    private lateinit var disposableObserverCharacters: DisposableObserver<MarvelCharactersResponse>
    var getCharactersFailure: MutableLiveData<String> = MutableLiveData()

     var charactersResponse: MutableLiveData<List<CharacterListMapper>> = MutableLiveData()
    fun getCharactersList(offSet:Int){

       disposableObserverCharacters=object : DisposableObserver<MarvelCharactersResponse>(){
           override fun onComplete() {
               Log.d("MarvelCharactersRes","onComplete")
           }
           override fun onNext(t: MarvelCharactersResponse) {
               Log.d("MarvelCharactersRes",t.toString())
               charactersResponse.postValue(getCharacterListMapper(t))
           }
           override fun onError(e: Throwable) {
               Log.d("MarvelCharactersRes",e.toString())
               getCharactersFailure.postValue(e.message)
           }
       }
        repository.getCharacters(offSet)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserverCharacters)

    }

     fun getCharacterListMapper(marvelCharactersResponse: MarvelCharactersResponse):List<CharacterListMapper>{
        var characterListMapperArray= ArrayList<CharacterListMapper>()
        if (marvelCharactersResponse.data?.results?.size!! > 0 ){
            for (result in marvelCharactersResponse.data?.results!!){
                var characterListMapper=CharacterListMapper()
                characterListMapper.charcterName=result.name
                characterListMapper.characterId=result.id
                characterListMapper.characterDescription=result.description
                characterListMapper.characterUrl=result?.thumbnail?.path + "." + result?.thumbnail?.extension

                characterListMapperArray.add(characterListMapper)
            }
        }
        return characterListMapperArray
    }

    private fun disposeElements() {
        if (::disposableObserverCharacters.isInitialized && !disposableObserverCharacters.isDisposed) {
            disposableObserverCharacters.dispose()
        }


        if (::disposableObserverCharacters.isInitialized && !disposableObserverCharacters.isDisposed) {
            disposableObserverCharacters.dispose()
        }
    }

}