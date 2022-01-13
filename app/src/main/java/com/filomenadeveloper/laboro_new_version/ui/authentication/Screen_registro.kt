package com.filomenadeveloper.laboro_new_version.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Screen_main
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.ViewModelAccontAuthentication
import com.filomenadeveloper.laboro_new_version.utils.ProgressDialogUtil
import com.filomenadeveloper.laboro_new_version.utils.showAlertTapadoo
import org.koin.androidx.viewmodel.ext.android.viewModel

class Screen_registro:AppCompatActivity() {

    private lateinit var InputTextName:AppCompatEditText
    private lateinit var InputTextPassword:AppCompatEditText
    private lateinit var InputTextEmail:AppCompatEditText
    private lateinit var ButtonRegisto:AppCompatButton
     private val viewModelAccountAuthentication: ViewModelAccontAuthentication by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_register)

        InputTextName = findViewById(R.id.txtName)
        InputTextEmail = findViewById(R.id.textEmail)
        InputTextPassword = findViewById(R.id.textPassword)
        ButtonRegisto = findViewById(R.id.btnRegisto)

        ButtonRegisto.setOnClickListener { RegisterAccont() }

    }

    private fun RegisterAccont(){
        viewModelAccountAuthentication.postAccountRegister(
            InputTextName.text.toString(),
            InputTextEmail.text.toString(),
            InputTextPassword.text.toString()
        ).observe(this,{
            it?.let { resource ->
                when(resource.status){
                    Status.LOADING -> {
                        ProgressDialogUtil.show(this, "Por favor aguarde.")
                    }
                    Status.SUCCESS ->{
                        ProgressDialogUtil.hide()
                        resource.data?.let { response ->{
                        }
                            showAlertTapadoo(
                                this@Screen_registro,
                                "Laboro",
                                "Sucessos ao criar conta.",
                                R.color.colorLightBlue)
                            startActivity(Intent(this@Screen_registro,Screen_main::class.java))
                        }
                    }
                    Status.ERROR -> {
                        ProgressDialogUtil.hide()
                        showAlertTapadoo(
                            this,
                            "Laboro",
                            "Erro ao criar conta.",
                            R.color.colorMediumRed
                        )
                    }
                }
            }
        })

    }
}

