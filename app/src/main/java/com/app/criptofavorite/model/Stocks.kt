package com.app.criptofavorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
class Stocks {
    // id, "name": "BM&F BOVESPA", "location": "Sao Paulo, Brazil", "points": 125517.27, "variation": -0.84
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    val name: String = ""
    val location: String = ""
    val points: Double = 0.0
    val variation: Double = 0.0

}