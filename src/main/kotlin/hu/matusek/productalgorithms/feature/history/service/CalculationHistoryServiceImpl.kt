package hu.matusek.productalgorithms.feature.history.service

import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryFilter
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import hu.matusek.productalgorithms.feature.history.dto.SaveCalculationHistoryRequest
import hu.matusek.productalgorithms.feature.history.dto.toCalculationHistoryResponse
import hu.matusek.productalgorithms.repository.CalculationHistoryEntryRepository
import org.springframework.stereotype.Service

@Service
class CalculationHistoryServiceImpl(
    val calculationCalculationHistoryEntryRepository: CalculationHistoryEntryRepository
) : CalculationHistoryService {

    override fun saveCalculationToHistory(saveCalculationHistoryRequest: SaveCalculationHistoryRequest) {
        TODO("Implement")
    }

    override fun searchCalculationHistory(calculationHistoryFilter: CalculationHistoryFilter): List<CalculationHistoryResponse> =
        if (calculationHistoryFilter.comment != null) {
            calculationCalculationHistoryEntryRepository.findAllByCommentContainsIgnoreCase(calculationHistoryFilter.comment)
        } else {
            calculationCalculationHistoryEntryRepository.findAll()
        }.map { it.toCalculationHistoryResponse() }
}