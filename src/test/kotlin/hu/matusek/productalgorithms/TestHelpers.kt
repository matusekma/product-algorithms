package hu.matusek.productalgorithms

import hu.matusek.productalgorithms.domain.AlgorithmType
import hu.matusek.productalgorithms.domain.CalculationHistoryEntry
import java.time.OffsetDateTime

fun getMockCalculationHistoryEntry() =
    CalculationHistoryEntry(
        1L,
        "test comment",
        AlgorithmType.A,
        listOf(1, 2, 3, 4),
        listOf(24, 12, 8, 6),
        OffsetDateTime.now()
    )