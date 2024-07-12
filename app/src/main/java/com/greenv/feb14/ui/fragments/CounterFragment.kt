package com.greenv.feb14.ui.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.greenv.feb14.databinding.FragmentSecondBinding
import com.greenv.feb14.service.CounterService

class CounterFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private var isServiceRunning = false
    private lateinit var serviceCounter: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        serviceCounter = binding.serviceCounter
        btnStart = binding.buttonStart
        btnStop = binding.buttonStop
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnStart.setOnClickListener {
            startCounterService()
        }
        btnStop.setOnClickListener {
            stopCounterService()
        }
    }

    private fun startCounterService() {
        if (!isServiceRunning) {
            val intent = Intent(requireContext(), CounterService::class.java)
            intent.action = CounterService.CounterAction.START.name
            requireContext().startService(intent)
            requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            isServiceRunning = true
        }
    }

    private fun stopCounterService() {
        if (isServiceRunning) {
            val intent = Intent(requireContext(), CounterService::class.java)
            intent.action = CounterService.CounterAction.STOP.name
            requireContext().startService(intent)
            requireContext().unbindService(serviceConnection)
            isServiceRunning = false
        }
    }
}