package com.ambrosio.gamebank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


const val VIDEO_GAME_TABLE = "video_games"

@Serializable
@Entity(tableName = VIDEO_GAME_TABLE)
class VideoGame (
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @SerialName("name")
    val name: String,

    @Transient
    @SerialName("genres")
    val genres: ArrayList<Genre?>? = null,

    @Transient
    @SerialName("cover")
    val cover: Cover? = null,

    @Transient
    @SerialName("rating")
    val rating: Double? = null,
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
