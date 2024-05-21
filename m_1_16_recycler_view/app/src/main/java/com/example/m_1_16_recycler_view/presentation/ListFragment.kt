package com.example.m_1_16_recycler_view.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.m_1_16_recycler_view.R
import com.example.m_1_16_recycler_view.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: Factory
    private val viewModel: MainViewModel by viewModels { factory }
    private val myAdapter = Adapter { photo -> onItemClick(photo) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.adapter = myAdapter.withLoadStateFooter(MyLoadStateAdapter())
        viewModel.pagingPhotos.onEach {
            myAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onItemClick(item: com.example.m_1_16_recycler_view.data.PhotosDto) {
        val bundle = Bundle().apply {
            putString("photo_info", item.img_src)
        }
        findNavController().navigate(R.id.photoFragment, bundle)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}