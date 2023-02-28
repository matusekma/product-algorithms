package hu.matusek.productalgorithms.feature.calculate.controller

import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsRequest
import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsResponse
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Validated
@RequestMapping("/api/calculate")
interface CalculateProductsApi {

    @PostMapping("/a")
    fun calculateA(@Valid @RequestBody calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse

    @PostMapping("/b")
    fun calculateB(@Valid @RequestBody calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse

    @PostMapping("/c")
    fun calculateC(@Valid @RequestBody calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse
}