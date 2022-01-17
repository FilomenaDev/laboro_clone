package com.filomenadeveloper.laboro_new_version.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Screen_main
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.data.api.UserModel
import com.filomenadeveloper.laboro_new_version.database.DatabaseKeys
import com.filomenadeveloper.laboro_new_version.database.HawkStorage
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
                    Status.SUCCESS -> {
                        ProgressDialogUtil.hide()
                        resource.data?.let { response ->
                            saveOnLocalDatabase(resource.data)
                            startActivity(
                                Intent(
                                    this@Screen_registro,
                                    Screen_main::class.java
                                )
                            )
                            }
                        }
                    Status.ERROR -> {
                        ProgressDialogUtil.hide()
                        showAlertTapadoo(
                            this,
                            "Laboro",
                            "Erro ao criar conta. Verifica a sua conexao a internet",
                            R.color.colorMediumRed
                        )
                    }
                }
            }
        })

    }

    private fun saveOnLocalDatabase(account: UserModel) {
        HawkStorage().putData(DatabaseKeys.id, account.userId)
        HawkStorage().putData(DatabaseKeys.email, account.userEmail)
        HawkStorage().putData(DatabaseKeys.firstName, account.userFristName)
        HawkStorage().putData(DatabaseKeys.owner, account.userOwner )
        HawkStorage().putData(DatabaseKeys.token, account.token)

    }
}

