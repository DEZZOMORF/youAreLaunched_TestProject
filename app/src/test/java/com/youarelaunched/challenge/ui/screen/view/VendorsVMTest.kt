package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.MainDispatcherRule
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.mock

internal class VendorsVMTest {

    private lateinit var viewModel: VendorsVM
    private lateinit var vendorsRepository: VendorsRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        vendorsRepository = mock(VendorsRepository::class.java)
        viewModel = VendorsVM(vendorsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get vendors success`() = runTest {
        val testList = listOf(
            Vendor(1, "A", "B", "C", false, null, null)
        )
        val expectedData = VendorsScreenUiState.Success(testList)

        Mockito.`when`(vendorsRepository.getVendors(anyString())).thenReturn(testList)
        viewModel.getVendors()
        val currentData = viewModel.uiState.value

        assertEquals(expectedData::class.java, currentData::class.java)
        assertFalse((currentData as? VendorsScreenUiState.Success)?.vendors.isNullOrEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = IndexOutOfBoundsException::class)
    fun `get vendors error`() = runTest {
        val testList = emptyList<Vendor>()

        Mockito.`when`(vendorsRepository.getVendors(anyString())).thenReturn(testList)
        viewModel.getVendors()

        val currentData = viewModel.uiState.value
        (currentData as? VendorsScreenUiState.Success)?.vendors?.get(0)
    }
}