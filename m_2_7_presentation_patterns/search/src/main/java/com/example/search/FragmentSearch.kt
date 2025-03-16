package com.example.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.aconst.AppConstants
import com.example.aconst.AppConstants.GET_CITY
import com.example.aconst.AppConstants.LAST_CITY
import com.example.aconst.AppConstants.LIST_CITY
import com.example.core.navigation.NavigationRouter
import com.example.core.navigation.SomeViewModel
import com.example.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSearch : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModuleSearch by viewModels()
    private val someViewModel: SomeViewModel by activityViewModels()
    private lateinit var getPrefers: SharedPreferences

    @Inject
    lateinit var navRouter: NavigationRouter
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
        navRouter.setViewModel(someViewModel)
        adapter()
        binding.image.setOnClickListener {
            prefrs.putString(LAST_CITY, binding.inputText.text.toString()).apply()
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
            navRouter.openCoreFlow(
                AppConstants.SEARCH_TAB,
                com.example.core.R.navigation.navigation_core
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
