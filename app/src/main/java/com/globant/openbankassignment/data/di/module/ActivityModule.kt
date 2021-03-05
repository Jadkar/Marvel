package com.globant.openbankassignment.data.di.module

import com.globant.openbankassignment.ui.characterslist.CharactersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesCharactersListActivity():CharactersListActivity
}