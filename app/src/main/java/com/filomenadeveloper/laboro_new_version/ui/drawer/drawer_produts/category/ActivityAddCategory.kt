package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Screen_main
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.utils.ProgressDialogUtil
import com.filomenadeveloper.laboro_new_version.utils.showAlertTapadoo
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityAddCategory: AppCompatActivity() {

    private lateinit var BtnAddCategory :AppCompatButton
    private lateinit var InputTextAddCategory :AppCompatEditText
    private lateinit var toobar : Toolbar

    private val viewModelCategory : ViewModelCategory  by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_add_category)
        toobar = findViewById(R.id.toolbar_category)
        setSupportActionBar(toobar)



        BtnAddCategory = findViewById(R.id.btnOfAddCategory)
        InputTextAddCategory = findViewById(R.id.name_category)

        BtnAddCategory.setOnClickListener {
            CreateCategoris()
        }
    }

    private fun CreateCategoris(){
        viewModelCategory.postCategories(
            InputTextAddCategory.text.toString(),
        ).observe(this,{
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS ->{
                        resource.data?.let { response ->
                           supportFragmentManager.beginTransaction().add(R.id.view_pager, FragmentoCategory()).commit()
                        }
                    }
                    Status.ERROR -> {
                        resource.data?.let { response ->
                            showAlertTapadoo(
                                this,
                                "Laboro",
                                "Verica sua conexao",
                                R.color.colorMediumRed
                            )
                        }

                    }
                }
            }
        })

    }
}