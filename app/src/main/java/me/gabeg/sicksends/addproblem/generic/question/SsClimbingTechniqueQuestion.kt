package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.addproblem.SsButtonToggleGroupBody
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.ui.SsBoulderIcon
import me.gabeg.sicksends.problem.ui.SsTechniqueIcon
import me.gabeg.sicksends.shared.getAllClimbNames
import me.gabeg.sicksends.shared.getAllClimbingTechniqueNames
import me.gabeg.sicksends.shared.getAllHoldNames

/**
 * Climbing technique question.
 */
@Composable
fun SsClimbingTechniqueQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsTechniqueIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsClimbingTechniqueBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = viewModel.climbingTechniqueIndex,
		scrollState = scrollState)
}

/**
 * Holds that are on a climb.
 */
@Composable
fun SsClimbingTechniqueBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	// Climbing techniques
	val climbingTechniques by viewModel.problem.observableClimbingTechnique.observeAsState(SsClimbingTechniqueType.emptySet())

	// Body
	SsButtonToggleGroupBody(
		title = "Climbing Techniques",
		question = viewModel.climbingTechniqueQuestion,
		initialState = climbingTechniques,
		allStateNames = getAllClimbingTechniqueNames(),
		visible = visible,
		onClick = {
			viewModel.problem.observableClimbingTechnique.value = it
		},
		onDone = onDone)

}
