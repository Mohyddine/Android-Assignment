package com.mehyo.androidassignment.ui.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehyo.androidassignment.databinding.FragmentMainPageBinding
import com.mehyo.androidassignment.ui.other.LaunchesAdapter
import com.mehyo.androidassignment.ui.other.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var launchesAdapter: LaunchesAdapter
    private val mainPageViewModel: MainPageViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initializing the launchesAdapter sending ItemClickListener as an argument
        launchesAdapter = LaunchesAdapter(this@MainPageFragment)

        //observing changes on the livedata sending the data to the adapter
        //hiding the progressBar
        mainPageViewModel.launchesLiveData.observe(viewLifecycleOwner) { launchesList ->
            if (launchesList != null) {
                launchesAdapter.setData(launchesList)
                binding.progressBar.visibility = View.GONE
            }
        }
        //initializing the recyclerview
        binding.rvLaunches.apply {
            adapter = launchesAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    //set viewBinding to null when fragment is destroyed to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //after clicking an item in recyclerview the user will be navigated to the innerPage
    //and sending the rocketID as an argument
    override fun onItemClicked(rocketId: String) {
        findNavController().navigate(
            MainPageFragmentDirections.actionMainPageFragmentToInnerPageFragment(
                rocketId
            )
        )
    }
}