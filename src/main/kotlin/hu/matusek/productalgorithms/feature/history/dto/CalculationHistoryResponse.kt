package hu.matusek.productalgorithms.feature.history.dto

import hu.matusek.productalgorithms.domain.AlgorithmType
import java.time.OffsetDateTime

data class CalculationHistoryResponse(
    val comment: String,
    val algorithmType: AlgorithmType,
    val input: List<Int>,
    val result: List<Int>,
    val timestamp: OffsetDateTime
)
