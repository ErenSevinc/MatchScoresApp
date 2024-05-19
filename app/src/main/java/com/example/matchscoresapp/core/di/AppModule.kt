package com.example.matchscoresapp.core.di

import android.app.Application
import androidx.room.Room
import com.example.matchscoresapp.core.Constants.BASE_URL
import com.example.matchscoresapp.data.local.AppDatabase
import com.example.matchscoresapp.data.local.LocalRepository
import com.example.matchscoresapp.data.local.MatchDao
import com.example.matchscoresapp.data.remote.ApiService
import com.example.matchscoresapp.data.repository.ApiRepositoryImpl
import com.example.matchscoresapp.domain.model.Match
import com.example.matchscoresapp.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService = api)
    }

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "MyDataBase"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase) : MatchDao{
        return appDatabase.dao()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: MatchDao): LocalRepository {
        return LocalRepository(dao)
    }

}