package com.filomenadeveloper.laboro_new_version.database

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import com.filomenadeveloper.laboro_new_version.utils.AlertDialog
import com.filomenadeveloper.laboro_new_version.utils.Toast
import com.orhanobut.hawk.Hawk

object DatabaseKeys {
    const val id: String = "idKey"
    const val firstName: String = "firstNameKey"
    const val lastName: String = "lastNameKey"
    const val email: String = "emailKey"
    const val phone: String = "phoneKey"
    const val neighborhood: String = "neighborhoodKey"
    const val street: String = "streetKey"
    const val county: String = "countyKey"
    const val points: String = "pointsKey"
    const val idCountry: String = "idCountryKey"
    const val token: String = "idTokenKey"
    const val tokenRefresh: String = "idTokenRefreshKey"
    const val password: String = "idPassword"
    const val app: String = "idAppKey"
}

class HawkStorage {

    fun clearData() {
        Hawk.deleteAll()
    }

    fun putData(key: String, value: Any) {
        Hawk.put(key, value)
    }

    fun getData(key: String): Any? = if (checkData(DatabaseKeys.id)) Hawk.get(key, Any()) else Any()

    fun getIntData(key: String): Int {
        return when {
            checkData(DatabaseKeys.id) -> getData(key) as Int
            else -> 0
        }
    }

    fun getCountryData(key: String): Int {
        return when {
            checkData(DatabaseKeys.id) -> getData(key) as Int
            else -> 1
        }
    }

    fun checkData(key: String): Boolean = Hawk.contains(key)


    fun AppCompatActivity.logOut() {
        when {
            HawkStorage().checkData(DatabaseKeys.id) -> {
                AlertDialog(object : AlertDialog.IAlert {
                    override fun onPositive(dialogInterface: DialogInterface) {
                        when {
                            HawkStorage().checkData(DatabaseKeys.id) -> {
                                HawkStorage().clearData()
                                dialogInterface.dismiss()
                           //     newScreenWithoutHistory(
                             //       ActivityAuthentication::class.java,
                              //      Bundle()
                                //)
                            }
                        }
                    }

                    override fun onCancel(dialogInterface: DialogInterface) {
                        dialogInterface.dismiss()
                    }
                }).show(
                    this,
                    "Olá ${HawkStorage().getData(DatabaseKeys.firstName)}.",
                    "Deseja terminar sessão?",
                    "Sim",
                    "Não"
                )
            }
            else -> {
                Toast.show(this, "Voçê não está logado.")
               // newScreenWithoutHistory(ActivityAuthentication::class.java, Bundle())
            }
        }
    }
}