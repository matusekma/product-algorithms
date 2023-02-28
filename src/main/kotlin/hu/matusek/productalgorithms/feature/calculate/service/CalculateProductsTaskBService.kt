package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.domain.AlgorithmType
import org.springframework.stereotype.Service

@Service
class CalculateProductsTaskBService : CalculateProductsService {

    override fun getAlgorithmType() = AlgorithmType.B

    override fun calculateProducts(numbers: List<Int>): List<Int> {
        validateNumberListLength(numbers)
        return List(numbers.size) { currentIndex ->
            numbers.foldIndexed(1) { i, product, number -> product * (if (currentIndex == i) 1 else number) }
        }
    }

}