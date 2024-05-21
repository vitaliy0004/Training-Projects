package presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.PhotosDto
import com.example.module_2_16_nasa.databinding.ItemPhotoSputnikBinding



class Adapter(private val onClick:(PhotosDto) -> Unit): PagingDataAdapter<PhotosDto, MyViewHolder>(
    DiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPhotoSputnikBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            sol.text = item?.sol.toString()
            date.text = "Дата: ${item?.earthDate}"
            rover.text = item?.rover?.name
            camera.text = item?.camera?.name

            item?.let{
                Glide
                    .with(photoSputnik.context)
                    .load(it.img_src)
                    .into(photoSputnik)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let{onClick(item)}
        }

    }
}
class  DiffUtilCallback: DiffUtil.ItemCallback<PhotosDto>(){
    override fun areContentsTheSame(oldItem: PhotosDto, newItem: PhotosDto): Boolean = oldItem.id == newItem.id


    override fun areItemsTheSame(oldItem: PhotosDto, newItem: PhotosDto): Boolean = oldItem == newItem
}

class MyViewHolder(val binding: ItemPhotoSputnikBinding):RecyclerView.ViewHolder(binding.root)