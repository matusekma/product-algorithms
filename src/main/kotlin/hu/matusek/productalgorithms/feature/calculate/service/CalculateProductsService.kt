package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.domain.AlgorithmType
import hu.matusek.productalgorithms.error.exception.OperationNotPermittedException

interface CalculateProductsService {

    fun getAlgorithmType(): AlgorithmType

    fun calculateProducts(numbers: List<Int>): List<Int>

    fun validateNumberListLength(numbers: List<Int>) {
        if (numbers.size == 1) {
            throw OperationNotPermittedException("The array must be empty or longer than 1 elements!")
        }
    }
}