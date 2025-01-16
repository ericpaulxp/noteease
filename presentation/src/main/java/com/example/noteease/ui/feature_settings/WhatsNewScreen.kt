package com.example.noteease.ui.feature_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.ui.common.CustomTopAppBar


@Composable
fun WhatsNewScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(topBar = {
        CustomTopAppBar(headerText = stringResource(R.string.whats_new_text),
            onBackButtonClicked = { navController.navigateUp() })
    }) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Spacer(modifier = Modifier.size(12.dp))

                WhatsNewCard(
                    icon = painterResource(R.drawable.ic_new),
                    versionText = "1.0.0",
                    dateText = "Wed, 15 Jan 2025",
                    descriptionText = stringResource(R.string.initial_release_text)
                )
            }
        }
    }
}

@Composable
fun WhatsNewCard(
    modifier: Modifier = Modifier,
    icon: Painter,
    versionText: String,
    dateText: String? = null,
    descriptionText: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.size(8.dp))
                Icon(
                    painter = icon, contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = versionText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            if (dateText != null)
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = dateText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
        }

        Text(
            modifier = Modifier.padding(
                start = 48.dp, end = 12.dp, top = 4.dp, bottom = 8.dp
            ),
            text = descriptionText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

