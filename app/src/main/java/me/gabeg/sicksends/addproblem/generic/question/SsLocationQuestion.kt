package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.SsBody
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
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
	// Get the index
	val index = viewModel.locationIndex

	// Create the question
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
	onDone : (String) -> Unit = {})
{

	println("Location : $visible")

	var subtitle = remember { mutableStateOf("") }

	// Regular body of the location section
	SsBody("Location", subtitle.value)
	{

		AnimatedVisibility(visible = visible)
		{

			Row(
				horizontalArrangement = Arrangement.Center
			)
			{

				OutlinedButton(
					onClick = { /*TODO*/ })
				{
					Text("Enter name")
				}

				Spacer(modifier = Modifier.padding(horizontal = 8.dp))

				OutlinedButton(
					onClick = { /*TODO*/ })
				{
					Text("Find on map")
				}

			}

		}

	}

}
