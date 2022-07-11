package me.gabeg.sicksends.addproblem.generic.question

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import me.gabeg.sicksends.addproblem.SsContinueSkipButton
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
		index = viewModel.projectIndex,
		scrollState = scrollState)
}

/**
 * Body of the project question.
 *
 * TODO: Might want to just disable one of the buttons, instead of hiding? Idk
 */
@Composable
fun SsIsProjectBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Project and flash states
	val isProject = viewModel.problem.isProject
	val isFlash = viewModel.problem.isFlash

	println("Is Project : $isProject || Visible : $visible")

	// Setup in case need to show the toast
	val context = LocalContext.current
	val msg = "Unable to project a flash"

	// Body
	SsYesNoBody(
		title = "Project",
		question = viewModel.projectQuestion,
		initialState = isProject,
		visible = visible,
		disableYesButton = isFlash ?: false,
		onDisabled = { status ->
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
		},
		onDone = { status ->
			viewModel.problem.isProject = status

			// Reset the is flash attribute
			if ((status == true) && (isFlash == true))
			{
				viewModel.problem.isFlash = null
			}

			onDone()
		})

	//// Skip button
	//AnimatedVisibility(visible = visible)
	//{
	//	SsContinueSkipButton(
	//		state = false,
	//		onSkip = {
	//			viewModel.problem.isProject = null

	//			onDone()
	//		})
	//}

}
