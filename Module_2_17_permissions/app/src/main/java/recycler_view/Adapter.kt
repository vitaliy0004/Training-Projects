package recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_2_17_permissions.databinding.ItemPhotoBinding
import data.Fail

class Adapter (): RecyclerView.Adapter<PhotoViewHolder>(){
    private var valuesBd: List<Fail> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context))
        return PhotoViewHolder(binding)
    }
    fun setData(newData: List<Fail>){
        this.valuesBd = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int= valuesBd.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.viewItem(valuesBd[position])
    }
}
class PhotoViewHolder (
    val binding: ItemPhotoBinding
): RecyclerView.ViewHolder(binding.root){

    fun viewItem (photosOfSights: Fail){
        binding.data.text = photosOfSights.data
        Glide.with(binding.photo)
            .load(photosOfSights.fail)
            .into(binding.photo)
    }

}