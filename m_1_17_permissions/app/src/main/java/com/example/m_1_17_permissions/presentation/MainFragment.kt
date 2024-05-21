package com.example.m_1_17_permissions.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.m_1_17_permissions.R
import com.example.m_1_17_permissions.data.App
import com.example.m_1_17_permissions.data.FailDao
import com.example.m_1_17_permissions.databinding.FragmentMainBinding
import com.example.m_1_17_permissions.recycler_view.Adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.recycler.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }
        binding.delete.setOnClickListener {
            viewModel.delete()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPhotosOfSights.collect {
                adapter.setData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}