package com.filomenadeveloper.laboro_new_version.database.baseModels

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produts::class], version = 1, exportSchema = false)
abstract class LaboroDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductDao

    companion object{
        @Volatile
        private var INSTANCE: LaboroDatabase? = null

        fun getDatabase(context: Context): LaboroDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    LaboroDatabase::class.java,
                    "BDLaboro"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}