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
		index = viewModel.outdoorIndex,
		scrollState = scrollState)
}

/**
 * Was the problem outdoors or not.
 */
@Composable
fun SsIsOutdoorBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Outdoor
	val isOutdoor = viewModel.problem.isOutdoor
	println("Is Outdoor : $isOutdoor || Visible : $visible")

	// Body
	SsYesNoBody(
		title = "Outdoors",
		question = viewModel.outdoorQuestion,
		initialState = isOutdoor,
		visible = visible,
		onDone = { status ->
			viewModel.problem.isOutdoor = status

			onDone()
		})

}
