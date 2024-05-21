package com.example.m_2_7_presentation_patterns.presentation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.m_2_7_presentation_patterns.R
import com.example.m_2_7_presentation_patterns.databinding.FragmentSearchBinding
import com.example.m_2_7_presentation_patterns.presentation.ViewModuleSearch


class FragmentSearch : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModuleSearch by viewModels()
    private lateinit var getPrefers: SharedPreferences

    private lateinit var prefrs: SharedPreferences.Editor
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        prefrs = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)?.edit()!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPrefers = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)!!
        adapter()
        binding.image.setOnClickListener {

            prefrs.putString(LAST_CITY, binding.inputText.text.toString()).apply()
            prefrs.putInt(SECOND_FRAGMENT, 1).apply()
            findNavController().navigate(R.id.action_search_to_fragmentWeather)

        }
        binding.inputText.setOnClickListener {
            adapter()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter()
    }

    private fun adapter() {
        if (binding.inputText.text?.length == 0) {
            viewModel.lastCity = getPrefers.getStringSet(LIST_CITY, mutableSetOf<String>())!!
            if (viewModel.lastCity.isEmpty()) {
                binding.text.visibility = View.VISIBLE
                binding.city.visibility = View.INVISIBLE
            } else {
                adapter = viewModel.adapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    viewModel.lastCity.toList()
                )
                binding.city.adapter = adapter
                clickCity(viewModel.lastCity.toList())
            }
        } else {
            binding.city.visibility = View.VISIBLE
            binding.text.visibility = View.INVISIBLE
            viewModel.filter(binding.inputText.text.toString())
            adapter = viewModel.adapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                viewModel.filter.toList()
            )
            binding.city.adapter = adapter
            clickCity(viewModel.filter)
        }
    }

    private fun clickCity(listCity: List<String>) {
        binding.city.setOnItemClickListener { adapterView, view, i, l ->
            prefrs.putString(LAST_CITY, listCity[i]).apply()
            prefrs.putInt(SECOND_FRAGMENT, 1).apply()
            findNavController().navigate(R.id.action_search_to_fragmentWeather)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

const val SECOND_FRAGMENT = "second Fragment"
const val LAST_CITY = "last city"
const val GET_CITY = "get city"
const val LIST_CITY = "list city"
const val ALL_CITY = "all city"
