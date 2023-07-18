package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun ErrorText(title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = VendorAppTheme.colors.textError,
            style = VendorAppTheme.typography.h1,
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            color = VendorAppTheme.colors.text,
            style = VendorAppTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

