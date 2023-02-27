package hu.matusek.productalgorithms.feature.calculate.service

class CalculateProductsTaskBService : CalculateProductsService {

    override fun calculateProducts(numbers: List<Int>): List<Int> {
        validateNumberListLength(numbers)
        return List(numbers.size) { currentIndex ->
            numbers.foldIndexed(1) { i, product, number -> product * (if (currentIndex == i) 1 else number) }
        }
    }

}