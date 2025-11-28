package uz.ibrohim.nonkaboob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.ibrohim.nonkaboob.adapter.ProductCategoryAdapter
import uz.ibrohim.nonkaboob.adapter.ProductsAdapter
import uz.ibrohim.nonkaboob.databinding.FragmentHomeBinding
import uz.ibrohim.nonkaboob.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: ProductCategoryAdapter
    private lateinit var productAdapter: ProductsAdapter

    private val viewModel: ProductsViewModel by viewModels()

    private val categoryList = arrayListOf(
        CategoryItem("Fast-Food", true),
        CategoryItem("Ichimliklar"),
        CategoryItem("Dessertlar")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}