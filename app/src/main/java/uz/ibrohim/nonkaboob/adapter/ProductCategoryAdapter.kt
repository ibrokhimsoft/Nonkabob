package uz.ibrohim.nonkaboob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ibrohim.nonkaboob.databinding.ItemCategoryBinding
import uz.ibrohim.nonkaboob.models.Category

class ProductCategoryAdapter(
    private val list: ArrayList<Category>,
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<ProductCategoryAdapter.VH>() {

    inner class VH(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.txtTitle.text = category.name
            binding.imgIcon.setImageResource(category.icon)

            itemView.setOnClickListener { onClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}