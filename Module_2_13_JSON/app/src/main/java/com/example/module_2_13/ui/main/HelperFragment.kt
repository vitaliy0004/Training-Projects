package com.example.module_2_13.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.module_2_13_JSON.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class HelperFragment : Fragment() {

    companion object {
        fun newInstance() = HelperFragment()
    }


    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!
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


        if (binding.name.text.toString() == "HelperFragment" && viewModel.control == 0) { viewModel.user() }

        binding.talk.setOnClickListener {
            viewModel.user()

        }


        viewLifecycleOwner.lifecycleScope
            .launch {
                viewModel.state
                    .collect { state ->
                        if(state == State.Success ) {
                            val response = RetrofitInstance().userImegApi.getUser().results[0]
                            binding.name.text = viewModel.name
                            binding.email.text = viewModel.email
                            binding.age.text = viewModel.age
                            binding.location.text = viewModel.location
                            binding.gender.text = viewModel.gender
                            Glide.with(context!!)
                                .load(viewModel.picture)
                                .centerCrop()
                                .into(binding.imageView)
                        }
                    }
            }
    }


}
