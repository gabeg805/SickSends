package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.gabeg.sicksends.addproblem.SsContinueSkipButton
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.SsTextFieldBody
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
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
		title = "Name",
		question = viewModel.name.question,
		initial = name ?: "",
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
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp, bottom = 64.dp),
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
