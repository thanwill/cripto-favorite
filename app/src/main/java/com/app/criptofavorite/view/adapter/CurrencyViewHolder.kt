package com.app.criptofavorite.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.criptofavorite.R

class CurrencyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.textCurrencie)
    val currencyBuyValue: TextView = view.findViewById(R.id.text_currency_buy_value)
}
