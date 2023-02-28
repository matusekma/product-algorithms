package hu.matusek.productalgorithms.repository

import hu.matusek.productalgorithms.domain.CalculationHistoryEntry
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CalculationHistoryEntryRepository : CrudRepository<CalculationHistoryEntry, Long> {

    fun findAllByCommentContainsIgnoreCase(commentSearch: String): List<CalculationHistoryEntry>

}