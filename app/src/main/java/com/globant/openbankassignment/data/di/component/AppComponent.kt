package com.globant.openbankassignment.data.di.component

import com.globant.openbankassignment.data.di.module.*
import com.globant.openbankassignment.data.di.module.ViewModelModule
import com.globant.openbankassignment.utils.MarvelApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component
    (modules = [AndroidSupportInjectionModule::class,NetworkApiModule::class,ActivityModule::class, ViewModelModule::class,
     FragmentModule::class,RepositoryModule::class])
interface AppComponent:AndroidInjector<MarvelApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MarvelApplication):AppComponent.Builder

        fun build():AppComponent
    }
}