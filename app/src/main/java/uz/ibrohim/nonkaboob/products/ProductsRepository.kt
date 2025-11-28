package uz.ibrohim.nonkaboob.products

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.ibrohim.nonkaboob.models.ProductItem
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun getProducts(): Flow<List<ProductItem>> = callbackFlow {
        val ref = firestore.collection("products")

        val listener = ref.addSnapshotListener { snap, _ ->
            if (snap != null) {
                val list = snap.documents.mapNotNull { it.toObject(ProductItem::class.java) }
                trySend(list)
            }
        }
        awaitClose { listener.remove() }
    }

    suspend fun addProduct(item: ProductItem) {
        firestore.collection("products").add(item)
    }
}