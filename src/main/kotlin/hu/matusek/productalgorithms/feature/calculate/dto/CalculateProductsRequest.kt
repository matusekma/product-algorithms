package hu.matusek.productalgorithms.feature.calculate.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CalculateProductsRequest(
    @field:NotBlank
    val comment: String,
    @field:NotNull
    val input: List<Int>
)