package hu.matusek.productalgorithms.feature.calculate.service

import hu.matusek.productalgorithms.error.exception.OperationNotPermittedException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

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
    fun `given a list with more than 2 elements, it returns the products according to the algorithm`() {
        val products = calculateProductsService.calculateProducts(listOf(1, 2, 3, 4))

        assertEquals(listOf(24, 12, 8, 6), products)
    }

}