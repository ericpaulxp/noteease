package com.example.noteease.ui.feature_edit_note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.ui.common.CustomActionbar
import com.example.noteease.ui.common.CustomTopAppBar
import com.example.noteease.ui.common.DeleteBottomSheet
import com.example.noteease.ui.common.TransparentTextField
import com.example.noteease.utils.getShortDate
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: EditNoteViewModel = koinViewModel(),
    navController: NavController,
) {
    val titleState = viewModel.titleState.value
    val bodyState = viewModel.bodyState.value
    val creationDateState = viewModel.creationDate.value
    val accessedAtState = viewModel.accessedAt.value
    val noteId = viewModel.doesHaveId

    val scope = rememberCoroutineScope()
    var isDeleteBottomSheetVisible = rememberSaveable { mutableStateOf(false) }
    val deleteBottomSheetState = rememberModalBottomSheetState()

    BackHandler {
        if (titleState.text.isBlank() && bodyState.text.isBlank()) {
            viewModel.onEvent(EditNoteEvent.DeleteNote)
        } else {
            viewModel.onEvent(EditNoteEvent.SaveNote)
        }
        navController.navigateUp()
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                headerText = if (noteId == 0) stringResource(R.string.new_note) else stringResource(R.string.edit_note),
                onBackButtonClicked = {
                    if (titleState.text.isBlank() && bodyState.text.isBlank()) {
                        viewModel.onEvent(EditNoteEvent.DeleteNote)
                    } else {
                        viewModel.onEvent(EditNoteEvent.SaveNote)
                    }
                    navController.navigateUp()
                }
            )
        },
        bottomBar = {
            CustomActionbar(
                onArchivedClicked = {
                    if (titleState.text.isBlank() && bodyState.text.isBlank()) {
                        viewModel.onEvent(EditNoteEvent.DeleteNote)
                    } else {
                        viewModel.onEvent(EditNoteEvent.ArchivedNote(true))
                    }
                    navController.navigateUp()
                },
                onDeleteClicked = {
                    scope.launch {
                        isDeleteBottomSheetVisible.value = true
                        deleteBottomSheetState.expand()
                    }
                }
            )
            DeleteBottomSheet(
                isBottomSheetVisible = isDeleteBottomSheetVisible.value,
                sheetState = deleteBottomSheetState,
                onDismiss = {
                    scope.launch { deleteBottomSheetState.hide() }
                        .invokeOnCompletion { isDeleteBottomSheetVisible.value = false }
                },
                onBtnClicked = {
                    scope.launch { deleteBottomSheetState.hide() }
                        .invokeOnCompletion { isDeleteBottomSheetVisible.value = false }
                    viewModel.onEvent(EditNoteEvent.DeleteNote)
                    navController.navigateUp()
                }
            )
        },
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(it)
                    .consumeWindowInsets(it)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
            ) {

                TransparentTextField(
                    text = titleState.text,
                    onValueChange = {
                        viewModel.onEvent(EditNoteEvent.EnteredTitle(it))
                    },
                    textStyle = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                    label = stringResource(R.string.title),
                    maxLines = 10,
                    style = MaterialTheme.typography.headlineMedium
                )
                TransparentTextField(
                    text = bodyState.text,
                    onValueChange = {
                        viewModel.onEvent(EditNoteEvent.EnteredBody(it))
                    },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        MaterialTheme.colorScheme.secondary
                    ),
                    label = stringResource(R.string.body),
                    maxLines = 20,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(0.4f))

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(R.string.created) + " " + getShortDate(creationDateState),
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_opened), contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(R.string.accessed) + " " + getShortDate(accessedAtState),
                        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

