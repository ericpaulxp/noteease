package com.example.noteease.ui.common

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.domain.model.Languages
import com.example.domain.model.Theme
import com.example.noteease.R
import com.example.noteease.ui.feature_settings.SelectedItem
import com.example.noteease.ui.feature_settings.SettingsViewmodel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: SettingsViewmodel = koinViewModel()
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val themes = Theme.entries
                val selectedTheme = viewmodel.theme.collectAsState()

                Text(text = stringResource(R.string.theme), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.size(16.dp))

                themes.forEach { theme ->
                    SelectedItem(
                        selected = selectedTheme.value == theme,
                        onClick = {
                            viewmodel.updateTheme(theme)
                            onDismiss()
                        },
                    ) {
                        Text(
                            text = theme.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val languages = listOf(
                    Languages("English", "en"),
                    Languages("Bangla", "bn")
                )
                val selectedLanguage =
                    remember { AppCompatDelegate.getApplicationLocales()[0]?.language }

                Text(text = stringResource(R.string.language), style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.size(16.dp))
                languages.forEach { language ->
                    SelectedItem(
                        selected = selectedLanguage == language.code,
                        onClick = {
                            val appLocale: LocaleListCompat =
                                LocaleListCompat.forLanguageTags(language.code)
                            AppCompatDelegate.setApplicationLocales(appLocale)
                            onDismiss()
                        },
                    ) {
                        Column {
                            Text(
                                text = language.name.toString(),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onBtnClicked: () -> Unit
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {

                Text(text = stringResource(R.string.delete_note), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.size(24.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
                    )
                ) {
                    Text(
                        text = stringResource(R.string.are_you_sure_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(R.string.warning_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelLarge.copy(MaterialTheme.colorScheme.secondary)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = onBtnClicked
                ) {
                    Text(text = stringResource(R.string.delete_note), style = MaterialTheme.typography.titleMedium, color = White)

                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}



