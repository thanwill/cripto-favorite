package com.app.criptofavorite.model

import androidx.room.PrimaryKey

class Taxes {
    // "date": "2023-11-23", "cdi": 12.25, "selic": 12.25, "daily_factor": 1.00045513, "selic_daily": 12.15, "cdi_daily": 12.15

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var date: String = ""
    var cdi: Double = 0.0
    var selic: Double = 0.0
    var daily_factor: Double = 0.0
    var selic_daily: Double = 0.0
    var cdi_daily: Double = 0.0

}