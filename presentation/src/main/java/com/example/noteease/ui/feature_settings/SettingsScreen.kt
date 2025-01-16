package com.example.noteease.ui.feature_settings

import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.navigation.AboutRoute
import com.example.noteease.navigation.WhatsNewRoute
import com.example.noteease.ui.common.CustomTopAppBar
import com.example.noteease.ui.common.LanguageBottomSheet
import com.example.noteease.ui.common.ThemeBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    var isThemeBottomSheetVisible = rememberSaveable { mutableStateOf(false) }
    var isLanguageBottomSheetVisible = rememberSaveable { mutableStateOf(false) }
    val themeBottomSheetState = rememberModalBottomSheetState()
    val languageBottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                headerText = stringResource(R.string.settings),
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
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Card(
                    modifier = modifier.padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
                    )
                ) {
                    SettingItem(
                        leadingIcon = painterResource(R.drawable.ic_sun_dial),
                        text = stringResource(R.string.theme),
                        onClicked = {
                            scope.launch {
                                isThemeBottomSheetVisible.value = true
                                themeBottomSheetState.expand()
                            }
                        }
                    )
                    SettingItem(
                        leadingIcon = painterResource(R.drawable.ic_planet),
                        text = stringResource(R.string.language),
                        onClicked = {
                            scope.launch {
                                isLanguageBottomSheetVisible.value = true
                                languageBottomSheetState.expand()
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))

                Card(
                    modifier = modifier.padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
                    )
                ) {
                    SettingItem(
                        leadingIcon = painterResource(R.drawable.ic_star),
                        text = stringResource(R.string.whats_new_text),
                        onClicked = {
                            navController.navigate(WhatsNewRoute)
                        }
                    )

                    SettingItem(
                        leadingIcon = painterResource(R.drawable.ic_info),
                        text = stringResource(R.string.about),
                        onClicked = {
                            navController.navigate(AboutRoute)
                        }
                    )
                }
            }
        }
        ThemeBottomSheet(
            isBottomSheetVisible = isThemeBottomSheetVisible.value,
            sheetState = themeBottomSheetState,
            onDismiss = {
                scope.launch { themeBottomSheetState.hide() }
                    .invokeOnCompletion { isThemeBottomSheetVisible.value = false }
            }
        )
        LanguageBottomSheet(
            isBottomSheetVisible = isLanguageBottomSheetVisible.value,
            sheetState = languageBottomSheetState,
            onDismiss = {
                scope.launch { languageBottomSheetState.hide() }
                    .invokeOnCompletion { isLanguageBottomSheetVisible.value = false }
            }
        )
    }
}

@Composable
fun SelectedItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Surface(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        color = if (selected) MaterialTheme.colorScheme.surface.copy(0.4f) else MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.bodyLarge,
                LocalContentColor provides if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary,
                content = content
            )
        }
    }
}
