package presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import data.PhotosDto
import com.example.module_2_16_nasa.R
import com.example.module_2_16_nasa.databinding.FragmentListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels{ Factory() }
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

    private fun onItemClick(item: PhotosDto){
        val bundle = Bundle().apply {
            putString("photo_info",item.img_src)
        }
        findNavController().navigate(R.id.photoFragment,bundle)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}