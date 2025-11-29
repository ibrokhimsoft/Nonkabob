package uz.ibrohim.nonkaboob.products

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import uz.ibrohim.nonkaboob.models.ProductItem
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : ProductsRepository {

    override fun getProducts(): Flow<List<ProductItem>> = callbackFlow {
        val ref = firestore.collection("products")

        val listener = ref.addSnapshotListener { snap, _ ->
            val list = snap?.documents
                ?.mapNotNull { it.toObject(ProductItem::class.java) } ?: emptyList()

            trySend(list)
        }

        awaitClose { listener.remove() }
    }

    override suspend fun addProduct(item: ProductItem, image: ByteArray): Boolean {
        return try {
            val id = firestore.collection("products").document().id

            val imageRef = storage.reference.child("products/$id.jpg")
            imageRef.putBytes(image).await()
            val imageUrl = imageRef.downloadUrl.await().toString()

            val newItem = item.copy(id = id, image = imageUrl)

            firestore.collection("products")
                .document(id)
                .set(newItem)
                .await()

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}