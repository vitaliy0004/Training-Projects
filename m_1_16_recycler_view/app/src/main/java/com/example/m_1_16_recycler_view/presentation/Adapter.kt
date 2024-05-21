package com.example.m_1_16_recycler_view.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m_1_16_recycler_view.databinding.ItemPhotoSputnikBinding


class Adapter(private val onClick: (com.example.m_1_16_recycler_view.data.PhotosDto) -> Unit) :
    PagingDataAdapter<com.example.m_1_16_recycler_view.data.PhotosDto, MyViewHolder>(
        DiffUtilCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPhotoSputnikBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            sol.text = item?.sol.toString()
            date.text = "Дата: ${item?.earthDate}"
            rover.text = item?.rover?.name
            camera.text = item?.camera?.name

            item?.let {
                Glide
                    .with(photoSputnik.context)
                    .load(it.img_src)
                    .into(photoSputnik)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let { onClick(item) }
        }

    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<com.example.m_1_16_recycler_view.data.PhotosDto>() {
    override fun areContentsTheSame(
        oldItem: com.example.m_1_16_recycler_view.data.PhotosDto,
        newItem: com.example.m_1_16_recycler_view.data.PhotosDto
    ): Boolean = oldItem.id == newItem.id


    override fun areItemsTheSame(
        oldItem: com.example.m_1_16_recycler_view.data.PhotosDto,
        newItem: com.example.m_1_16_recycler_view.data.PhotosDto
    ): Boolean = oldItem == newItem
}

class MyViewHolder(val binding: ItemPhotoSputnikBinding) : RecyclerView.ViewHolder(binding.root)