package com.soft.task.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soft.task.R
import com.soft.task.adapters.CategoriesAdapter
import com.soft.task.databinding.FragmentGenreBinding
import com.soft.task.databinding.FragmentSearchBinding
import com.soft.task.presentation.fragments.main.MainViewModel
import com.soft.task.utils.Status
import com.soft.task.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var query : String
    private lateinit var listAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.soft.task.R.layout.fragment_search, container, false)
        setupRecyclerView()
        handleSearch()
        val bundle = this.arguments
        if (bundle != null) {
            query = bundle.getString("query") as String
            binding.searchField.setText(query)
            viewModel.getSearchQuery(query)

        }



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSearchQuery.collect{ list ->

                    when(list.status)
                    {

                        Status.SUCCESS ->{

                            if (list.data?.results!!.isNotEmpty())
                            {
                                binding.progressBar.visibility=View.GONE

                                list.data.results.apply {
                                    listAdapter.updateDayListItems(this)

                                }


                            }

                        }
                        Status.ERROR ->{
                            binding.progressBar.visibility=View.GONE
                        }
                        Status.LOADING -> {

                            binding.progressBar.visibility=View.VISIBLE

                        }
                    }

                }
            }
        }


        return binding.root
    }

    private fun searchAction()
    {
        viewModel.getSearchQuery(binding.searchField.text.toString())
        binding.searchField.hideKeyboard()
    }
    private fun handleSearch()
    {
        binding.searchBtn.setOnClickListener {

           searchAction()
        }
        binding.searchField.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                searchAction()

                return@OnEditorActionListener true
            }
            false
        })
    }
    private fun setupRecyclerView()
    {
        binding.recyclerviewSearchResult.layoutManager = GridLayoutManager(context,2)
        listAdapter = CategoriesAdapter(arrayListOf()) {  movie ->

            val bundle = Bundle()
            bundle.putSerializable("movie", movie)

            findNavController().navigate(R.id.action_searchFragment_to_Movie_details,bundle)
        }
        binding.recyclerviewSearchResult.adapter =listAdapter


    }
}