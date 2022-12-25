package com.soft.task.presentation.fragments.movie_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.soft.task.R
import com.soft.task.adapters.CategoriesAdapter
import com.soft.task.adapters.GenreViewPagerAdapter
import com.soft.task.databinding.FragmentMainBinding
import com.soft.task.databinding.FragmentMovieDetailsBinding
import com.soft.task.domain.models.Genre
import com.soft.task.domain.models.Movie
import com.soft.task.domain.repository.MainRepo
import com.soft.task.presentation.fragments.CategoryFragment
import com.soft.task.presentation.fragments.main.MainViewModel
import com.soft.task.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter : CategoriesAdapter
    private lateinit var gAdapter : GenreViewPagerAdapter
  //  private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.soft.task.R.layout.fragment_movie_details, container, false)



        val bundle = this.arguments
        if (bundle!!.getSerializable("movie") != null) {
           val movie = bundle.getSerializable("movie") as Movie
            binding.movieDetails=movie
            (requireActivity() as AppCompatActivity).supportActionBar?.title = movie.title
            binding.ratingBar.rating= movie.vote_average!!.toFloat()
            binding.raingReviews.text=getString(R.string.reviews)+" ${movie.vote_count}"
        }


        

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }





}