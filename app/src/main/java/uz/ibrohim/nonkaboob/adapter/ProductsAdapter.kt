package uz.ibrohim.nonkaboob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ibrohim.nonkaboob.databinding.ItemProductBinding
import uz.ibrohim.nonkaboob.models.ProductItem

class ProductsAdapter(
    private val list: ArrayList<ProductItem>,
    private val onClick: (ProductItem) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.VH>() {

    inner class VH(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem: ProductItem) {
            binding.txtName.text = productItem.name
            binding.txtCount.text = productItem.count
            binding.txtPrice.text = "${productItem.price} so'm"

            Glide.with(itemView.context)
                .load(productItem.image)
                .into(binding.productImage)

            itemView.setOnClickListener { onClick(productItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    fun setData(newList: List<ProductItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}