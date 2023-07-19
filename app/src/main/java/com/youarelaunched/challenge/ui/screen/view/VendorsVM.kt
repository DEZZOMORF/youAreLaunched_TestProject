package com.youarelaunched.challenge.ui.screen.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val _uiState: MutableStateFlow<VendorsScreenUiState> =
        MutableStateFlow(VendorsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _filterString = MutableStateFlow("")

    init {
        automaticallyGetVendor()
    }

    @OptIn(FlowPreview::class)
    private fun automaticallyGetVendor() {
        _filterString
            .debounce(500)
            .filter { it.length >= 3 || it.isEmpty() }
            .map { getVendors(it) }
            .catch { e -> Log.e(TAG, "${e.message}") }
            .launchIn(viewModelScope)
    }

    fun getVendors(filter: String = "") {
        _uiState.value = VendorsScreenUiState.Loading
        viewModelScope.launch {
            _uiState.value = VendorsScreenUiState.Success(repository.getVendors(filter))
        }
    }

    fun setFilterString(filter: String) {
        _filterString.value = filter
    }
}