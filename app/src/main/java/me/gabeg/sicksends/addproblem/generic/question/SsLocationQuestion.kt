package me.gabeg.sicksends.addproblem.generic.question

import android.view.KeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsBody
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsLocationIcon

/**
 * Location question.
 */
@Composable
fun SsLocationQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.location)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsLocationIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsLocationBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Location of a climb.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsLocationBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	val location = viewModel.problem.locationName
	println("Location : $location || Visible : $visible")

	// Subtitle
	var subtitle by remember { mutableStateOf("") }

	// Text
	var text by remember { mutableStateOf("") }

	// Request focus to show the keyboard
	val focusRequester = remember { FocusRequester() }
	val imeAction = ImeAction.Next

	// Screen width
	val screenWidth : Dp = LocalConfiguration.current.screenWidthDp.dp - 32.dp

	// Regular body of the location section
	SsBody("Location", subtitle)
	{

		// Animate visibility as needed
		AnimatedVisibility(visible = visible)
		{

			Row(
				verticalAlignment = Alignment.CenterVertically)
			{

				OutlinedTextField(
					modifier = Modifier
						.width(screenWidth/2)
						.focusRequester(focusRequester)
						.onKeyEvent {

							// Check if Enter or Tab or pressed to indicate that the
							// user is done with this question
							val key = it.nativeKeyEvent.keyCode
							val isDone = (key == KeyEvent.KEYCODE_ENTER) ||
								(key == KeyEvent.KEYCODE_TAB)

							// The user is done with this question
							if (isDone)
							{
								subtitle = text

								onDone()
								return@onKeyEvent true
							}
							else
							{
								return@onKeyEvent false
							}
						},
					value = text,
					onValueChange = {
						text = it.replace("\n", "")
					},
					singleLine = true,
					keyboardOptions = KeyboardOptions(imeAction = imeAction),
					keyboardActions = KeyboardActions(
						onNext = {
							subtitle = text

							onDone()
						}))

				// Focus the text field, if it is visible
				if (visible)
				{
					focusRequester.requestFocus()
				}

				Spacer(modifier = Modifier.padding(horizontal = 8.dp))

				// Show map button
				Button(
					modifier = Modifier
						.height(IntrinsicSize.Max)
						.padding(0.dp),
					colors = ButtonDefaults.buttonColors(
						backgroundColor = Color.Magenta,
						contentColor = Color.White),
					shape = RoundedCornerShape(5.dp),
					onClick = { println("Map button clicked!") })
				{
					Text("Map...")
				}

			}

		}

	}

}

