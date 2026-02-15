package uz.ibrohim.nonkaboob.products

import android.R
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import uz.ibrohim.nonkaboob.databinding.DialogAddProductBinding
import uz.ibrohim.nonkaboob.models.CategoryItem
import uz.ibrohim.nonkaboob.models.ProductItem
import java.util.UUID

@AndroidEntryPoint
class AddProductDialog : DialogFragment() {

    private var _binding: DialogAddProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by activityViewModels()

    private var imageBytes: ByteArray? = null

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { selectedUri ->
                binding.imgPreview.setImageURI(selectedUri)

                // ByteArray
                val stream = requireContext().contentResolver.openInputStream(selectedUri)
                imageBytes = stream?.readBytes()
            }
        }

    private lateinit var categories: List<CategoryItem>
    private lateinit var warehouses: List<WarehouseItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadSpinners()
        setupClicks()
    }

    private fun loadSpinners() {
        // Misol uchun, categoryList sizda mavjud
        categories = listOf(
            CategoryItem(1, "Ichimliklar"),
            CategoryItem(2, "Shirinliklar"),
            CategoryItem(3, "Fast Food")
        )

        warehouses = listOf(
            WarehouseItem("wh1", "Asosiy ombor"),
            WarehouseItem("wh2", "Filial Ombori")
        )

        val catTitles = categories.map { it.name }
        val whTitles = warehouses.map { it.name }

        binding.category.adapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, catTitles)

        binding.warehouse.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, whTitles)
    }

    private fun setupClicks() {
        binding.btnPickImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            saveProduct()
        }
    }

    private fun saveProduct() {
        val name = binding.edtName.text.toString().trim()
        val price = binding.edtPrice.text.toString().trim()
        val count = binding.edtCount.text.toString().trim()

        if (name.isEmpty() || price.isEmpty() || count.isEmpty()) {
            Toast.makeText(requireContext(), "Ma'lumotlarni to‘liq kiriting!", Toast.LENGTH_SHORT).show()
            return
        }

        if (imageBytes == null) {
            Toast.makeText(requireContext(), "Rasm tanlang!", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedCategory = categories[binding.category.selectedItemPosition]
        val selectedWarehouse = warehouses[binding.warehouse.selectedItemPosition]

        val product = ProductItem(
            id = "",
            name = name,
            price = price,
            count = count,
            categoryId = selectedCategory.name,
            warehouseId = selectedWarehouse.id,
            image = ""
        )

        viewModel.addProduct(product, imageBytes!!) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Mahsulot qo‘shildi!", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Xatolik!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class WarehouseItem(
    val id: String,
    val name: String
)