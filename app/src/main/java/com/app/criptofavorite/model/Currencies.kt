package com.app.criptofavorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
class Currencies {
    // source, moeda {name, buy, sell, variation}
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    var source: String = ""
    var name: String = ""
    var buy: String = ""
    var sell: String = ""
    var variation: String = ""

}