package com.example.noteease.ui.feature_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.ui.common.CustomActionbar
import com.example.noteease.ui.common.CustomTopAppBar
import com.example.noteease.ui.feature_edit_note.EditNoteEvent
import com.example.noteease.ui.feature_edit_note.EditNoteViewModel
import com.example.noteease.utils.getShortDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArchivedDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchivedDetailsViewModel = koinViewModel(),
    editViewModel: EditNoteViewModel = koinViewModel(),
    navController: NavController
) {
    val titleState = viewModel.titleState.value
    val bodyState = viewModel.bodyState.value
    val accessDateState = viewModel.dateState.value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                headerText = stringResource(R.string.archive_details),
                onBackButtonClicked = {
                    navController.navigateUp()
                }
            )
        },
        bottomBar = {
            CustomActionbar(
                onArchivedClicked = {
                    editViewModel.onEvent(EditNoteEvent.ArchivedNote(false))
                    navController.navigateUp()
                },
                onDeleteClicked = {
                    editViewModel.onEvent(EditNoteEvent.DeleteNote)
                    navController.navigateUp()
                }
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
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)

            ) {
                Spacer(modifier.size(12.dp))
                Text(
                    text = titleState, style = MaterialTheme.typography.headlineMedium.copy(
                        MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(modifier.size(18.dp))
                Text(
                    text = bodyState, style = MaterialTheme.typography.bodyMedium.copy(
                        MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(modifier = Modifier.size(32.dp))

                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_opened),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = stringResource(R.string.accessed) + " " + getShortDate(
                            accessDateState
                        ),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic)
                    )
                }
            }
        }
    }
}