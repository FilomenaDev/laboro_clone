package com.filomenadeveloper.laboro_new_version

import android.app.Application
import android.os.StrictMode
import com.filomenadeveloper.laboro_new_version.app.injections.mainModule
import com.filomenadeveloper.laboro_new_version.database.baseModels.LaboroDatabase
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppLaboro: Application() {

    override fun onCreate() {
        super.onCreate()
        //FirebaseApp.initializeApp(applicationContext)
        Hawk.init(applicationContext).build()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppLaboro)
            modules(mainModule)
        }
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }

    val database: LaboroDatabase by lazy { LaboroDatabase.getDatabase(this) }
}