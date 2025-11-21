package uz.ibrohim.nonkaboob.models

data class CategoryModel(
    val title: String,
    val iconRes: Int,
    val color: Int,
    val products: List<ProductModel>
)
