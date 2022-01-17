package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.filomenadeveloper.laboro_new_version.R
import java.io.IOException


class ActivityAddProduts : AppCompatActivity() {

    lateinit var resultUri: Uri
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
    private var IMAGE_GALLERY_REQUEST: Int = 111
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_add_product)

        nameProd = findViewById(R.id.name_prod)
        mLinearOptions = findViewById(R.id.linerOptions)
        priceProd = findViewById(R.id.price_prod)
        pricePromo = findViewById(R.id.price_pormocional_prod)
        descricaoProd = findViewById(R.id.descricao_prod)
        mProfileImage = findViewById(R.id.image_product)
        mOptions = findViewById(R.id.textOption)
        mName = findViewById(R.id.tv_model)
        mPrice = findViewById(R.id.tv_price_current)
        mPromo = findViewById(R.id.tv_price_without_discount)
        mDescricao = findViewById(R.id.tv_desc)
        mDesconto = findViewById(R.id.tv_discount)
        spinnerCategoria = findViewById(R.id.categoria_prod)
        spinnerPorUnidade = findViewById(R.id.por_unit_prod)
        mImage = findViewById(R.id.iv_model)

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
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        spinnerCategoria.setAdapter(adapter)

        val adapterPor = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            itemsPor
        )
        spinnerPorUnidade.setAdapter(adapterPor)

        mProfileImage.setOnClickListener(View.OnClickListener {
           OpenGallery()
        })
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
            var imageUri: Uri = data!!.data!!
            resultUri = imageUri
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, resultUri)
                Glide.with(application)
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

}