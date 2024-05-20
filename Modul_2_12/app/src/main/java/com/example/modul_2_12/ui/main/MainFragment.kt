package com.example.modul_2_12.ui.main

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.modul_2_12.R
import com.example.modul_2_12.databinding.ActivityMainBinding
import com.example.modul_2_12.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private var text =""
private var defoltText = ""
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var  binding: FragmentMainBinding
    private  val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchInputEditText.doOnTextChanged { text, start, before, count ->
            if (!binding.progressBar.isVisible) viewModel.enabled(binding.searchInputEditText.text?.length!!, binding.search)
        }

        binding.search.setOnClickListener {
            text = binding.searchInputEditText.text.toString()
            viewModel.search(binding.search,binding.progressBar,binding.text,binding.searchInputLayout)

             }
       viewLifecycleOwner.lifecycleScope
            .launch {
                viewModel.state
                    .collect{state->
                        when(state){
                            State.Loadin->{binding.search.isEnabled = false
                                binding.progressBar.isVisible = true

                            }
                            State.Success->{

                               if (binding.searchInputEditText.text?.length!! >= 3) binding.search.isEnabled = true
                                if (text != "" ){
                                    binding.text.text ="По запросу $text ничего не найдено"
                                    defoltText =binding.text.text.toString()
                                }
                                binding.progressBar.isVisible = false
                            }
                        }
                    }
            }
        //для сахранения прошлого запроса
        if (binding.progressBar.isVisible) if (defoltText != "") binding.text.text = defoltText

    }

}