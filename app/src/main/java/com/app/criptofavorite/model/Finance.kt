package com.app.criptofavorite.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Finance(
    val by: String,
    val valid_key: Boolean,
    val results: Results,
    val execution_time: Int,
    val from_cache: Boolean
)

data class Results(
    val currencies: Currencies,
    val stocks: Stocks,
    val available_sources: List<String>,
    val taxes: List<Any>
)

// Currencies.kt
@Entity(tableName = "currencies")
data class Currencies(
    @PrimaryKey(autoGenerate = true)
    val source: String,
    @Embedded(prefix = "usd_")
    val USD: Currency,
    @Embedded(prefix = "eur_")
    val EUR: Currency,
    @Embedded(prefix = "gbp_")
    val GBP: Currency,
    @Embedded(prefix = "ars_")
    val ARS: Currency,
    @Embedded(prefix = "cad_")
    val CAD: Currency,
    @Embedded(prefix = "aud_")
    val AUD: Currency,
    @Embedded(prefix = "jpy_")
    val JPY: Currency,
    @Embedded(prefix = "cny_")
    val CNY: Currency,
    @Embedded(prefix = "btc_")
    val BTC: Currency
)

@Entity(tableName = "currency")
data class Currency(
    // "name": "Dollar", "buy": 4.9057, "sell": 4.9057, "variation": -0.006
    val name: String,
    val buy: Double,
    val sell: Double,
    val variation: Double

)

@Entity (tableName = "stocks")
data class Stocks(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val IBOVESPA: Stock,
    val IFIX: Stock,
    val NASDAQ: Stock,
    val DOWJONES: Stock,
    val CAC: Stock,
    val NIKKEI: Stock
)

@Entity (tableName = "stock")
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val location: String,
    val points: Double,
    val variation: Double
)