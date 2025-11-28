package uz.ibrohim.nonkaboob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ibrohim.nonkaboob.databinding.ItemCategoryBinding
import uz.ibrohim.nonkaboob.models.CategoryItem

class ProductCategoryAdapter(
    private val list: ArrayList<CategoryItem>,
    private val onClick: (CategoryItem) -> Unit
) : RecyclerView.Adapter<ProductCategoryAdapter.VH>() {

    inner class VH(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryItem: CategoryItem) {
            binding.txtTitle.text = categoryItem.name
            binding.imgIcon.setImageResource(categoryItem.icon)

            itemView.setOnClickListener { onClick(categoryItem) }
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