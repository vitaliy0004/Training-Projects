package com.example.m_1_14_room.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m_1_14_room.R
import com.example.m_1_14_room.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class HelperFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (requireActivity().application as App).db.wordsDao()
                return MainViewModel(wordDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            val text = binding.editText.text.toString()
            val regex = Regex("[^a-zA-Zа-яА-Я^-]+")
            val isPattern = !regex.containsMatchIn(text)
            if (isPattern && text != "") {
                viewModel.addButton(text)
            } else {
                binding.message.text = getString(R.string.error)

            }
        }

        binding.delete.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.onDelete()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allWords.collect {

                binding.message.text = it.joinToString(separator = "\n")

            }
        }
    }

}