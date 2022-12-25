package com.soft.task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soft.task.databinding.RowMovieBinding
import com.soft.task.domain.models.Movie

import com.soft.task.utils.UserDiffCallback
import javax.inject.Inject


class CategoriesAdapter @Inject constructor (var movies: ArrayList<Movie>,val itemClick: (Movie) -> Unit) :  RecyclerView.Adapter<CategoriesAdapter.SearchViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    private lateinit var binding: RowMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)

    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)


    }


    fun updateDayListItems(newMovies: List<Movie>) {

        val diffCallback = UserDiffCallback(this.movies, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.movies.clear()
        this.movies.addAll(newMovies)
        diffResult.dispatchUpdatesTo(this)


    }


    inner class SearchViewHolder(private val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(propertyDetails: Movie) {
            binding.item = propertyDetails


        }

        init {

            binding.root.setOnClickListener {


                itemClick.invoke(movies[adapterPosition])
            }

        }


        }
    }




