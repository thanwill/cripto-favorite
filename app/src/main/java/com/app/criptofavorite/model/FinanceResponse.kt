package com.app.criptofavorite.model

class FinanceResponse {
    //"by": "default", "valid_key": false, "results":{"currencies": {}, "stocks": {}, "available_sources": [], "taxes": []}, "execution_time": 0, "from_cache": true
    var by: String = ""
    var valid_key: Boolean = false
    var results: Results = Results()
    var execution_time: Int = 0
    var from_cache: Boolean = false

}

class Results {
    var currencies: Currencies = Currencies()
    var stocks: Stocks = Stocks()
    var available_sources: List<String> = listOf()
    var taxes: List<Taxes> = listOf()
}