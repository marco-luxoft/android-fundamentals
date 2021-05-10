package com.luxoft.films.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Table
@Entity(tableName = "person_entity")
data class PersonEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "age") var age: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}