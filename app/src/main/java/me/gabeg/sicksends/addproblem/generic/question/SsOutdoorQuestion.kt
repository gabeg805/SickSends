package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsYesNoBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsOutdoorIcon

/**
 * Outdoor question.
 */
@Composable
fun SsOutdoorQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.outdoorIndex

	// Create the question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsOutdoorIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsIsOutdoorBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Was the problem outdoors or not.
 */
@Composable
fun SsIsOutdoorBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Outdoor : $visible")
	val isOutdoor = viewModel.problem.isOutdoor

	SsYesNoBody(
		title = "Outdoor",
		question = "Was the problem outdoors?",
		initial = isOutdoor,
		visible = visible,
		onDone = { status, subtitle ->
			viewModel.problem.isOutdoor = status

			onDone(subtitle)
		})

}
