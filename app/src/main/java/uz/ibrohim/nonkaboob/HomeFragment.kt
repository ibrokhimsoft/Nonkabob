package uz.ibrohim.nonkaboob

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.ibrohim.nonkaboob.adapter.CategoryAdapter
import uz.ibrohim.nonkaboob.databinding.FragmentHomeBinding
import uz.ibrohim.nonkaboob.models.CategoryModel
import uz.ibrohim.nonkaboob.models.ProductModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvCategories.layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            rvCategories.adapter = CategoryAdapter(sampleData())

            rvCategories.adapter = CategoryAdapter(sampleData())
        }

    }

    @SuppressLint("UseKtx")
    private fun sampleData(): List<CategoryModel> {

        val drinks = listOf(
            ProductModel(
                "Coca Cola", 45, 135000,
                Color.parseColor("#DBEAFE"),
                R.drawable.ic_drink, Color.parseColor("#2E70EF")
            ),
            ProductModel(
                "Fanta", 38, 114000,
                Color.parseColor("#DBEAFE"),
                R.drawable.ic_drink, Color.parseColor("#2E70EF")
            ),
            ProductModel(
                "Sprite", 52, 156000,
                Color.parseColor("#DBEAFE"),
                R.drawable.ic_drink, Color.parseColor("#2E70EF")
            ),
            ProductModel(
                "Kofe", 29, 145000,
                Color.parseColor("#DBEAFE"),
                R.drawable.ic_drink, Color.parseColor("#2E70EF")
            ),
            ProductModel(
                "Pepsi", 32, 180000,
                Color.parseColor("#DBEAFE"),
                R.drawable.ic_drink, Color.parseColor("#2E70EF")
            ),
        )

        val fastFood = listOf(
            ProductModel(
                "Burger", 67, 670000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),
            ProductModel(
                "Pizza", 43, 860000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),
            ProductModel(
                "Tovuq", 58, 522000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),
            ProductModel(
                "Hot Dog", 35, 280000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),

            ProductModel(
                "Nonkabob", 38, 320000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),

            ProductModel(
                "Nonburger", 42, 500000,
                Color.parseColor("#FFEDD5"),
                R.drawable.ic_burger, Color.parseColor("#F56C14")
            ),
        )

        val sweets = listOf(
            ProductModel(
                "Muzqaymoq", 41, 205000,
                Color.parseColor("#FCE7F3"),
                R.drawable.ic_ice_cream, Color.parseColor("#E43989")
            ),
            ProductModel(
                "Tort", 18, 360000,
                Color.parseColor("#FCE7F3"),
                R.drawable.ic_ice_cream, Color.parseColor("#E43989")
            ),
        )

        return listOf(
            CategoryModel("Ichimliklar", R.drawable.ic_drink, Color.parseColor("#2E70EF"), drinks),
            CategoryModel("Fast-Food", R.drawable.ic_burger, Color.parseColor("#F56C14"), fastFood),
            CategoryModel(
                "Shirinliklar",
                R.drawable.ic_ice_cream,
                Color.parseColor("#E43989"),
                sweets
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}