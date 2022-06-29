package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.SsBody
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsNumberOfAttemptsIcon

/**
 * Number of attempts question.
 */
@Composable
fun SsNumAttemptQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.attemptIndex

	// Create the question
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
	onDone : (String) -> Unit = {})
{

	println("Num attempt : $visible")

	// Initial text to show depending on if a number of attempts has been stored
	// or not
	val initial = viewModel.getInitialNumAttemptSubtitle()

	// Field value
	var fieldValue by remember { mutableStateOf(TextFieldValue(initial)) }

	// Subtitle
	var subtitle = viewModel.getNumAttemptsSubtitle(fieldValue.text, visible)

	// Request focus to show the keyboard
	val focusRequester = remember { FocusRequester() }

	// Body
	SsBody("Attempts", subtitle)
	{

		AnimatedVisibility(visible = visible)
		{

			BasicTextField(
				modifier = Modifier
					.focusRequester(focusRequester)
					.onFocusChanged {
						if (it.isFocused)
						{
							val selection = TextRange(0, initial.length)

							fieldValue = fieldValue.copy(selection = selection)
						}
					}
					.height(0.dp),
				value = fieldValue,
				onValueChange = {
					fieldValue = it
				},
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next,
					keyboardType = KeyboardType.Number),
				keyboardActions = KeyboardActions(
					onNext = {
						val numAttempt = viewModel.numAttemptsFromTextFieldValue(fieldValue)

						viewModel.problem.numAttempt = numAttempt
						fieldValue = fieldValue.copy(text = numAttempt.toString())

						onDone(fieldValue.text)
					}),
				cursorBrush = SolidColor(Color.Transparent),
				textStyle = TextStyle(
					color = Color.Transparent))

			// Focus the text field
			if (visible)
			{
				focusRequester.requestFocus()
			}

		}

	}

}
