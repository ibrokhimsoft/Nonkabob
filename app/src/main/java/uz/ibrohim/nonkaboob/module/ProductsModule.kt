package uz.ibrohim.nonkaboob.module

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.ibrohim.nonkaboob.products.ProductsRepository
import uz.ibrohim.nonkaboob.products.ProductsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsModule {

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideProductsRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): ProductsRepository = ProductsRepositoryImpl(firestore, storage)

    @Binds
    abstract fun bindProductsRepository(
        impl: ProductsRepositoryImpl
    ): ProductsRepository
}