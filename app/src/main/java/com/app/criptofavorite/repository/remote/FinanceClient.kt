package com.app.criptofavorite.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class FinanceClient {
    companion object{

        private const val BASE_URL = "https://api.hgbrasil.com"
        private lateinit var INSTANCE : Retrofit

        // Função da classe: Criar uma instância do Retrofit
        fun getInstance() : Retrofit {

            if(!::INSTANCE.isInitialized){
                val HTTP = OkHttpClient.Builder()
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(HTTP.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun createService() : FinanceService {
            return getInstance().create(FinanceService::class.java)
        }

    }
}