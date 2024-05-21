package presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modile_2_3_paging_library.databinding.ItemPersonBinding
import data.characters.ResultDto
import data.characters.RickAndMortyDto

class Adapter(
    // private val onClick: (ResultDto) -> Unit
): PagingDataAdapter<ResultDto,Adapter.ViewHolder>(DiffUtilCallback()){
    inner class ViewHolder(val binding: ItemPersonBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with (holder.binding){
            item.let {
                Glide.with(image.context)
                    .load(it?.image)
                    .into(image)
                name.text = it?.name
                rasa.text = it?.status
                earth.text = it?.location?.name
            }
        }
        //holder.binding.root.setOnClickListener { onClick(item!!) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}
class DiffUtilCallback:DiffUtil.ItemCallback<ResultDto>(){
    override fun areItemsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean = oldItem == newItem
}