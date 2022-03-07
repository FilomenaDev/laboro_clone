package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.ProductViewModel
import com.filomenadeveloper.laboro_new_version.utils.ProgressDialogUtil
import com.filomenadeveloper.laboro_new_version.utils.showAlertTapadoo
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import android.graphics.Bitmap
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.ImageHeaderParser
import com.filomenadeveloper.laboro_new_version.AppLaboro
import com.filomenadeveloper.laboro_new_version.database.LaboroViewModelFactory
import com.filomenadeveloper.laboro_new_version.database.LaboroViewModels
import com.filomenadeveloper.laboro_new_version.database.baseModels.Produts
import com.filomenadeveloper.laboro_new_version.databinding.ScreenAddProductBinding
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.ProductFragmentNavigationDirections
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.FragmentOfUnityProductDirections
import java.io.ByteArrayOutputStream


class ActivityAddProduts : Fragment() {
    private lateinit var root: View
    private val viewModelProducts: ProductViewModel by viewModel()
    lateinit var resultUri: Uri
    lateinit var bitmap: Bitmap
    private var IMAGE_GALLERY_REQUEST: Int = 1



    private val viewModel: LaboroViewModels by activityViewModels {
        LaboroViewModelFactory((activity?.application as AppLaboro).database.productsDao())
    }


    lateinit var item: Produts

    private var _binding : ScreenAddProductBinding? = null
    private val binding get() = _binding!!

    lateinit var produts: Produts

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
    ): View {
        _binding = ScreenAddProductBinding.inflate(inflater,container,false)
        return binding.root
    }


    fun OpenGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent, "Selecionar a Imagem"),
            IMAGE_GALLERY_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            resultUri = data!!.data!!
            try {
                 bitmap =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, resultUri)
                Glide.with(requireContext())
                    .load(bitmap) // Uri of the picture
                    .apply(RequestOptions()).into(binding.layoutItemProduct.ivModel)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun getCountriesList(): ArrayList<String> {
        val countriesList = ArrayList<String>()
        countriesList.add("India")
        countriesList.add("Usa")
        countriesList.add("Canada")
        return countriesList

    }



    private fun addProducts(){
        viewModelProducts.postProducts(
            requireContext(),
            binding.nameProd.text.toString(),
            binding.priceProd.text.toString(),
            binding.descricaoProd.text.toString(),
            0.0,
            false,
            false,
            false,
            1,
            false,
            0,
            0,
            resultUri
        ).observe(requireActivity(),{
            it?.let { resource ->
                when(resource.status){
            Status.LOADING -> {
                        ProgressDialogUtil.show(requireContext(), "Por favor aguarde.")
                    }
                    Status.SUCCESS ->{
                        ProgressDialogUtil.hide()
                        resource.data?.let { response ->
                          //  FragmentManager.beginTransaction().add(R.id.view_pager, FragmentProdut()).commit()
                        }
                    }
                    Status.ERROR -> {
                        ProgressDialogUtil.hide()
                            showAlertTapadoo(
                                requireActivity(),
                                "Laboro",
                                "Verica sua conexao",
                                R.color.colorMediumRed
                            )
                    }
                }
            }
        })

    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.nameProd.text.toString(),
            binding.priceProd.text.toString(),
            resultUri.path.toString(),
        )
    }

    private fun addNewItem() {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val image = stream.toByteArray()
        if (isEntryValid()) {
            viewModel.addNewProduct(
                binding.nameProd.text.toString(),
                binding.priceProd.text.toString(),
                binding.descricaoProd.text.toString(),
                1,
                binding.pricePormocionalProd.text.toString(),
                "Por unit",
                false,
                false,
                1,
                false,
                0,
                0,
                image
            )
            val action = FragmentOfUnityProductDirections.actionItemListFragmentToAddItemFragment()
            findNavController().navigate(action)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textOption.setOnClickListener {
            if(binding.linerOptions.visibility == View.GONE) {
                binding.linerOptions.visibility = View.VISIBLE
                binding.textOption.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,
                    R.drawable.ic_baseline_arrow_drop_up,0)
            }
            else {
                binding.linerOptions.visibility = View.GONE
                binding.textOption.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_baseline_arrow_drop_down,
                    0
                )
            }
        }


        binding.btnAddProduct.setOnClickListener {
            try{
                addNewItem()
            }catch (e:Exception){

            }

        }



        binding.nameProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.layoutItemProduct.tvModel.text = s.toString()
            }
        })

        binding.priceProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.layoutItemProduct.tvPriceCurrent.text = "Kz $s"

            }})

        binding.descricaoProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.layoutItemProduct.tvDesc.text = "$s"
            }})

        binding.pricePormocionalProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //
                binding.layoutItemProduct.tvPriceCurrent.text = "Kz $s"
                binding.layoutItemProduct.tvDesc.text = "$s"

            }})
        val items = listOf("Sanduiche", "Festfod", "Roupas", "Telefones")
        val itemsPor = listOf("Sanduiche", "Festfod", "Roupas", "Telefones")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            items
        )
        binding.categoriaProd.setAdapter(adapter)

        val adapterPor = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            itemsPor
        )
        binding.porUnitProd.setAdapter(adapterPor)

        binding.imageProduct.setOnClickListener(View.OnClickListener {
            OpenGallery()
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}