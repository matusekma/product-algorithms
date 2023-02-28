package hu.matusek.productalgorithms.repository

import hu.matusek.productalgorithms.domain.HistoryEntry
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoryEntryRepository : CrudRepository<HistoryEntry, Long> {

    fun findAllByCommentContainsIgnoreCase(commentSearch: String)

}