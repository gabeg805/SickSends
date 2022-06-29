package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsTextFieldBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsNameIcon

/**
 * Name question.
 */
@Composable
fun SsNameQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.nameIndex

	// Create the question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsNameIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsNameBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the name question.
 */
@Composable
fun SsNameBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Name : $visible")
	val initialName = viewModel.getInitialName()

	SsTextFieldBody(
		title = "Name",
		question = "What is the name of the climb?",
		initial = initialName,
		singleLine = true,
		visible = visible,
		onDone = { newName ->
			viewModel.problem.name = newName

			onDone(newName)
		})

}
