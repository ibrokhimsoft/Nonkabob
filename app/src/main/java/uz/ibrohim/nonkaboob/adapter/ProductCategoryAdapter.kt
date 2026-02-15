package uz.ibrohim.nonkaboob.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ibrohim.nonkaboob.R
import uz.ibrohim.nonkaboob.databinding.ItemCategoryBinding
import uz.ibrohim.nonkaboob.models.CategoryItem

class ProductCategoryAdapter(
    private val list: ArrayList<CategoryItem>,
    private val onClick: (CategoryItem) -> Unit
) : RecyclerView.Adapter<ProductCategoryAdapter.VH>() {

    inner class VH(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryItem) {
            binding.txtTitle.text = item.name
            binding.imgIcon.setImageResource(item.icon)

            // 🔥 Selected bo'lsa background + text color o'zgaradi
            if (item.selected) {
                binding.root.setBackgroundResource(R.drawable.bg_category_selected)
                binding.txtTitle.setTextColor(Color.WHITE)
            } else {
                binding.root.setBackgroundResource(R.drawable.bg_category_unselected)
                binding.txtTitle.setTextColor(Color.BLACK)
            }

            itemView.setOnClickListener { onClick(item) }
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