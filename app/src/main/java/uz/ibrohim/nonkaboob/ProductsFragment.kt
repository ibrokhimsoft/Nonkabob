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
import uz.ibrohim.nonkaboob.models.CategoryItem
import uz.ibrohim.nonkaboob.products.AddProductDialog
import uz.ibrohim.nonkaboob.products.ProductsViewModel

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: ProductCategoryAdapter
    private lateinit var productAdapter: ProductsAdapter

    private val viewModel: ProductsViewModel by viewModels()

    private val categoryList = arrayListOf(
        CategoryItem(0,"Fast-Food", R.drawable.ic_burger,true),
        CategoryItem(1,"Ichimliklar", R.drawable.ic_drink),
        CategoryItem(2,"Shirinliklar", R.drawable.ic_ice_cream)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCategoryRv()
        setupProductsRv()
        observeProducts()

        binding.productAdd.setOnClickListener {
            openAddProductDialog()
        }
    }

    private fun setupCategoryRv() {
        categoryAdapter = ProductCategoryAdapter(categoryList) { clickedItem ->
            categoryList.forEachIndexed { index, item ->
                categoryList[index] = item.copy(selected = item.id == clickedItem.id)
            }

            categoryAdapter.notifyDataSetChanged()

            viewModel.filterByCategory(clickedItem.name)
        }
    }

    private fun setupProductsRv() {
        productAdapter = ProductsAdapter(arrayListOf()) {}
        binding.productRv.adapter = productAdapter
    }

    private fun observeProducts() {
        viewModel.products.observe(viewLifecycleOwner) { list ->
            productAdapter.setData(list)
        }
    }

    private fun openAddProductDialog() {
        AddProductDialog().show(childFragmentManager, "ADD_PRODUCT")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}