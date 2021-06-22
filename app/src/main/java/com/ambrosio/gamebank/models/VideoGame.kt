package com.ambrosio.gamebank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


const val VIDEO_GAME_TABLE = "video_games"

@Serializable
@Entity(tableName = VIDEO_GAME_TABLE)
class VideoGame(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @SerialName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerialName("genres")
    @ColumnInfo(name = "genres")
    val genres: ArrayList<Genre>?,

    @SerialName("cover")
    @ColumnInfo(name = "cover")
    val cover: Cover?,

    @SerialName("rating")
    @ColumnInfo(name = "rating")
    val rating: Double? = 0.0,

    @SerialName("first_release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: Int = 0,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Int = 0
    )


@Serializable
class Genre(
    val id: Int,
    val name: String
)

@Serializable
class Cover(
    val id: Int,
    val url: String
)
