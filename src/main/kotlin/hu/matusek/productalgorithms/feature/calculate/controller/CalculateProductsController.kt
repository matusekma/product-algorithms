package hu.matusek.productalgorithms.feature.calculate.controller

import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsRequest
import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsResponse
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskAService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskBService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskCService
import hu.matusek.productalgorithms.feature.history.dto.SaveCalculationHistoryRequest
import hu.matusek.productalgorithms.feature.history.service.CalculationHistoryService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

@RestController
class CalculateProductsController(
    val calculateProductsTaskAService: CalculateProductsTaskAService,
    val calculateProductsTaskBService: CalculateProductsTaskBService,
    val calculateProductsTaskCService: CalculateProductsTaskCService,
    val calculationHistoryService: CalculationHistoryService
) : CalculateProductsApi {

    @Transactional
    override fun calculateA(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskAService, calculateProductsRequest)


    @Transactional
    override fun calculateB(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskBService, calculateProductsRequest)


    @Transactional
    override fun calculateC(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskCService, calculateProductsRequest)


    private fun calculate(
        calculateProductsService: CalculateProductsService,
        calculateProductsRequest: CalculateProductsRequest
    ): CalculateProductsResponse {
        val timestamp = OffsetDateTime.now()

        val products = calculateProductsService.calculateProducts(calculateProductsRequest.input)

        val saveCalculationHistoryRequest = SaveCalculationHistoryRequest(
            calculateProductsRequest.comment,
            calculateProductsService.getAlgorithmType(),
            calculateProductsRequest.input,
            products,
            timestamp
        )
        calculationHistoryService.saveCalculationToHistory(saveCalculationHistoryRequest)

        return CalculateProductsResponse(products)
    }
}