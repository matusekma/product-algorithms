package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.error.exception.OperationNotPermittedException
import org.springframework.stereotype.Service

@Service
class CalculateProductsServiceImpl : CalculateProductsService {

    override fun calculateProductsA(numbers: List<Int>): List<Int> {
        if (numbers.size == 1) {
            throw OperationNotPermittedException("The array must be empty or longer than 1 elements!")
        }
        val product = numbers.fold(1) { product, number -> product * number }
        return numbers.map { product / it }
    }
}