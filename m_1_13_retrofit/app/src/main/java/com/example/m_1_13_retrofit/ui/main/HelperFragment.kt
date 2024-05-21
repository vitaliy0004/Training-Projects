package com.example.m_1_13_retrofit.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.m_1_13_retrofit.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class HelperFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (binding.name.text.toString() == "HelperFragment" && viewModel.control == 0) {
            viewModel.user()
        }

        binding.talk.setOnClickListener {
            viewModel.user()

        }


        viewLifecycleOwner.lifecycleScope
            .launch {
                viewModel.state
                    .collect { state ->
                        if (state == State.Success) {
                            RetrofitInstance().userImageApi.getUser().results[0]
                            binding.name.text = viewModel.name
                            binding.email.text = viewModel.email
                            binding.age.text = viewModel.age
                            binding.location.text = viewModel.location
                            binding.gender.text = viewModel.gender
                            Glide.with(requireContext())
                                .load(viewModel.picture)
                                .centerCrop()
                                .into(binding.imageView)
                        }
                    }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
