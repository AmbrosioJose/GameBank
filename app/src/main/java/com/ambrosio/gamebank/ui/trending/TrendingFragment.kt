package com.ambrosio.gamebank.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.gamebank.R
import com.ambrosio.gamebank.adapters.VideoGameAdapter
import com.ambrosio.gamebank.ui.design.GridSpacingItemDecoration
import com.ambrosio.gamebank.ui.search.SearchViewModel
import java.util.*

class TrendingFragment : Fragment() {


    private lateinit var trendingViewModel: TrendingViewModel
    private lateinit var trendingGamesAdapter : VideoGameAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val viewModel  = ViewModelProvider(this).get(TrendingViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_trending, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initView(view: View){
        val etHeader: EditText = view.findViewById(R.id.etHeader)
        etHeader.setText(getString(R.string.title_trending_games).toUpperCase(Locale.ROOT))
        val headerIcon: ImageView = view.findViewById(R.id.headerIcon)
        headerIcon.setImageResource(R.drawable.ic_trending)
        val trendingGamesRV = view.findViewById<RecyclerView>(R.id.trendingRV)
        trendingGamesRV.layoutManager = GridLayoutManager(context, 2)

        trendingGamesAdapter = VideoGameAdapter()
        trendingGamesRV.adapter = trendingGamesAdapter
    }

    private fun initViewModel(){
        trendingViewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        trendingViewModel.getTrendingListObserver().observe(viewLifecycleOwner, { videoGames ->
            trendingGamesAdapter.setUpdatedData(videoGames)
        })

        trendingViewModel.fetchTrending()
    }
}