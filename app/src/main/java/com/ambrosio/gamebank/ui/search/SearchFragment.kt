package com.ambrosio.gamebank.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ambrosio.gamebank.R
import com.ambrosio.gamebank.adapters.VideoGameAdapter

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchGamesAdapter : VideoGameAdapter

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
        val etHeader = view.findViewById<EditText>(R.id.etHeader)
        etHeader.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(view.context,R.drawable.ic_clear), null)
        etHeader.isEnabled = true
        val searchGamesRV = view.findViewById<RecyclerView>(R.id.searchRV)
        searchGamesRV.layoutManager = GridLayoutManager(context, 2)

        searchGamesAdapter = VideoGameAdapter()
        searchGamesRV.adapter = searchGamesAdapter



        etHeader.onSubmit { submit(etHeader.text.toString()) }


    }

    private fun initViewModel(){
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.getSearchListObserver().observe(viewLifecycleOwner, { videoGames ->
            searchGamesAdapter.setUpdatedData(videoGames)
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