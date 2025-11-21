package uz.ibrohim.nonkaboob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.ibrohim.nonkaboob.R
import uz.ibrohim.nonkaboob.models.CategoryModel

class CategoryAdapter(private val items: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    inner class CategoryVH(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val imgIcon: ImageView = view.findViewById(R.id.imgIcon)
        val rvProducts: RecyclerView = view.findViewById(R.id.rvProducts)
        val txtTotal: TextView = view.findViewById(R.id.txtTotal)
        val card: LinearLayout = view.findViewById(R.id.cardCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_category, parent, false)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val item = items[position]

        holder.txtTitle.text = item.title
        holder.imgIcon.setImageResource(item.iconRes)
        holder.card.setBackgroundColor(item.color)
        holder.txtTotal.setTextColor(item.color)

        // Inner Recycler
        holder.rvProducts.adapter = ProductAdapter(item.products)
        holder.rvProducts.layoutManager =
            GridLayoutManager(holder.itemView.context, 4, GridLayoutManager.HORIZONTAL, false)

//        holder.rvProducts.layoutManager = LinearLayoutManager(holder.itemView.context)

        // Total
        val total = item.products.sumOf { it.price }
        holder.txtTotal.text = "${total} so'm"
    }

    override fun getItemCount(): Int = items.size
}