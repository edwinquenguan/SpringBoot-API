package com.example.API.controllers

import com.example.API.models.Warranty
import com.example.API.repositories.WarrantiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WarrantiesController {
    @Autowired
    lateinit var warrantyRepository: WarrantiesRepository
    @GetMapping ("/warranties")
    fun getWarranties(): List<Warranty>{
        return warrantyRepository.getWarranties()
    }

}