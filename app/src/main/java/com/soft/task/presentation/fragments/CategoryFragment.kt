package com.soft.task.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soft.task.R
import com.soft.task.adapters.CategoriesAdapter
import com.soft.task.databinding.FragmentGenreBinding
import com.soft.task.domain.models.Genre
import com.soft.task.presentation.fragments.main.MainFragmentDirections
import com.soft.task.presentation.fragments.main.MainViewModel
import com.soft.task.presentation.fragments.movie_details.MovieDetailsFragment
import com.soft.task.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentGenreBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var genreObject : Genre
    private lateinit var genreAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.soft.task.R.layout.fragment_genre, container, false)
        setupRecyclerView()

        val bundle = this.arguments
        if (bundle != null) {
            genreObject = bundle.getSerializable("genre") as Genre
        }

        viewModel.getGenreList(genreObject.id.toString())

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getGenreList.collect{

                        data ->

                    when(data.status)
                    {
                        Status.SUCCESS ->{

                            genreAdapter.updateDayListItems(data.data!!.results)


                        }
                        Status.LOADING -> {

                        }

                        Status.ERROR -> {

                        }

                    }
                }

            }


            }

        return binding.root
    }

    private fun setupRecyclerView()
    {
        binding.recyclerGenreList.layoutManager = GridLayoutManager(context,2)
        genreAdapter = CategoriesAdapter(arrayListOf()) {  movie ->
           // val navController = Navigation.findNavController(requireView())
           // Log.e("object","="+movie.toString())
           // val action = CategoryFragmentDirections.actionCategoryFragmentToMovieDetails(movie)
        //    bundle.putParcelable("movie",movie)
            val bundle = Bundle()
            val ge=Genre(id=8, name = "yousssef")
            bundle.putSerializable("movie", movie)
          //  val frag=MovieDetailsFragment()
          //  frag.arguments=bundle


          //  findNavController().navigateUp()
         //val action=   MainFragmentDirections.actionMainToMovieDetails(movie)
            findNavController().navigate(R.id.action_main_to_Movie_details,bundle)
        }
        binding.recyclerGenreList.adapter =genreAdapter


    }
}