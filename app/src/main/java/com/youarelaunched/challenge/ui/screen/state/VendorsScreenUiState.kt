package com.youarelaunched.challenge.ui.screen.state

import com.youarelaunched.challenge.data.repository.model.Vendor

sealed class VendorsScreenUiState {
    class Success(val vendors: List<Vendor>?) : VendorsScreenUiState()
    object Loading : VendorsScreenUiState()
}
