package hu.matusek.productalgorithms.feature.history.dto

import jakarta.validation.constraints.Size

data class CalculationHistoryFilter(
    @field:Size(min = 1)
    val comment: String?
)
