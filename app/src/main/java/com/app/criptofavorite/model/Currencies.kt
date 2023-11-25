package com.app.criptofavorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
class Currencies {
    // id, "name": "Dollar", "buy": 4.9028, "sell": 4.9028, "variation": 0.004
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    val name: String = ""
    val buy: Double = 0.0
    val sell: Double = 0.0
    val variation: Double = 0.0

}