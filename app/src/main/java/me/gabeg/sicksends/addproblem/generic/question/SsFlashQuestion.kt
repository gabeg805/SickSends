package me.gabeg.sicksends.addproblem.generic.question

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
	// Index
	val index = viewModel.findIndex(viewModel.flash)

	// Question
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
	onDone : () -> Unit = {})
{

	// Flash and project states
	val isFlash = viewModel.problem.isFlash
	val isProject = viewModel.problem.isProject

	println("Is Flash : $isFlash || Visible : $visible")

	// Setup in case need to show the toast
	val context = LocalContext.current
	val msg = "Unable to flash a project"

	// Body
	SsYesNoBody(
		title = viewModel.flash.title,
		question = viewModel.flash.question,
		initialState = isFlash,
		visible = visible,
		disableYesButton = isProject ?: false,
		onDisabled = { status ->
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
		},
		onDone = { status ->
			viewModel.problem.isFlash = status

			// Reset the is project attribute
			if ((status == true) && (isProject == true))
			{
				viewModel.problem.isProject = null
			}

			onDone()
		})

}
