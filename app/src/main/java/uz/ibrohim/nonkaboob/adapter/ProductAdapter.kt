package uz.ibrohim.nonkaboob.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.ibrohim.nonkaboob.R
import uz.ibrohim.nonkaboob.models.ProductModel

class ProductAdapter(private val items: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    inner class ProductVH(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtCount: TextView = view.findViewById(R.id.txtCount)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val card: RelativeLayout = view.findViewById(R.id.iconBackground)
        val icons: ImageView = view.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_product, parent, false)
        return ProductVH(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val item = items[position]
        holder.txtName.text = item.name
        holder.card.setBackgroundColor(item.iconBackground)
        holder.icons.setImageResource(item.icon)
        holder.txtPrice.setTextColor(item.textColor)
        holder.txtCount.text =  "${item.quantity} dona"
        holder.txtPrice.text = "${item.price} so'm"
    }

    override fun getItemCount(): Int = items.size
}