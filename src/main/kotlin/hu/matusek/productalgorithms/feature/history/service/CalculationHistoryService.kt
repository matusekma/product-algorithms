package hu.matusek.productalgorithms.feature.history.service

import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryFilter
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import hu.matusek.productalgorithms.feature.history.dto.SaveCalculationHistoryRequest

interface CalculationHistoryService {

    fun saveCalculationToHistory(saveCalculationHistoryRequest: SaveCalculationHistoryRequest)

    fun searchCalculationHistory(calculationHistoryFilter: CalculationHistoryFilter): List<CalculationHistoryResponse>

}