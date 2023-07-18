package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val shape = RoundedCornerShape(22.dp)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = shape,
                ambientColor = VendorAppTheme.colors.shadowColor,
                spotColor = VendorAppTheme.colors.text
            )
            .background(color = Color.White, shape = shape)
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            placeholder = { SearchBarPlaceholder() },
            trailingIcon = { SearchBarTrailingIcon() },
            shape = shape,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = VendorAppTheme.colors.text,
                cursorColor = VendorAppTheme.colors.text,
                trailingIconColor = VendorAppTheme.colors.iconColor,
                placeholderColor = VendorAppTheme.colors.text
            ),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
            })
        )
    }
}

@Composable
fun SearchBarPlaceholder() {
    Text(
        text = "Search...",
        color = VendorAppTheme.colors.text,
        style = VendorAppTheme.typography.body1,
    )
}

@Composable
fun SearchBarTrailingIcon() {
    Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = null
    )
}

