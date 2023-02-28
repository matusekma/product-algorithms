package hu.matusek.productalgorithms.feature.history.dto

import hu.matusek.productalgorithms.domain.CalculationHistoryEntry

fun CalculationHistoryEntry.toCalculationHistoryResponse() =
    CalculationHistoryResponse(
        comment,
        algorithmType,
        input,
        result,
        timestamp
    )