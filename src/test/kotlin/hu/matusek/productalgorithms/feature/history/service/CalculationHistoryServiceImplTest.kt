package hu.matusek.productalgorithms.feature.history.service

import hu.matusek.productalgorithms.domain.AlgorithmType
import hu.matusek.productalgorithms.domain.CalculationHistoryEntry
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryFilter
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import hu.matusek.productalgorithms.feature.history.dto.SaveCalculationHistoryRequest
import hu.matusek.productalgorithms.getMockCalculationHistoryEntry
import hu.matusek.productalgorithms.repository.CalculationHistoryEntryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.time.OffsetDateTime


@SpringJUnitConfig
class CalculationHistoryServiceImplTest {

    @Mock
    lateinit var calculationCalculationHistoryEntryRepository: CalculationHistoryEntryRepository

    @InjectMocks
    lateinit var calculationHistoryServiceImpl: CalculationHistoryServiceImpl

    @Nested
    inner class TestSaveCalculationToHistory {

        @Captor
        lateinit var saveArgumentCaptor: ArgumentCaptor<CalculationHistoryEntry>

        @Test
        fun `given a save request as input, it save it to the history through the repository`() {
            val mockSaveRequest = SaveCalculationHistoryRequest(
                "test comment",
                AlgorithmType.A,
                listOf(1, 2, 3, 4),
                listOf(24, 12, 8, 6),
                OffsetDateTime.now()
            )
            calculationHistoryServiceImpl.saveCalculationToHistory(
                mockSaveRequest
            )

            verify(calculationCalculationHistoryEntryRepository).save(saveArgumentCaptor.capture())
            assertEquals(mockSaveRequest.input, saveArgumentCaptor.value.input)
            assertEquals(mockSaveRequest.result, saveArgumentCaptor.value.result)
            assertEquals(mockSaveRequest.comment, saveArgumentCaptor.value.comment)
            assertEquals(mockSaveRequest.algorithmType, saveArgumentCaptor.value.algorithmType)
            assertEquals(mockSaveRequest.timestamp, saveArgumentCaptor.value.timestamp)
        }

    }

    @Nested
    inner class TestSearchCalculationHistory {

        @Test
        fun `given comment filter, it should call the search method of the repository and return the response`() {
            val comment = "test comment"
            val mockFilter = CalculationHistoryFilter(comment)
            val mockCalculationHistoryEntry = getMockCalculationHistoryEntry()
            `when`(calculationCalculationHistoryEntryRepository.findAllByCommentContainsIgnoreCase(comment)).thenReturn(
                listOf(mockCalculationHistoryEntry)
            )

            val calculationHistoryResponses = calculationHistoryServiceImpl.searchCalculationHistory(mockFilter)

            verify(calculationCalculationHistoryEntryRepository).findAllByCommentContainsIgnoreCase(comment)
            assertEquals(1, calculationHistoryResponses.size)
            val calculationHistoryResponse = calculationHistoryResponses[0]
            assertCalculationHistoryResponse(mockCalculationHistoryEntry, calculationHistoryResponse)
        }

        @Test
        fun `given no comment filter, it should call the findAll method of the repository and return the response`() {
            val mockFilter = CalculationHistoryFilter(null)
            val mockCalculationHistoryEntry = getMockCalculationHistoryEntry()
            `when`(calculationCalculationHistoryEntryRepository.findAll()).thenReturn(
                listOf(mockCalculationHistoryEntry)
            )

            val calculationHistoryResponses = calculationHistoryServiceImpl.searchCalculationHistory(mockFilter)

            verify(calculationCalculationHistoryEntryRepository).findAll()
            assertEquals(1, calculationHistoryResponses.size)
            val calculationHistoryResponse = calculationHistoryResponses[0]
            assertCalculationHistoryResponse(mockCalculationHistoryEntry, calculationHistoryResponse)
        }

        @Test
        fun `given there is no history, it should return empty list`() {
            val comment = "test comment"
            val mockFilter = CalculationHistoryFilter(comment)
            `when`(calculationCalculationHistoryEntryRepository.findAllByCommentContainsIgnoreCase(comment)).thenReturn(
                emptyList()
            )

            val calculationHistoryResponses = calculationHistoryServiceImpl.searchCalculationHistory(mockFilter)

            verify(calculationCalculationHistoryEntryRepository).findAllByCommentContainsIgnoreCase(comment)
            assertEquals(0, calculationHistoryResponses.size)
        }

        private fun assertCalculationHistoryResponse(
            mockCalculationHistoryEntry: CalculationHistoryEntry,
            calculationHistoryResponse: CalculationHistoryResponse
        ) {
            assertEquals(mockCalculationHistoryEntry.input, calculationHistoryResponse.input)
            assertEquals(mockCalculationHistoryEntry.result, calculationHistoryResponse.result)
            assertEquals(mockCalculationHistoryEntry.comment, calculationHistoryResponse.comment)
            assertEquals(mockCalculationHistoryEntry.algorithmType, calculationHistoryResponse.algorithmType)
            assertEquals(mockCalculationHistoryEntry.timestamp, calculationHistoryResponse.timestamp)
        }

    }
}