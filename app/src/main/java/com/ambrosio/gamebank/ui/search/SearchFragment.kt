package com.ambrosio.gamebank.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.gamebank.R
import com.ambrosio.gamebank.adapters.VideoGameAdapter

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchGamesAdapter : VideoGameAdapter

    private lateinit var tvNoResults: TextView
    private lateinit var imgNoResults: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViewModel()
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initView(view: View){
        imgNoResults = view.findViewById(R.id.imgNoResult)
        tvNoResults = view.findViewById(R.id.tvNoResults)
        hideNoResultsView()

        val etHeader = view.findViewById<EditText>(R.id.etHeader)
        etHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.context,R.drawable.ic_clear), null)
        etHeader.isEnabled = true



        val searchGamesRV = view.findViewById<RecyclerView>(R.id.searchRV)
        searchGamesRV.layoutManager = GridLayoutManager(context, 2)

        searchGamesAdapter = VideoGameAdapter(isFavEnabled = false, touchActionDelegate = null)
        searchGamesRV.adapter = searchGamesAdapter



        etHeader.onSubmit { submit(etHeader.text.toString()) }


    }

    private fun hideNoResultsView(){
        imgNoResults.isInvisible = true
        tvNoResults.isInvisible = true
    }

    private fun showNoResultsView(){
        imgNoResults.isInvisible = false
        tvNoResults.isInvisible = false
    }



    private fun initViewModel(){
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.getSearchListObserver().observe(viewLifecycleOwner, { videoGames ->
            if(videoGames.size > 0) {
                hideNoResultsView()
                searchGamesAdapter.clear()
                searchGamesAdapter.setUpdatedData(videoGames)
            } else
                showNoResultsView()
        })
    }

    private fun EditText.onSubmit(func: ()-> Unit){
        setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                func()
            }
            true
        }
    }

    private fun submit(searchTerm: String){
        searchViewModel.fetchSearchTerm(searchTerm)
    }
}