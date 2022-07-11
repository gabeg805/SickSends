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
		index = viewModel.nameIndex,
		scrollState = scrollState)
}

/**
 * Body of the name question.
 */
@Composable
fun SsNameBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	val name = viewModel.problem.name
	println("Name : $name || Visible : $visible")

	SsTextFieldBody(
		title = "Name",
		question = viewModel.nameQuestion,
		initial = name ?: "",
		singleLine = true,
		visible = visible,
		onDone = { newName ->
			viewModel.problem.name = newName

			onDone()
		})

}
