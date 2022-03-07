package com.filomenadeveloper.laboro_new_version.data.models

import com.google.gson.annotations.SerializedName

data class ProductOfModel(@SerializedName("name")
                            var nameProduct: String = "",
                            @SerializedName("price")
                            var priceProduct: String = "",
                            @SerializedName("description")
                            var descriptionProduct: String = "",
                          @SerializedName("id")
                          var idProduct: Int = 0,
                          @SerializedName("promotion_price")
                          var promotion_priceProduct: String = "",
                          @SerializedName("sold_per_unit")
                          var sold_per_unitProduct: String = "",
                          @SerializedName("highlight")
                          var highlightProduct: String = "",
                          @SerializedName("available_online")
                          var available_onlineProduct: String = "",
                          @SerializedName("product_categories_id")
                          var product_categories_idProduct: String = "",
                          @SerializedName("create_stock")
                          var create_stockProduct: String = "",
                          @SerializedName("quantity")
                          var quantityProduct: String = "",
                          @SerializedName("minimum")
                          var minimumProduct: String = "",
                          @SerializedName("stock")
                          var stockProduct: String = "",
                          @SerializedName("image_url")
                          var image_urlProduct: String = "",

)

