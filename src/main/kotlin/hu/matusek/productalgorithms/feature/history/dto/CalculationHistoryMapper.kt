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

fun SaveCalculationHistoryRequest.toCalculationHistoryEntry() =
    CalculationHistoryEntry(
        comment = comment,
        algorithmType = algorithmType,
        input = input,
        result = result,
        timestamp = timestamp
    )