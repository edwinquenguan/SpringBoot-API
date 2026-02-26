package com.example.API.controllers

import com.example.API.models.Product
import com.example.API.repositories.ProductsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {
    @Autowired
    lateinit var productsRepository: ProductsRepository

    @GetMapping("/products")
    fun getProduts(): List<Product> {
        return productsRepository.getProducts()
    }

    @PostMapping("/products")
    fun createProducts(@RequestBody product: Product): Map<String, Any>{

        val createdProduct = productsRepository.createProduct(product)

        return if (createdProduct > 0 ) {
            mapOf(
                "success" to true,
                "message" to "Producto creado correctamente"
            )
        } else {
            mapOf(
                "success" to false,
                "message" to "No se pudo crear el producto"
            )
        }
    }

    @PutMapping("/products/{id}")
    fun updateUser(@RequestBody product: Product, @PathVariable id: Int): Map<String, Any>{
        val updatedProduct = productRepository.updateProduct(product, id)

        return if (updatedProduct > 0){
            mapOf(
                "success" to true,
                "message" to "Producto actualizado correctamente"
            )
        } else {
            mapOf(
                "success" to false,
                "message" to "No se pudo actualizar el usuario"
            )
        }
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: Int): Map<String, Any>{
        val deleteProduct = productRepository.deleteProduct(id)

        return if (deleteProduct > 0){
            mapOf(
                "success" to true,
                "message" to "Producto eliminado correctamente"
            )
        } else {
            mapOf(
                "success" to false,
                "message" to "No se pudo eliminar el producto"
            )
        }
    }


}