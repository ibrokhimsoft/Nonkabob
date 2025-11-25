package uz.ibrohim.nonkaboob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ibrohim.nonkaboob.databinding.ItemProductBinding
import uz.ibrohim.nonkaboob.models.Product

class ProductsAdapter(
    private val list: ArrayList<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.VH>() {

    inner class VH(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.txtName.text = product.name
            binding.txtPrice.text = "${product.price} so'm"

            // Rasm yuklash (Glide)
            Glide.with(binding.imgProduct.context)
                .load(product.image)
                .into(binding.imgProduct)

            itemView.setOnClickListener { onClick(product) }
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

    override fun getItemCount() = list.size
}