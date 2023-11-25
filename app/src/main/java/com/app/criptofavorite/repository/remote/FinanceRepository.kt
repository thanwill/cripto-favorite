package com.app.criptofavorite.repository.remote

import android.content.Context
import com.app.criptofavorite.model.FinanceResponse
import retrofit2.Call

class FinanceRepository( context: Context) {
    val api = FinanceClient.createService()

    fun list(): Call<FinanceResponse> {
        return api.getFinance()
    }
}