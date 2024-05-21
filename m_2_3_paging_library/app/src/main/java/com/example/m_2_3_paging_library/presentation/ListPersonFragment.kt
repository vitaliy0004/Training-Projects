package com.example.m_2_3_paging_library.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.m_2_3_paging_library.data.characters.ResultDto
import com.example.m_2_3_paging_library.databinding.ListPersonFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListPersonFragment : Fragment() {
    @Inject
    lateinit var factory: Factory
    private val viewModel: MainViewModel by viewModels{ factory }
    private var _binding: ListPersonFragmentBinding? = null
    private val binding get() = _binding!!

    private val myAdapter = Adapter ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListPersonFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            myAdapter.refresh()
        }
        binding.recyclerView.adapter = myAdapter.withLoadStateFooter(Load())
        viewModel.pagingPerson.onEach {
            myAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> {
                        binding.restartText.visibility = View.INVISIBLE
                        binding.progressBar.visibility = View.VISIBLE
                        binding.button.visibility = View.INVISIBLE
                    }

                    State.Error -> {
                        binding.restartText.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.button.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.INVISIBLE
                    }

                    State.Success -> {
                        binding.restartText.visibility = View.INVISIBLE
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.button.visibility = View.INVISIBLE
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }

            }
        }


    }

}