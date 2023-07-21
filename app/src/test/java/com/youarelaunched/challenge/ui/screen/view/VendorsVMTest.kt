package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.MainDispatcherRule
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.mock

internal class VendorsVMTest {

    private lateinit var viewModel: VendorsVM

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        val vendorsRepository = mock(VendorsRepository::class.java)
        viewModel = VendorsVM(vendorsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get vendors success`() = runTest {
        val expectedData = listOf(
            Vendor(1, "A", "B", "C", false, null, null)
        )

        Mockito.`when`(viewModel.getVendors(anyString())).thenReturn(expectedData)
        val currentData = viewModel.getVendors()

        assertTrue(expectedData == currentData)
        assertTrue(currentData.isNotEmpty())
    }
}