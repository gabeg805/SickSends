package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel.Companion.getSubtitle
import me.gabeg.sicksends.addproblem.generic.SsBody
import me.gabeg.sicksends.addproblem.generic.SsContinueSkipButton
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsAddIcon
import me.gabeg.sicksends.problem.ui.SsNumberOfAttemptsIcon
import me.gabeg.sicksends.problem.ui.SsSubtractIcon

/**
 * Number of attempts question.
 */
@Composable
fun SsNumAttemptQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.numAttempt)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsNumberOfAttemptsIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsNumAttemptBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the number of attempts question.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsNumAttemptBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Number of attempts
	val numAttempt by viewModel.problem.observableNumAttempt.observeAsState()
	val text = numAttempt?.toString() ?: ""

	println("Num attempt : $numAttempt || Visible : $visible")

	// Title and question
	val title = viewModel.numAttempt.title
	val question = viewModel.numAttempt.question

	// Subtitle
	var subtitle = getSubtitle(text, question, visible)

	// Body
	SsBody(title, subtitle)
	{

		// Whether to show the composables or not
		AnimatedVisibility(visible = visible)
		{

			// Column
			Column()
			{

				// Add/subtract number of attempts
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.height(IntrinsicSize.Min),
				horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically)
				{

					// Minus button
					SsLongClickOutlinedButton(
						modifier = Modifier
							.fillMaxHeight()
							.padding(end = 32.dp),
						onClick = {

							// New number of attempts
							val newNumAttempt =
								if ((numAttempt == null) || (numAttempt == 0L))
								{
									null
								}
								else
								{
									numAttempt!! -1
								}

							// Set the number of attempts
							viewModel.problem.observableNumAttempt.value = newNumAttempt
						})
					{
						SsSubtractIcon()
					}

					// Text field
					OutlinedTextField(
						modifier = Modifier
							.width(75.dp),
						value = text,
						onValueChange = {

							// Remove any non-number characters from the text
							val sanitizedText = it.onEach {
								if (it.digitToIntOrNull() == null)
									""
								else
									it
							}

							// New number of attempts
							val newNumAttempts = sanitizedText.toLongOrNull()

							// Set the number of attempts
							viewModel.problem.observableNumAttempt.value = newNumAttempts
						},
						singleLine = true,
						colors = TextFieldDefaults.textFieldColors(
							backgroundColor = Color.Transparent
						),
						textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
						keyboardOptions = KeyboardOptions(
							imeAction = ImeAction.Next,
							keyboardType = KeyboardType.Number),
						keyboardActions = KeyboardActions(
							onNext = {
								onDone()
							}),
					)


					// Plus button
					SsLongClickOutlinedButton(
						modifier = Modifier
							.fillMaxHeight()
							.padding(start = 32.dp),
						onClick = {

							// New number of attempts
							val newNumAttempt =
								if (numAttempt == null)
								{
									1
								}
								else
								{
									numAttempt!! + 1
								}

							// Set the number of attempts
							viewModel.problem.observableNumAttempt.value = newNumAttempt
						})
					{
						SsAddIcon()
					}

				}

				// Continue/skip button
				SsContinueSkipButton(
					state = (numAttempt != null),
					onClick = {
						onDone()
					})

			}

		}

	}

}
