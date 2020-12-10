package com.lekstel.aqi.main.di.module

import android.content.Context
import androidx.room.Room
import com.lekstel.aqi.constants.Constants.API_TOKEN_NAME
import com.lekstel.aqi.constants.Constants.API_TOKEN_VALUE
import com.lekstel.aqi.constants.Constants.API_URL
import com.lekstel.aqi.main.data.cache.AppDatabase
import com.lekstel.aqi.stations.data.network.AqiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder().addQueryParameter(API_TOKEN_NAME, API_TOKEN_VALUE)
                .build()
            chain.proceed(request.newBuilder().url(url).build())
        }
        .build()

    @Provides
    @Singleton
    fun provideAqiService(client: OkHttpClient): AqiService = Retrofit.Builder()
        .client(client)
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AqiService::class.java)
}