package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.addproblem.SsButtonToggleGroupBody
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.ui.SsBoulderIcon
import me.gabeg.sicksends.shared.getAllHoldNames

/**
 * Hold question.
 */
@Composable
fun SsHoldQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.hold)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsBoulderIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsHoldBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Holds that are on a climb.
 */
@Composable
fun SsHoldBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Holds
	val holds by viewModel.problem.observableHold.observeAsState(SsHoldType.emptySet())

	// Body
	SsButtonToggleGroupBody(
		title = "Holds",
		question = viewModel.hold.question,
		initialState = holds,
		allStateNames = getAllHoldNames(),
		visible = visible,
		onClick = {
			viewModel.problem.observableHold.value = it
		},
		onDone = onDone)

}
