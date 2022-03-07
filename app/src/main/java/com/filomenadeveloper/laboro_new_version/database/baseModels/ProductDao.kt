package com.filomenadeveloper.laboro_new_version.database.baseModels


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * from products ORDER BY product ASC")
    fun getItems(): Flow<List<Produts>>

    @Query("SELECT * from products WHERE id = :id")
    fun getItem(id: Int): Flow<Produts>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg produts: Produts)


}