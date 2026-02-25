package com.example.API.models

data class Product (
    var input_order_id: Int,
    var subcategory_id: Int,
    var product_id : Int,
    var product_serial: Int,
    var product_name: String,
    var product_model: String,
    var product_stock: Int,
    var product_garanty_input: String
) {
}