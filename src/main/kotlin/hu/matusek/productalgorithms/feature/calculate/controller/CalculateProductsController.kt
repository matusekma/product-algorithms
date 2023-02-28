package hu.matusek.productalgorithms.feature.calculate.controller

import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsRequest
import hu.matusek.productalgorithms.feature.calculate.dto.CalculateProductsResponse
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskAService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskBService
import hu.matusek.productalgorithms.feature.calculate.service.CalculateProductsTaskCService
import org.springframework.web.bind.annotation.RestController

@RestController
class CalculateProductsController(
    val calculateProductsTaskAService: CalculateProductsTaskAService,
    val calculateProductsTaskBService: CalculateProductsTaskBService,
    val calculateProductsTaskCService: CalculateProductsTaskCService,
) : CalculateProductsApi {

    override fun calculateA(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskAService, calculateProductsRequest)


    override fun calculateB(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskBService, calculateProductsRequest)


    override fun calculateC(calculateProductsRequest: CalculateProductsRequest): CalculateProductsResponse =
        calculate(calculateProductsTaskCService, calculateProductsRequest)


    private fun calculate(
        calculateProductsService: CalculateProductsService,
        calculateProductsRequest: CalculateProductsRequest
    ): CalculateProductsResponse {
        // save request
        val products = calculateProductsService.calculateProducts(calculateProductsRequest.input)

        val response = CalculateProductsResponse(products)

        // save response
        return response
    }
}