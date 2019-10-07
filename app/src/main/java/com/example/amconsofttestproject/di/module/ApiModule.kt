package com.example.amconsofttestproject.di.module

import com.example.amconsofttestproject.BuildConfig
import com.example.amconsofttestproject.di.scope.ApiScope
import com.example.amconsofttestproject.usecases.repository.server.ApiService
import com.example.amconsofttestproject.usecases.repository.server.UserCommunicator
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @ApiScope
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create<ApiService>(ApiService::class.java)

    @Provides
    @ApiScope
    fun provideUserCommunicator(apiService: ApiService): UserCommunicator = UserCommunicator(apiService)

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit =
        builder.baseUrl("https://jsonplaceholder.typicode.com/").build()

    @Provides
    @ApiScope
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpLoginInterceptor = HttpLoggingInterceptor()
            httpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoginInterceptor)
        }

        return Retrofit.Builder().client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    }
}