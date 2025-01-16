package com.example.noteease.ui.feature_settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.ui.common.CustomTopAppBar


@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val localUriHandler = LocalUriHandler.current
    val uri = "https://www.linkedin.com/in/ericpaulxp/"

    Scaffold(
        topBar = {
            CustomTopAppBar(
                headerText = stringResource(R.string.about),
                onBackButtonClicked = { navController.navigateUp() }
            )
        }
    ) {
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
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.size(16.dp))

                Box(
                    modifier = modifier
                        .size(112.dp)
                        .clip(RoundedCornerShape(56.dp))
                        .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f)),
                    contentAlignment = Alignment.Center
                ) {

                    Image(painterResource(R.drawable.ic_splash), null)
                }
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    "#1.0.0",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = modifier.size(16.dp))

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(painterResource(R.drawable.ic_profile), null)
                            Spacer(modifier = modifier.size(8.dp))
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = stringResource(R.string.developer),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Eric Paul",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                localUriHandler.openUri(uri)
                            }
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painterResource(R.drawable.ic_heart),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                                Spacer(modifier = modifier.size(8.dp))
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = stringResource(R.string.socials_text),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.tertiary,
                                )
                            }
                            Text(
                                modifier = Modifier.padding(
                                    start = 36.dp, end = 12.dp, top = 0.dp, bottom = 8.dp
                                ),
                                text = stringResource(R.string.socials_description_text),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                }
            }
        }
    }
}

