package com.example.API.controllers

import com.example.API.models.Warranty
import com.example.API.repositories.WarrantiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WarrantiesController {
    @Autowired
    lateinit var warrantyRepository: WarrantiesRepository
    @GetMapping ("/warranties")
    fun getWarranties(): List<Warranty>{
        return warrantyRepository.getWarranties()
    }

    @PostMapping("/warranties")
    fun createWarranty(@RequestBody warranty: Warranty): Map<String, Any>{
        val createWarranty = warrantyRepository.createWarranty(warranty)
        return if (createWarranty>0){
            mapOf(
                "success" to true,
                "message" to "Garantía creada correctamente"
            )
        } else {
            mapOf(
                "success" to false,
                "message" to "No se pudo crear la garantía"
            )
        }

    }


}