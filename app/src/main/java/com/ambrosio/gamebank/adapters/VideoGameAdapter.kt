package com.ambrosio.gamebank.adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import com.ambrosio.gamebank.R
import com.ambrosio.gamebank.models.VideoGame
import com.bumptech.glide.Glide

interface TouchActionDelegate{
    fun onFavButtonClicked(game: VideoGame)
}

class VideoGameAdapter(private val touchActionDelegate: TouchActionDelegate?, private val isFavEnabled: Boolean = true, ): RecyclerView.Adapter<VideoGameAdapter.MyViewHolder>(){

    var items = ArrayList<VideoGame>()

    fun setUpdatedData(items: ArrayList<VideoGame>){
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View, private val isFavEnabled: Boolean): RecyclerView.ViewHolder(view){
        private val imgCover: ImageView = view.findViewById(R.id.imgGame)
        private val imgFavorite: ImageView = view.findViewById(R.id.icFavorite)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvGenre: TextView = view.findViewById(R.id.tvGenre)
        private val tvRating: TextView = view.findViewById(R.id.tvRating)

        fun bind(game: VideoGame, touchActionDelegate: TouchActionDelegate?){
            tvName.text = game.name
            if(!game.genres.isNullOrEmpty())
                tvGenre.text = game.genres.first().name?:""
            tvRating.text = "${(game?.rating?:0).toInt().toString()}%"
            if(game.cover != null && game.cover.url.isNotEmpty() ?: false){
                print("https://"+ game.cover.url)
                Glide.with(imgCover.context)
                    .load("https://"+ game.cover?.url?.replace("thumb", "cover_big"))
                    .into(imgCover)
            }

            if(!isFavEnabled){
                imgFavorite.isInvisible = true
            } else {
                if(game.isFavorite == 1)
                    imgFavorite.setImageResource(R.drawable.ic_favorite)
                else
                    imgFavorite.setImageResource(R.drawable.ic_favorite_outline)

                imgFavorite.setOnClickListener {
                    touchActionDelegate?.onFavButtonClicked(game);
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_game_item, parent, false)
        return MyViewHolder(view, this.isFavEnabled)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bind(items[position], this.touchActionDelegate)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        this.items = ArrayList()
        notifyDataSetChanged()
    }
}