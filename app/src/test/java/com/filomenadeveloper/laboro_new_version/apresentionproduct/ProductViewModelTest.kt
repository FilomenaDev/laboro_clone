package com.filomenadeveloper.laboro_new_version.apresentionproduct

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.filomenadeveloper.laboro_new_version.data.api.ApiClient
import com.filomenadeveloper.laboro_new_version.data.repository.Repository
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.ProductViewModel
import org.junit.Rule
import org.junit.Test
import java.io.File

class ProductViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModelProduct:ProductViewModel
    private lateinit var apiClient: ApiClient
    private val repository: Repository = Repository(apiClient)

    @Test
    suspend fun `when my product view model successfully registers`(){

        val file = File("/C:/Users/Hernani Neto/Pictures/image823.png")
        val body: HashMap<String,Any> = hashMapOf()

        body["name"]=viewModelProduct.createPartFromString("Massa")
        body["price"]=viewModelProduct.createPartFromString("350")
        body["image"] = viewModelProduct.prepareFilePart("image",file.path)
       val result = repository.createProducts(body)
        print(result)
    }
}