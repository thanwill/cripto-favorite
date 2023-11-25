package com.app.criptofavorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bitcoin")
class Bitcoin {
    // id, name, format ["",""], last, buy, sell, variation
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    val name: String = ""
    val format: List<String> = listOf()
    val last: String = ""
    val buy: String = ""
    val sell: String = ""
    val variation: String = ""
}