package hu.matusek.productalgorithms.feature.history.service

import hu.matusek.productalgorithms.feature.history.dto.*
import hu.matusek.productalgorithms.repository.CalculationHistoryEntryRepository
import org.springframework.stereotype.Service

@Service
class CalculationHistoryServiceImpl(
    val calculationCalculationHistoryEntryRepository: CalculationHistoryEntryRepository
) : CalculationHistoryService {

    override fun saveCalculationToHistory(saveCalculationHistoryRequest: SaveCalculationHistoryRequest) {
        calculationCalculationHistoryEntryRepository.save(saveCalculationHistoryRequest.toCalculationHistoryEntry())
    }

    override fun searchCalculationHistory(calculationHistoryFilter: CalculationHistoryFilter): List<CalculationHistoryResponse> =
        if (calculationHistoryFilter.comment != null) {
            calculationCalculationHistoryEntryRepository.findAllByCommentContainsIgnoreCase(calculationHistoryFilter.comment)
        } else {
            calculationCalculationHistoryEntryRepository.findAll()
        }.map { it.toCalculationHistoryResponse() }
}