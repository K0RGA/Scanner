package com.example.scanner

import android.app.Application
import com.example.scanner.network.ScannerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class AppComponent() : Application() {
    companion object{
        var accessToken: String = ""
        var refreshToken: String = ""
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideScannerApi(): ScannerApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.120:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ScannerApi::class.java)
    }
}
