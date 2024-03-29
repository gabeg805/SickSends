package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsButtonToggleGroupBody
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsWallFeatureType
import me.gabeg.sicksends.problem.ui.SsWallIcon
import me.gabeg.sicksends.shared.getAllWallFeatureNames

/**
 * Wall feature question.
 */
@Composable
fun SsWallFeatureQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.wallFeature)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsWallIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsWallFeatureBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Features that are on the wall of a climb.
 */
@Composable
fun SsWallFeatureBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Wall features
	val wallFeatures by viewModel.problem.observableWallFeature.observeAsState(SsWallFeatureType.emptySet())

	// Body
	SsButtonToggleGroupBody(
		title = viewModel.wallFeature.title,
		question = viewModel.wallFeature.question,
		initialState = wallFeatures,
		allStateNames = getAllWallFeatureNames(),
		visible = visible,
		onClick = {
			viewModel.problem.observableWallFeature.value = it
		},
		onDone = onDone)

}
