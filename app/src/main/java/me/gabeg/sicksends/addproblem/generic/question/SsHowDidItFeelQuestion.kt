package me.gabeg.sicksends.addproblem.generic.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel.Companion.getSubtitle
import me.gabeg.sicksends.addproblem.generic.SsBody
import me.gabeg.sicksends.addproblem.generic.SsContinueSkipButton
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.problem.ui.SsThoughtBubbleIcon
import me.gabeg.sicksends.shared.getAllHowDidItFeelNames
import me.gabeg.sicksends.shared.howDidItFeelToName
import kotlin.math.round


/**
 * How did it feel question.
 */
@Composable
fun SsHowDidItFeelQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.howDiditFeel)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsThoughtBubbleIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsHowDidItFeelBody(
				viewModel = viewModel,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * A page asking a user how a climb felt.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsHowDidItFeelBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Determine the initial slider position
	val allHowDidItFeelNames = getAllHowDidItFeelNames()
	val howDidItFeel by viewModel.problem.observableHowDidItFeel.observeAsState()

	// Slider position
	var sliderPosition by remember {
		mutableStateOf(howDidItFeel?.toFloat()
				?: SsHowDidItFeelType.NORMAL.value.toFloat())
	}

	// Determine the initial how did it feel scale
	val initial = howDidItFeelToName(howDidItFeel)

	// Subtitle
	var subtitle = getSubtitle(initial, viewModel.howDiditFeel.question, visible)

	// View
	SsBody(viewModel.howDiditFeel.title, subtitle)
	{

		// Whether to show the composables or not
		AnimatedVisibility(visible = visible)
		{

			Column()
			{

				// Slider
				Slider(
					value = sliderPosition,
					onValueChange = {
						sliderPosition = round(it)
						//val index = sliderPosition.toInt()

						//subtitle = allHowDidItFeelNames[index]
					},
					valueRange = 0f..4f,
					onValueChangeFinished = {
						val index = sliderPosition.toInt()

						viewModel.problem.observableHowDidItFeel.value = index
						//viewModel.problem.howDidItFeelName = allHowDidItFeelNames[index]
						//viewModel.problem.howDidItFeel = sliderPosition.toInt()
						//viewModel.problem.howDidItFeelName = subtitle
						//subtitle = allHowDidItFeelNames[index]

						onDone()
					},
					steps = 3,
					colors = SliderDefaults.colors(
						thumbColor = MaterialTheme.colors.secondary,
						activeTrackColor = MaterialTheme.colors.secondary
					)
				)

				// Row of text labels indicating what each slider position is for
				Row(
					modifier = Modifier
						.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically)
				{

					// Iterate over each name
					allHowDidItFeelNames.forEach {

						// Replace any spaces with new lines so that each name does not
						// interfere with the next one and so that they all fit on one
						// line
						val text = it.replace(" ", "\n")

						// Text for each slider position
						Text(text,
							textAlign = TextAlign.Center,
							fontSize = MaterialTheme.typography.body2.fontSize)
					}

				}

				// Continue/skip button
				SsContinueSkipButton(
					state = (howDidItFeel != null),
					onClick = {
						onDone()
					})

			}
		}
	}
}
