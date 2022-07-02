package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsYesNoBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsProjectIcon

/**
 * Project question.
 */
@Composable
fun SsProjectQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.projectIndex

	// Create the question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsProjectIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsIsProjectBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the project question.
 *
 * TODO: If is flash, then can't be project.
 */
@Composable
fun SsIsProjectBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Project : $visible")
	val isProject = viewModel.problem.isProject

	SsYesNoBody(
		title = "Project",
		question = "Are you projecting this problem?",
		initial = isProject,
		visible = visible,
		onDone = { status, subtitle ->
			viewModel.problem.observableIsProject.value = status

			// Reset the is flash attribute
			if (viewModel.problem.isFlash == true)
			{
				viewModel.problem.isFlash = null
			}

			onDone(subtitle)
		})

}
