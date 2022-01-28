package com.filomenadeveloper.laboro_new_version.data.models

import com.google.gson.annotations.SerializedName

data class ProductOfModel(@SerializedName("name")
                            var nameProduct: String = "",
                            @SerializedName("price")
                            var priceProduct: Double = 0.0,
                            @SerializedName("description")
                            var descriptionProduct: String = "",
                          @SerializedName("id")
                          var idProduct: Int = 0,
                          @SerializedName("promotion_price")
                          var promotion_priceProduct: Double = 0.0,
                          @SerializedName("sold_per_unit")
                          var sold_per_unitProduct: Boolean = false,
                          @SerializedName("highlight")
                          var highlightProduct: Boolean = false,
                          @SerializedName("available_online")
                          var available_onlineProduct: Boolean = false,
                          @SerializedName("product_categories_id")
                          var product_categories_idProduct: Int = 0,
                          @SerializedName("create_stock")
                          var create_stockProduct: Boolean = false,
                          @SerializedName("quantity")
                          var quantityProduct: Int = 0,
                          @SerializedName("minimum")
                          var minimumProduct: Int = 0,
                          @SerializedName("stock")
                          var stockProduct: String = "",
                          @SerializedName("image_url")
                          var image_urlProduct: String = "",

)