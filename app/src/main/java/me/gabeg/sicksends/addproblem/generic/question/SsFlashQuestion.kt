package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsYesNoBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsFlashIcon

/**
 * Flash question.
 */
@Composable
fun SsFlashQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.attemptIndex

	// Create the question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsFlashIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsIsFlashBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the flash question.
 */
@Composable
fun SsIsFlashBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Flash : $visible")
	val isFlash = viewModel.problem.isFlash

	SsYesNoBody(
		title = "Flash",
		question = "Did you flash the problem?",
		initial = isFlash,
		visible = visible,
		onDone = { status, subtitle ->
			viewModel.problem.isFlash = status

			onDone(subtitle)
		})

}
