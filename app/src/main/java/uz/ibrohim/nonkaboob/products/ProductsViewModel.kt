package uz.ibrohim.nonkaboob.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.ibrohim.nonkaboob.models.ProductItem
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repo: ProductsRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductItem>>()
    val products: LiveData<List<ProductItem>> get() = _products

    private var fullList: List<ProductItem> = emptyList()

    init {
        loadProducts()
    }

    private fun loadProducts() = viewModelScope.launch {
        repo.getProducts().collect {
            fullList = it
            _products.value = it
        }
    }

    fun filterByCategory(cat: String) {
        _products.value = fullList.filter { it.categoryId == cat }
    }
}