package com.globant.openbankassignment.di.module

import com.globant.openbankassignment.BuildConfig
import com.globant.openbankassignment.data.source.remote.MarvelApi
import com.globant.openbankassignment.utils.DateHelper

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkApiModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        builder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            //val publicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
            val currentTimeStamp = DateHelper.getCurrentTimestamp()
            val haskKey =
                currentTimeStamp + BuildConfig.MARVEL_API__PRIVATE_KEY + BuildConfig.MARVEL_API_PUBLIC_KEY
            val calculatedHaskKey = DateHelper.calculateHash(haskKey)
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", currentTimeStamp)
                .addQueryParameter("apikey", BuildConfig.MARVEL_API_PUBLIC_KEY)
                .addQueryParameter("hash", calculatedHaskKey)
                .build()

            val requestBuilder = original.newBuilder().url(url)

            chain.proceed(requestBuilder.build())
        }


        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun buildService(@NonNull retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

}