package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.domain.AlgorithmType
import org.springframework.stereotype.Service

@Service
class CalculateProductsTaskCService : CalculateProductsService {

    override fun getAlgorithmType() = AlgorithmType.C

    override fun calculateProducts(numbers: List<Int>): List<Int> {
        validateNumberListLength(numbers)
        val products = mutableListOf<Int>()
        var temp = 1
        // calculate suffix products
        numbers.forEach {
            products.add(temp)
            temp *= it
        }
        temp = 1
        // multiply with prefix products
        for (i in numbers.size - 1 downTo 0) {
            products[i] = products[i] * temp
            temp *= numbers[i]
        }
        return products
    }

}