package com.greenv.feb14.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenv.feb14.R
import com.greenv.feb14.databinding.FragmentFirstBinding
import com.greenv.feb14.ui.RamIntent
import com.greenv.feb14.ui.RamState
import com.greenv.feb14.ui.recycler.RamAdapter
import com.greenv.feb14.ui.viewmodel.RamViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RaMFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var ramViewModel: RamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ramViewModel = ViewModelProvider(this).get(RamViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ramRecycler.layoutManager = LinearLayoutManager(context)
        observeViewModel()
//        fetchRamCharacters()//
    }

//    private fun fetchRamCharacters() {
//        ramViewModel.fetchCharacters()
//    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            ramViewModel.ramState.collectLatest { state ->
                when (state) {
                    RamState.Failure -> {
                        Log.e("******RamFragment", "Failed to load data")
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Failed to load data", Toast.LENGTH_SHORT).show()
                    }

                    RamState.Loading -> {
                        Log.d("******RamFragment", "Loading data")
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    RamState.None -> {
                        Log.d("******RamFragment", "None state")
                        ramViewModel.handleFetchCharacters(RamIntent.getCharactersIntent)
                    }

                    is RamState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val response = state.characters.results
                        binding.ramRecycler.adapter =
                            RamAdapter(response)
                        Log.d("******RamFragment", "observeViewModel, setup adapter ")
                    }
                }
            }
        }
    }

    private fun setupRecycler() {
        val resId: Int = R.anim.animation_fall_down
        val animation = AnimationUtils.loadAnimation(context, resId)
        val controller = LayoutAnimationController(animation)
        binding.ramRecycler.animation.apply { animation }
        binding.ramRecycler.layoutManager.apply { controller.start() }
//        recyclerView?.animation.apply { animation }
//        recyclerView!!.layoutManager.apply { controller.start() }
        //binding.ramRecycler.adapter = ramAdapter
        //binding.ramRecycler.layoutManager = LinearLayoutManager(context)
    }
}