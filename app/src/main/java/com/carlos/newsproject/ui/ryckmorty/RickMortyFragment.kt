package com.carlos.newsproject.ui.ryckmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carlos.newsproject.databinding.FragmentRickMortyBinding
import com.carlos.newsproject.ui.ryckmorty.adapter.RyckMortyAdapter
import com.carlos.newsproject.ui.ryckmorty.model.RickMortyViewModel
import com.carlos.newsproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickMortyFragment : Fragment() {

    private lateinit var binding: FragmentRickMortyBinding
    private val rickMortyViewModel: RickMortyViewModel by viewModels()
    private val adapter:RyckMortyAdapter = RyckMortyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRickMortyBinding.inflate(inflater,container,false)
        setRecycler()
        launchCharacter()
        return binding.root
    }

    private fun setRecycler(){
        binding.rvCharacter.adapter = adapter
    }

    private fun launchCharacter(){
        rickMortyViewModel.responseCharacters.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addItems(it.data?.results)
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}