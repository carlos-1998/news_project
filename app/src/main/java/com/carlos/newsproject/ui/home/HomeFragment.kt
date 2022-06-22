package com.carlos.newsproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.carlos.newsproject.databinding.FragmentHomeBinding
import com.carlos.newsproject.ui.home.adapter.HomeAdapter
import com.carlos.newsproject.ui.home.viewmodel.HomeViewModel
import com.carlos.newsproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val adaterHomeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        setRecycler()
        launchNews()
        return binding.root
    }

    private fun setRecycler(){
        binding.recyclerNotice.adapter = adaterHomeAdapter
    }

    private fun launchNews(){
        homeViewModel.responseNews.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    adaterHomeAdapter.arrayList.clear()
                    adaterHomeAdapter.addItems(it.data?.articles)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}