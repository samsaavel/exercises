package com.greenv.feb14.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenv.feb14.databinding.FragmentFirstBinding
import com.greenv.feb14.response.ResultObject
import com.greenv.feb14.ui.RamState
import com.greenv.feb14.ui.viewmodel.RamViewModel
import com.greenv.feb14.ui.recycler.RamAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RaMFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val ramAdapter by lazy { RamAdapter() }
    private val ramViewModel by lazy { RamViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeViewModel()
        fetchRamCharacters()
    }

    private fun fetchRamCharacters() {
        ramViewModel.fetchCharacters()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ramViewModel.ramState.collectLatest { state ->
                    when (state) {
                        RamState.Failure -> {}
                        RamState.Loading -> {}
                        RamState.None -> {}
                        is RamState.Success -> handleRamCharactersSuccess(state.characters.results)
                    }
                }
            }
        }
    }

    private fun handleRamCharactersSuccess(charactersList: List<ResultObject>) {
        ramAdapter.addRamList(charactersList)
    }

    private fun setupRecycler() {
        binding.ramRecycler.adapter = ramAdapter
        binding.ramRecycler.layoutManager = LinearLayoutManager(context)
    }
}