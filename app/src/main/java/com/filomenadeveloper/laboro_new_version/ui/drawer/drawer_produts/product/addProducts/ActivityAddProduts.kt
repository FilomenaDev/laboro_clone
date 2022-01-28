package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.FragmentProdut
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.ProductViewModel
import com.filomenadeveloper.laboro_new_version.utils.ProgressDialogUtil
import com.filomenadeveloper.laboro_new_version.utils.showAlertTapadoo
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.lang.Exception
import android.graphics.Bitmap
import java.nio.ByteBuffer


class ActivityAddProduts : Fragment() {
    private lateinit var root: View
    private val viewModelProducts: ProductViewModel by viewModel()
    lateinit var resultUri: Uri
    lateinit var bitmap: Bitmap
    lateinit var spinnerCategoria: AppCompatAutoCompleteTextView
    lateinit var spinnerPorUnidade: AppCompatAutoCompleteTextView
    lateinit var nameProd: EditText
    lateinit var priceProd: EditText
    lateinit var pricePromo: EditText
    lateinit var descricaoProd: EditText
    lateinit var mName: TextView
    lateinit var mPrice: TextView
    lateinit var mPromo: TextView
    lateinit var mDescricao: TextView
    lateinit var mDesconto: TextView
    lateinit var mOptions: TextView
    lateinit var mLinearOptions: LinearLayout
    lateinit var mProfileImage: ImageView
    lateinit var mImage: ImageView
    lateinit var BtnAddProducts: AppCompatButton
    private var IMAGE_GALLERY_REQUEST: Int = 1

   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.screen_add_product, container, false)
       nameProd = root.findViewById(R.id.name_prod)
       mLinearOptions = root.findViewById(R.id.linerOptions)
       priceProd = root.findViewById(R.id.price_prod)
       pricePromo = root.findViewById(R.id.price_pormocional_prod)
       descricaoProd = root.findViewById(R.id.descricao_prod)
       mProfileImage = root.findViewById(R.id.image_product)
       mOptions = root.findViewById(R.id.textOption)
       mName = root.findViewById(R.id.tv_model)
       mPrice = root.findViewById(R.id.tv_price_current)
       mPromo = root.findViewById(R.id.tv_price_without_discount)
       mDescricao = root.findViewById(R.id.tv_desc)
       spinnerCategoria = root.findViewById(R.id.categoria_prod)
       spinnerPorUnidade = root.findViewById(R.id.por_unit_prod)
       mImage = root.findViewById(R.id.iv_model)
       BtnAddProducts = root.findViewById(R.id.btnAddProduct)

       mOptions.setOnClickListener {
           if(mLinearOptions.visibility == View.GONE) {
               mLinearOptions.visibility = View.VISIBLE
               mOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,
                   R.drawable.ic_baseline_arrow_drop_up,0)
           }
           else {
               mLinearOptions.visibility = View.GONE
               mOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(
                   0,
                   0,
                   R.drawable.ic_baseline_arrow_drop_down,
                   0
               )
           }
       }


       BtnAddProducts.setOnClickListener(View.OnClickListener {
           try {
               addProducts()
           }catch (e:Exception){

           }
       })

       nameProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mName.text = s.toString()
            }
        })

        priceProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mPrice.text = "Kz $s"

            }})

        descricaoProd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mDescricao.text = "$s"
            }})

        pricePromo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mPromo.visibility = View.VISIBLE
                mPromo.text = priceProd.text}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               //
                mPrice.text = "Kz $s"
                mDesconto.text = "$s"

            }})
        val items = listOf("Sanduiche", "Festfod", "Roupas", "Telefones")
        val itemsPor = listOf("Sanduiche", "Festfod", "Roupas", "Telefones")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            items
        )
        spinnerCategoria.setAdapter(adapter)

        val adapterPor = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            itemsPor
        )
        spinnerPorUnidade.setAdapter(adapterPor)

        mProfileImage.setOnClickListener(View.OnClickListener {
           OpenGallery()
        })


        return root
    }


    fun OpenGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent, "Selecionar a Imagem"),
            IMAGE_GALLERY_REQUEST
        )
    }

    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray? {
        val byteBuffer: ByteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        byteBuffer.rewind()
        return byteBuffer.array()
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
                    .apply(RequestOptions()).into(mImage)
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
            nameProd.text.toString(),
            priceProd.text.toString(),
            descricaoProd.text.toString(),
            0.0,
            false,
            false,
            false,
            1,
            false,
            0,
            0,
           image = convertBitmapToByteArray(bitmap)!!
        ).observe(requireActivity(),{
            it?.let { resource ->
                when(resource.status){
            Status.LOADING -> {
                      //  ProgressDialogUtil.show(this, "Por favor aguarde.")
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


}