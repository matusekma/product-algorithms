package hu.matusek.productalgorithms.feature.history.controller

import hu.matusek.productalgorithms.domain.CalculationHistoryEntry
import hu.matusek.productalgorithms.feature.history.dto.CalculationHistoryResponse
import hu.matusek.productalgorithms.getMockCalculationHistoryEntry
import hu.matusek.productalgorithms.repository.CalculationHistoryEntryRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureWebTestClient
class CalculationHistoryControllerIntegrationTest {

    @Autowired
    private lateinit var calculationHistoryEntryRepository: CalculationHistoryEntryRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @AfterEach
    fun afterEach() {
        calculationHistoryEntryRepository.deleteAll()
    }

    @Nested
    inner class TestSearchCalculationHistory {

        @Test
        fun `should find the history entries matching the filter`() {
            val mockCalculationHistoryEntryMatching =
                getMockCalculationHistoryEntry().apply { id = null; comment = "test comment" }
            val mockCalculationHistoryEntryNotMatching =
                getMockCalculationHistoryEntry().apply { id = null; comment = "not matching comment" }
            calculationHistoryEntryRepository.saveAll(
                listOf(
                    mockCalculationHistoryEntryMatching, mockCalculationHistoryEntryNotMatching
                )
            )

            val calculationHistoryResponses = webTestClient
                .get()
                .uri { uriBuilder ->
                    uriBuilder.path("/api/history").queryParam("comment", "est co").build()
                }
                .exchange()
                .expectStatus()
                .isOk
                .expectBodyList<CalculationHistoryResponse>()
                .returnResult()
                .responseBody

            assertNotNull(calculationHistoryResponses)
            assertEquals(1, calculationHistoryResponses!!.size)
            val calculationHistoryResponse = calculationHistoryResponses[0]
            assertCalculationHistoryResponse(mockCalculationHistoryEntryMatching, calculationHistoryResponse)
        }

        @Test
        fun `should return all the history entries when there is no filter`() {
            val mockCalculationHistoryEntry1 =
                getMockCalculationHistoryEntry().apply { id = null; comment = "test comment" }
            val mockCalculationHistoryEntry2 =
                getMockCalculationHistoryEntry().apply { id = null; comment = "test comment 1" }
            calculationHistoryEntryRepository.saveAll(
                listOf(
                    mockCalculationHistoryEntry1, mockCalculationHistoryEntry2
                )
            )

            val calculationHistoryResponses = webTestClient
                .get()
                .uri("/api/history")
                .exchange()
                .expectStatus()
                .isOk
                .expectBodyList<CalculationHistoryResponse>()
                .returnResult()
                .responseBody

            assertNotNull(calculationHistoryResponses)
            assertEquals(2, calculationHistoryResponses!!.size)
            val calculationHistoryResponse1 = calculationHistoryResponses[0]
            val calculationHistoryResponse2 = calculationHistoryResponses[1]
            assertCalculationHistoryResponse(mockCalculationHistoryEntry1, calculationHistoryResponse1)
            assertCalculationHistoryResponse(mockCalculationHistoryEntry2, calculationHistoryResponse2)
        }

        private fun assertCalculationHistoryResponse(
            expectedCalculationHistoryEntry: CalculationHistoryEntry,
            calculationHistoryResponse: CalculationHistoryResponse
        ) {
            assertEquals(expectedCalculationHistoryEntry.comment, calculationHistoryResponse.comment)
            assertEquals(expectedCalculationHistoryEntry.input, calculationHistoryResponse.input)
            assertEquals(expectedCalculationHistoryEntry.algorithmType, calculationHistoryResponse.algorithmType)
            assertEquals(
                expectedCalculationHistoryEntry.timestamp.toEpochSecond(),
                calculationHistoryResponse.timestamp.toEpochSecond()
            )
            assertEquals(expectedCalculationHistoryEntry.result, expectedCalculationHistoryEntry.result)
        }
    }
}