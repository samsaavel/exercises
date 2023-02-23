package com.greenv.feb14.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.greenv.feb14.databinding.FragmentSecondBinding
import com.greenv.feb14.service.ForegroundExample

class ServiceFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(requireActivity(), ForegroundExample::class.java)

        val intentBind = Intent(requireActivity(), ForegroundExample::class.java)
        binding.buttonStart.setOnClickListener {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().startService(intent)
            ContextCompat.startForegroundService(requireActivity(), intent)
        }
        binding.buttonStop.setOnClickListener {
            requireActivity().stopService(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}