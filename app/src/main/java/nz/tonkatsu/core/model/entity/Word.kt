package nz.tonkatsu.core.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    indices = [
        Index(value = ["english"]),
        Index(value = ["kanji"]),
        Index(value = ["kana"]),
        Index(value = ["jlpt"])
    ]
)
data class Word(
    val english: String,
    val jlpt: Int?,
    val kana: String,
    val kanji: String,
    val sound: String?,
    @ColumnInfo(name = "parts_of_speech") val pos: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L
)
