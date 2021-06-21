package com.ambrosio.gamebank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


const val VIDEO_GAME_TABLE = "video_games"
@Serializable
@Entity(tableName = VIDEO_GAME_TABLE)
class VideoGame (

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val name: String,

    val genres: ArrayList<Genre>,

    val cover: Cover,

    val rating: Double
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
