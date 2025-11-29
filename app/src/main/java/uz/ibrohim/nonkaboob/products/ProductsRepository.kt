package uz.ibrohim.nonkaboob.products

import kotlinx.coroutines.flow.Flow
import uz.ibrohim.nonkaboob.models.ProductItem

interface ProductsRepository {
    fun getProducts(): Flow<List<ProductItem>>
    suspend fun addProduct(item: ProductItem, image: ByteArray): Boolean
}