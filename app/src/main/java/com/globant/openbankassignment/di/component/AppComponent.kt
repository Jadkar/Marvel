package com.globant.openbankassignment.di.component

import com.globant.openbankassignment.di.module.ViewModelModule
import com.globant.openbankassignment.di.module.ActivityModule
import com.globant.openbankassignment.di.module.FragmentModule
import com.globant.openbankassignment.di.module.NetworkApiModule
import com.globant.openbankassignment.utils.MarvelApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component
    (modules = [AndroidSupportInjectionModule::class,NetworkApiModule::class,ActivityModule::class, ViewModelModule::class,
     FragmentModule::class])
interface AppComponent:AndroidInjector<MarvelApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: MarvelApplication):AppComponent.Builder

        fun build():AppComponent
    }
}