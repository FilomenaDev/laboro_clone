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
import com.filomenadeveloper.laboro_new_version.data.api.UserModel
import com.filomenadeveloper.laboro_new_version.database.DatabaseKeys
import com.filomenadeveloper.laboro_new_version.database.HawkStorage
import com.filomenadeveloper.laboro_new_version.utils.ProgressDialogUtil
import com.filomenadeveloper.laboro_new_version.utils.showAlertTapadoo
import org.koin.androidx.viewmodel.ext.android.viewModel

class Screen_Login : AppCompatActivity() {
    private lateinit var InputTextPassword: AppCompatEditText
    private lateinit var InputTextEmail: AppCompatEditText
    private lateinit var ButtonRegisto: AppCompatButton
    private val viewModelAccountAuthentication: ViewModelAccontAuthentication by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        InputTextEmail = findViewById(R.id.textEmailLogin)
        InputTextPassword = findViewById(R.id.textPasswordLogin)
        ButtonRegisto = findViewById(R.id.btnLogin)

        ButtonRegisto.setOnClickListener { RegisterAccont() }

    }

    private fun RegisterAccont(){
        viewModelAccountAuthentication.postAccontLogin(
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
                            print(response.userEmail)
                            when {
                                response !=null -> {
                                    ProgressDialogUtil.hide()

                                    saveOnLocalDatabase(response)
                                    startActivity(
                                        Intent(
                                            this@Screen_Login,
                                            Screen_main::class.java
                                        )
                                    )
                                    finish()
                                }
                                else -> {
                                    ProgressDialogUtil.hide()
                                    showAlertTapadoo(
                                        this@Screen_Login,
                                        "Laboro",
                                        "Erro ao aceder conta.\nVerifique as suas credencias de acesso ou tente recuperar a sua conta.",
                                        R.color.colorMediumRed
                                    )
                                }
                            }
                        }

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

    private fun saveOnLocalDatabase(account: UserModel) {
        HawkStorage().putData(DatabaseKeys.id, account.userId)
        HawkStorage().putData(DatabaseKeys.email, account.userEmail)
        HawkStorage().putData(DatabaseKeys.firstName, account.userFristName)
        HawkStorage().putData(DatabaseKeys.owner, account.userOwner )
        HawkStorage().putData(DatabaseKeys.token, account.token)


    }
}