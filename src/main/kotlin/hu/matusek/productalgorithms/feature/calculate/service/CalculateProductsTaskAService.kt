package hu.matusek.productalgorithms.feature.calculate.service

import org.springframework.stereotype.Service

@Service
class CalculateProductsTaskAService : CalculateProductsService {

    override fun calculateProducts(numbers: List<Int>): List<Int> {
        validateNumberListLength(numbers)
        val product = numbers.fold(1) { product, number -> product * number }
        return numbers.map { product / it }
    }

}