package com.globant.openbankassignment.data.di.module

import com.globant.openbankassignment.data.mapper.CharacterDetailsMapper
import com.globant.openbankassignment.data.mapper.CharacterListMapper
import com.globant.openbankassignment.data.mapper.datasource.DetailsMapperDataSource
import com.globant.openbankassignment.data.repository.GetCharacterDetailsRepositoryImpl
import com.globant.openbankassignment.data.repository.GetCharactersRepositoryImpl
import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.globant.openbankassignment.domain.repository.GetCharactersDetailsRepository
import com.openbank.domain.datasource.CharacterListDataSource
import com.openbank.domain.repository.GetCharactersRepository
import com.openbank.domain.usecase.MarvelCharacterDetailsUseCaseImpl
import com.openbank.domain.usecase.MarvelCharactersDetailsUseCase
import com.openbank.domain.usecase.MarvelCharactersListUseCase
import com.openbank.domain.usecase.MarvelCharactersListUseCaseImpl
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGetCharacterRepo(api: MarvelApi): GetCharactersRepository {
        return GetCharactersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsRepo(api: MarvelApi): GetCharactersDetailsRepository {
        return GetCharacterDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetDataSource():  CharacterListDataSource{
        return CharacterListMapper()
    }

    @Provides
    @Singleton
    fun provideDetailsDataSource(): DetailsMapperDataSource {
        return CharacterDetailsMapper()
    }

    @Provides
    @Singleton
    fun provideMarvelCharactersListUseCase(  repository: GetCharactersRepository,
                                              characterListMapper: CharacterListDataSource): MarvelCharactersListUseCase {
        return MarvelCharactersListUseCaseImpl(repository,characterListMapper)
    }

    @Provides
    @Singleton
    fun provideMarvelCharactersDetailsUseCase(repository: GetCharactersDetailsRepository,
                                       characterDetailsMapper: DetailsMapperDataSource): MarvelCharactersDetailsUseCase {
        return MarvelCharacterDetailsUseCaseImpl(repository,characterDetailsMapper)
    }
}