package com.example.noteease.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.noteease.R
import org.koin.ext.clearQuotes

@Composable
fun CustomSearchBar(
    query: String,
    onSearchQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = query,
            singleLine = true,
            onValueChange = onSearchQuery,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    style = MaterialTheme.typography.titleSmall.copy(
                        MaterialTheme.colorScheme.secondary
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}