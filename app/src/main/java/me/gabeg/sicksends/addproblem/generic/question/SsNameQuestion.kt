package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsContinueSkipButton
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsTextFieldBody
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
	// Index
	val index = viewModel.findIndex(viewModel.name)

	// Question
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
		index = index,
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

	// Name
	val name by viewModel.problem.observableName.observeAsState()
	println("Name : $name || Visible : $visible")

	// Body
	SsTextFieldBody(
		title = viewModel.name.title,
		question = viewModel.name.question,
		initial = name ?: "",
		modifier = Modifier
			.fillMaxWidth(),
		singleLine = true,
		visible = visible,
		onTextChange = { newName ->
			viewModel.problem.observableName.value = newName
		},
		onDone = {
			onDone()
		})

	// Continue/skip button
	AnimatedVisibility(visible = visible)
	{

		SsContinueSkipButton(
			state = name?.isNotEmpty() ?: false,
			onContinue = {
				onDone()
			},
			onSkip = {
				viewModel.problem.observableName.value = null

				onDone()
			}
		)

	}

}
