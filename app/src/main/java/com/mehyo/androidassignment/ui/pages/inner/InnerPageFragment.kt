package com.mehyo.androidassignment.ui.pages.inner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.mehyo.androidassignment.databinding.FragmentInnerPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class InnerPageFragment : Fragment() {

    private var _binding: FragmentInnerPageBinding? = null
    private val binding get() = _binding!!
    private val args: InnerPageFragmentArgs by navArgs()
    private val innerPageViewModel:InnerPageViewModel by viewModel()
    private lateinit var link:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentInnerPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting Rocket details by ID from the viewModel by providing the ID from the args
        innerPageViewModel.getRocketByID(args.rocketID)

        //observing changes on the livedata and filling the Views with the received rocketItem data
        innerPageViewModel.rocketItemLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                val rocket=data.body()
                binding.tvRocketName.text=rocket?.name.toString()
                binding.tvRocketDate.text=rocket?.firstFlight.toString()
                binding.tvRocketDesc.text=rocket?.description.toString()
                binding.imageView.load(rocket?.flickrImages?.get(0))
                link=rocket?.wikipedia.toString()
            }
        }
        //open wikipedia
        binding.btnRead.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link))) }
        //share the wikipedia link
        binding.btnShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, link)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        //close the innerPage and return to the mainPage
        binding.btnClose.setOnClickListener {
            findNavController().navigate(InnerPageFragmentDirections.actionInnerPageFragmentToMainPageFragment())
        }
    }

    //set viewBinding to null when fragment is destroyed to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}