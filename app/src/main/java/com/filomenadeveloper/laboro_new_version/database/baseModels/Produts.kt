package com.filomenadeveloper.laboro_new_version.database.baseModels


import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey



@Entity(tableName = "products")
data class Produts(
    @PrimaryKey(autoGenerate = true)
     var id: Int =0 ,
    @ColumnInfo(name = "product")
    var nameProduct: String?,
    @ColumnInfo(name = "price")
    var priceProduct: Double?,
    @ColumnInfo(name = "description")
    var descriptionProduct: String?,
    @ColumnInfo(name = "idproduct")
    var idProduct: Int?,
    @ColumnInfo(name = "promotion")
    var promotion_priceProduct: String?,
    @ColumnInfo(name = "sold_per_unit")
    var sold_per_unitProduct: String?,
    @ColumnInfo(name = "highlight")
    var highlightProduct: Boolean?,
    @ColumnInfo(name = "available_online")
    var available_onlineProduct: Boolean?,
    @ColumnInfo(name = "categorie")
    var product_categories_idProduct: Int?,
    @ColumnInfo(name = "create_stock")
    var create_stockProduct: Boolean?,
    @ColumnInfo(name = "minimum")
    var minimumProduct: Int?,
    @ColumnInfo(name = "stock")
    var stockProduct: Int?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "image")
    val image_urlProduct: ByteArray? = null
)
fun Produts.getFormattedPrice(): String =
    java.text.NumberFormat.getCurrencyInstance().format(priceProduct)