package uz.ibrohim.nonkaboob.products

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class AddProductDialog : DialogFragment() {

    private lateinit var binding: DialogAddProductBinding
    private var imageUrl: String? = null

    private val viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        binding = DialogAddProductBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.btnPickImage.setOnClickListener { pickImage() }

        binding.btnSave.setOnClickListener {
            val product = ProductItem(
                name = binding.edtName.text.toString(),
                price = binding.edtPrice.text.toString().toInt(),
                count = binding.edtCount.text.toString(),
                category = binding.category.selectedItem.toString(),
                image = imageUrl ?: ""
            )

            viewModel.addProduct(product)
            dismiss()
        }

        return dialog
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val uri = result.data?.data ?: return@registerForActivityResult
                uploadToFirebase(uri)
            }
        }

    private fun uploadToFirebase(uri: Uri) {
        val ref = FirebaseStorage.getInstance().reference
            .child("product_images/${UUID.randomUUID()}")

        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                imageUrl = it.toString()
                binding.imgPreview.setImageURI(uri)
            }
        }
    }
}