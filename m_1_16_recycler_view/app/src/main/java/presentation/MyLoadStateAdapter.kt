package presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_2_16_nasa.databinding.ProgresBinding

class MyLoadStateAdapter: LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) = Unit

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ProgresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  LoadStateViewHolder(binding)
    }
}
class LoadStateViewHolder(binding: ProgresBinding): RecyclerView.ViewHolder(binding.root)