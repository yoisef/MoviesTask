package com.soft.task.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.soft.task.adapters.CategoriesAdapter
import com.soft.task.adapters.GenreViewPagerAdapter
import com.soft.task.databinding.FragmentMainBinding
import com.soft.task.domain.repository.MainRepo
import com.soft.task.presentation.fragments.CategoryFragment
import com.soft.task.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var gAdapter : GenreViewPagerAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.soft.task.R.layout.fragment_main, container, false)


        initialization()
        observeInsertionState()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun observeInsertionState()
    {
        /*
        collect data from SharedStateFlow to handle Single Events by param (reply=0) inside
        lifecycle coroutine scope with 'launchWhenStarted' to make flow aware with fragment lifecycle
         */
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // This happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.getGenres.collect{

                        genres ->
                    when(genres.status)
                    {
                        Status.SUCCESS ->{

                            val tabsNames= arrayListOf<String>()
                            if (genres.data?.genres!!.isNotEmpty())
                            {
                                genres.data.genres.size.let {
                                    for (i in 0 until it)
                                    {

                                        val fragmentObject=CategoryFragment()
                                        val bundle = Bundle()
                                        bundle.putSerializable("genre", genres.data?.genres[i])
                                        fragmentObject.arguments=bundle

                                        gAdapter.addFragment(fragmentObject)
                                        tabsNames.add(genres.data?.genres[i].name)

                                    }
                                    setupViewPager(tabsNames)
                                }


                            }


                        }
                        Status.ERROR ->{

                        }
                        Status.LOADING -> {


                        }
                    }

                }
            }
        }



    }

    private fun initialization()
    {
        viewModel.getGenre()

        gAdapter = GenreViewPagerAdapter(fragmentManager = childFragmentManager, lifecycle)


        // add Fragments in your ViewPagerFragmentAdapter class

        // add Fragments in your ViewPagerFragmentAdapter class


        // set Orientation in your ViewPager2

        // set Orientation in your ViewPager2

    }

    private fun setupViewPager(names :ArrayList<String>) {

        binding.tabDots.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab1: TabLayout.Tab) {
                tab1.select()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        binding.tabDots.tabMode = TabLayout.MODE_SCROLLABLE


        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.tabDots.layoutDirection = View.LAYOUT_DIRECTION_LTR

        binding.pager.adapter = gAdapter

        TabLayoutMediator(binding.tabDots, binding.pager) { tab, position ->
            tab.text = names[position]
        }.attach()
    }


}