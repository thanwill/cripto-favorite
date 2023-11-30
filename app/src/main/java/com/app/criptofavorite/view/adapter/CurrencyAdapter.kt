package com.app.criptofavorite.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.criptofavorite.R
import com.app.criptofavorite.model.Currency

class CurrencyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val currency = ArrayList<Currency>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_finance, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currency[position]
        holder.name.text = currency.name

    }

    override fun getItemCount(): Int {
        return currency.size
    }

    fun addCurrency(currency: Currency) {
        this.currency.add(currency)
        notifyDataSetChanged()
    }
}
