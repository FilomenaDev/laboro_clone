package com.filomenadeveloper.laboro_new_version.database

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.filomenadeveloper.laboro_new_version.database.baseModels.ProductDao
import kotlinx.coroutines.launch
import com.filomenadeveloper.laboro_new_version.database.baseModels.Produts as Produts1

class LaboroViewModels(private val productDao: ProductDao): ViewModel(){

    val allItems: LiveData<List<Produts1>> = productDao.getItems().asLiveData()

    private fun insertItem(products: Produts1) {
        viewModelScope.launch {
            productDao.insert(products)
        }
    }

    fun isEntryValid(productName: String, productPrice: String, itemImage: String): Boolean {
        if (productName.isBlank() || productPrice.isBlank() || itemImage.isBlank()) {
            return false
        }
        return true
    }

    fun addNewProduct(itemName: String, itemPrice: String,
                      description:String,idproduct:Int,promotion:String,
                      sold_per_unit:String,highlight:Boolean,available_online:Boolean,
                      categorie:Int,create_stock:Boolean,
                      minimum:Int,stock:Int,image:ByteArray) {
        val newItem = getNewItemEntry(itemName, itemPrice,description,idproduct,promotion,sold_per_unit,
        highlight,available_online,categorie,create_stock,minimum,stock,image)
        insertItem(newItem)
    }

   // val allProducts : LiveData<List<Produts>> = productDao.getItems().asLiveData()


    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(itemName: String, itemPrice: String,
                                description:String,idproduct:Int,promotion:String,
                                sold_per_unit:String,highlight:Boolean,available_online:Boolean,
                                categorie:Int,create_stock:Boolean,
                                minimum:Int,stock:Int,image:ByteArray
    ): Produts1 {
        return Produts1(
            nameProduct = itemName,
            priceProduct = itemPrice.toDouble(),
            descriptionProduct = description,
            idProduct = idproduct,
            promotion_priceProduct = promotion,
            sold_per_unitProduct = sold_per_unit,
            highlightProduct = highlight,
            available_onlineProduct = available_online,
            product_categories_idProduct = categorie,
            create_stockProduct = create_stock,
            minimumProduct = minimum,
            stockProduct = stock,
            image_urlProduct = image
        )
    }
}

class LaboroViewModelFactory(private val productDao: ProductDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaboroViewModels::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LaboroViewModels(productDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}