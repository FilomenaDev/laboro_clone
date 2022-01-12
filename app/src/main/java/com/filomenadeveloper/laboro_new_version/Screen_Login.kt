package com.filomenadeveloper.laboro_new_version

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

class Screen_Login:AppCompatActivity() {

    private lateinit var InputTextName:AppCompatEditText
    private lateinit var InputTextPassword:AppCompatEditText
    private lateinit var InputTextEmail:AppCompatEditText
    private lateinit var ButtonRegisto:AppCompatButton
     private val viewModelAccountAuthentication:ViewModelAccontAuthentication by viewModel()

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
                    Status.SUCCESS ->{
                        Toast.makeText(this@Screen_Login,"criado",Toast.LENGTH_LONG).show()
                    }
                 //
                }
            }
        })

        }
    }

