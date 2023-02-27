package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.error.exception.OperationNotPermittedException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.util.stream.Stream

@SpringJUnitConfig
abstract class CalculateProductsServiceTest<T : CalculateProductsService> {

    lateinit var calculateProductsService: T

    abstract fun createInstance(): T

    @BeforeEach
    fun setUp() {
        calculateProductsService = createInstance()
    }

    @Test
    fun `given an empty list, it should return an empty list`() {
        val products = calculateProductsService.calculateProducts(emptyList())

        assertEquals(0, products.size)
    }

    @Test
    fun `given a list with 1 element, it should throw an exception`() {
        assertThrows<OperationNotPermittedException> { calculateProductsService.calculateProducts(listOf(5)) }
    }

    @Test
    fun `given a list with 2 elements, it returns the reversed list`() {
        val products = calculateProductsService.calculateProducts(listOf(5, 6))

        assertEquals(listOf(6, 5), products)
    }

    @Test
    fun `given the list contains one 0, it returns 0 except for the 0's index`() {
        val products = calculateProductsService.calculateProducts(listOf(5, 6, 0))

        assertEquals(listOf(0, 0, 30), products)
    }

    @Test
    fun `given the list contains more than one 0, it returns all 0s`() {
        val products = calculateProductsService.calculateProducts(listOf(0, 5, 6, 0))

        assertEquals(listOf(0, 0, 0, 0), products)
    }

    @ParameterizedTest
    @MethodSource("getNumbersAndExpectedProducts")
    fun `given a list with more than 2 elements, it returns the products according to the algorithm`(
        numbers: List<Int>,
        expectedProducts: List<Int>
    ) {
        val products = calculateProductsService.calculateProducts(numbers)

        assertEquals(expectedProducts, products)
    }

    companion object {
        @JvmStatic
        private fun getNumbersAndExpectedProducts(): Stream<Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3, 4),
                listOf(24, 12, 8, 6),
            ),
            Arguments.of(
                listOf(11, 99, 23, 45),
                listOf(99 * 23 * 45, 11 * 23 * 45, 11 * 99 * 45, 11 * 99 * 23),
            ),
            Arguments.of(
                listOf(5, 0, 7, 2),
                listOf(0, 5 * 7 * 2, 0, 0),
            ),
        )
    }

}