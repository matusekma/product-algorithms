package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.domain.AlgorithmType
import org.springframework.stereotype.Service

@Service
class CalculateProductsTaskAService : CalculateProductsService {

    override fun getAlgorithmType() = AlgorithmType.A

    override fun calculateProducts(numbers: List<Int>): List<Int> {
        validateNumberListLength(numbers)
        val countOfZeroes = numbers.count { it == 0 }
        if (countOfZeroes > 1) return List(numbers.size) { 0 }

        val productOfNonZeroes = numbers.fold(1) { product, number -> product * (if (number == 0) 1 else number) }
        if (countOfZeroes == 1) {
            return numbers.map { if (it == 0) productOfNonZeroes else 0 }
        }
        return numbers.map { productOfNonZeroes / it }
    }

}