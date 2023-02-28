package hu.matusek.productalgorithms.feature.calculate.controller

import hu.matusek.productalgorithms.domain.AlgorithmType
import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsRequest
import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsResponse
import hu.matusek.productalgorithms.repository.CalculationHistoryEntryRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureWebTestClient
class CalculateProductsControllerIntegrationTest {

    @Autowired
    private lateinit var calculationHistoryEntryRepository: CalculationHistoryEntryRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @AfterEach
    fun afterEach() {
        calculationHistoryEntryRepository.deleteAll()
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "c"])
    fun `should calculate the result of the algorithm and save he request and response to the DB`(algorithm: String) {
        val calculateProductsRequest = CalculateProductsRequest("test comment", listOf(1, 2, 3, 4))
        val calculateProductsResponse = webTestClient
            .post()
            .uri("/api/calculate/${algorithm}")
            .bodyValue(calculateProductsRequest)
            .exchange()
            .expectStatus()
            .isOk
            .returnResult<CalculateProductsResponse>()
            .responseBody
            .blockFirst()

        assertNotNull(calculateProductsResponse)
        assertEquals(listOf(24, 12, 8, 6), calculateProductsResponse!!.products)
        val calculationHistoryEntries = calculationHistoryEntryRepository.findAll().toList()
        assertEquals(1, calculationHistoryEntries.size)
        val calculationHistoryEntry = calculationHistoryEntries[0]
        assertEquals(calculateProductsRequest.comment, calculationHistoryEntry.comment)
        assertEquals(calculateProductsRequest.input, calculationHistoryEntry.input)
        assertEquals(
            if (algorithm == "a") AlgorithmType.A else if (algorithm == "b") AlgorithmType.B else AlgorithmType.C,
            calculationHistoryEntry.algorithmType
        )
        assertNotNull(calculationHistoryEntry.timestamp)
        assertEquals(calculateProductsResponse.products, calculationHistoryEntry.result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "c"])
    fun `should return 400 status code in case of invalid integer array`(algorithm: String) {
        val calculateProductsRequest = CalculateProductsRequest("test comment", listOf(1))

        webTestClient
            .post()
            .uri("/api/calculate/${algorithm}")
            .bodyValue(calculateProductsRequest)
            .exchange()
            .expectStatus()
            .isBadRequest
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "c"])
    fun `should return 400 status code in case of request without comment`(algorithm: String) {
        val calculateProductsRequest = CalculateProductsRequest("", listOf(1, 2, 3, 4))

        webTestClient
            .post()
            .uri("/api/calculate/${algorithm}")
            .bodyValue(calculateProductsRequest)
            .exchange()
            .expectStatus()
            .isBadRequest
    }
}