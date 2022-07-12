package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.gabeg.sicksends.addproblem.SsContinueSkipButton
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsTextFieldBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsNoteIcon

/**
 * Note question.
 */
@Composable
fun SsNoteQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.note)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsNoteIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsNoteBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Notes for a climb.
 *
 * TODO: Note height seems to overlap other things.
 */
@Composable
fun SsNoteBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Note
	val note by viewModel.problem.observableNote.observeAsState()
	println("Note : $note || Visible : $visible")

	// Body
	SsTextFieldBody(
		title = "Notes",
		question = viewModel.note.question,
		initial = note ?: "",
		modifier = Modifier.fillMaxSize(),
		maxLines = 6,
		visible = visible,
		onTextChange = { newNote ->
			viewModel.problem.observableNote.value = newNote
		})

	// Continue/skip button
	AnimatedVisibility(visible = visible)
	{
		SsContinueSkipButton(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp, bottom = 64.dp),
			state = note?.isNotEmpty() ?: false,
			onContinue = {
				onDone()
			},
			onSkip = {
				viewModel.problem.observableNote.value = null

				onDone()
			}
		)
	}
}
