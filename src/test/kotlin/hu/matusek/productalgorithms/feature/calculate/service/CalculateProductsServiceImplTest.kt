package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.error.exception.OperationNotPermittedException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig
class CalculateProductsServiceImplTest {

    val calculateProductService: CalculateProductsServiceImpl = CalculateProductsServiceImpl()

    @Nested
    inner class TestCalculateProductsA {
        @Test
        fun `given an empty list, it should return an empty list`() {
            val products = calculateProductService.calculateProductsA(emptyList())

            assertEquals(0, products.size)
        }

        @Test
        fun `given a list with 1 element, it should throw an exception`() {
            assertThrows<OperationNotPermittedException> { calculateProductService.calculateProductsA(listOf(5)) }
        }

        @Test
        fun `given a list with 2 elements, it returns the reversed list`() {
            val products = calculateProductService.calculateProductsA(listOf(5, 6))

            assertEquals(listOf(6, 5), products)
        }

        @Test
        fun `given a list with more than 2 elements, it returns the products according to the algorithm`() {
            val products = calculateProductService.calculateProductsA(listOf(1, 2, 3, 4))

            assertEquals(listOf(24, 12, 8, 6), products)
        }
    }

}