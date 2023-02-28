package hu.matusek.productalgorithms.feature.history.controller

import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryFilter
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Validated
@RequestMapping("/api/history")
interface CalculationHistoryApi {

    @GetMapping
    fun searchCalculationHistory(calculationHistoryFilter: CalculationHistoryFilter): List<CalculationHistoryResponse>

}