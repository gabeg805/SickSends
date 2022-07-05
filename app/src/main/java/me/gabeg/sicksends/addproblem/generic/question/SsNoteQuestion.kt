package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
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
	// Get the index
	val index = viewModel.noteIndex

	// Create the question
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
 */
@Composable
fun SsNoteBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	val note = viewModel.problem.note
	println("Note : $note || Visible : $visible")

	SsTextFieldBody(
		title = "Notes",
		question = "Do you have any notes for the climb?",
		initial = note ?: "",
		singleLine = false,
		visible = visible,
		onDone = { newNote ->
			viewModel.problem.note = newNote

			onDone(newNote)
		})

}
