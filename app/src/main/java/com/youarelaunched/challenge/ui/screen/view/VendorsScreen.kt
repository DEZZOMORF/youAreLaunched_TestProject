package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.ErrorText
import com.youarelaunched.challenge.ui.screen.view.components.SearchBar
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(
        uiState = uiState,
        searchAction = viewModel::setFilterString
    )
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState,
    searchAction: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) },
        topBar = {
            SearchBar(
                modifier = Modifier
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                onSearch = { searchAction(it) }
            )
        }
    ) { paddings ->
        when (uiState) {
            is VendorsScreenUiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    CircularProgressIndicator(
                        color = VendorAppTheme.colors.progressColor
                    )
                }
            }

            is VendorsScreenUiState.Success -> {
                if (!uiState.vendors.isNullOrEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddings)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(
                            vertical = 24.dp,
                            horizontal = 16.dp
                        )
                    ) {
                        items(uiState.vendors) { vendor ->
                            VendorItem(
                                vendor = vendor
                            )
                        }
                    }
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                    ) {
                        ErrorText(
                            title = stringResource(R.string.no_results_found_title),
                            description = stringResource(R.string.no_results_found_description)
                        )
                    }
                }
            }
        }
    }
}