package hu.matusek.productalgorithms.feature.history.controller

import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryFilter
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import hu.matusek.productalgorithms.feature.history.service.CalculationHistoryService
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculationHistoryController(
    val calculationHistoryService: CalculationHistoryService
) : CalculationHistoryApi {

    override fun searchCalculationHistory(calculationHistoryFilter: CalculationHistoryFilter): List<CalculationHistoryResponse> =
        calculationHistoryService.searchCalculationHistory(calculationHistoryFilter)

}