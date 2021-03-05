package com.globant.openbankassignment.data.di.module

import com.globant.openbankassignment.data.mapper.CharactersDetailsMapperImpl
import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.data.mapper.CharacterListMapperImpl
import com.globant.openbankassignment.data.mapper.CharactersDetailsMapper
import com.globant.openbankassignment.data.repository.GetCharacterDetailsRepositoryImpl
import com.globant.openbankassignment.data.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.data.source.remote.*
import com.openbank.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.repository.GetCharactersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGetCharacterRepo(getCharacterListRemoteSource: GetCharacterListRemoteSource): GetCharactersRepository {
        return GetCharactersRepositoryImpl(getCharacterListRemoteSource)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsRepo(getCharacterDetailsRemoteSource: GetCharacterDetailsRemoteSource): GetCharactersDetailsRepository {
        return GetCharacterDetailsRepositoryImpl(getCharacterDetailsRemoteSource)
    }

    @Provides
    @Singleton
    fun provideGetCharacterListRemoteSource(service: MarvelApi, characterListMapper: CharacterListMapper): GetCharacterListRemoteSource {
        return GetCharacterListRemoteSourceImpl(service,characterListMapper)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsRemoteSource(service: MarvelApi, charactersDetailsMapper: CharactersDetailsMapper): GetCharacterDetailsRemoteSource {
        return GetCharacterDetailRemoteSourceImpl(service,charactersDetailsMapper)
    }

    @Provides
    @Singleton
    fun provideCharacterListMapperImpl(): CharacterListMapper {
        return CharacterListMapperImpl()
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsMapperImpl(): CharactersDetailsMapper {
        return CharactersDetailsMapperImpl()
    }


}