package me.gabeg.sicksends.addproblem.generic.question

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel.Companion.getSubtitle
import me.gabeg.sicksends.addproblem.generic.SsBody
import me.gabeg.sicksends.addproblem.generic.SsQuestion
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsGradingSystemIcon
import me.gabeg.sicksends.shared.getExampleGrade
import me.gabeg.sicksends.ui.SsButtonToggleGroup


/**
 * Number of attempts question.
 */
@Composable
fun SsGradingSystemQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index. This should be the zeroth index
	val index = viewModel.gradingSystem.index

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsGradingSystemIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsGradingsystemBody(
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
fun SsGradingsystemBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{
	// Get all the grading systems that are used
	//val allGradingSystems = viewModel.dataStore.observeAllGradingSystemsWillUse()
	val allGradingSystems = remember { mutableListOf<String>() }

	if (allGradingSystems.isEmpty())
	{
		println("Adding all grading systems!")
		allGradingSystems.addAll(0, viewModel.dataStore.getAllGradingSystemsWillUse())
	}

	// Grading system
	val gradingSystem = viewModel.problem.gradingSystem

	// Title and question
	val title = viewModel.gradingSystem.title
	val question = viewModel.gradingSystem.question

	// Subtitle
	val subtitle = getSubtitle(gradingSystem, question, visible)

	// Body
	SsBody(title, subtitle)
	{
		AnimatedVisibility(visible = visible)
		{
			Column()
			{

				var exampleGradingSystem by remember { mutableStateOf("") }

				// Show all the grading systems
				SsButtonToggleGroup(
					items = allGradingSystems,
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 16.dp),
					numPerRow = 2,
					singleSelection = true,
					toggleable = false,
					initialSelect = gradingSystem,
					onClick = { index, name, isChecked ->

						// A different grading system was chosen. Clear out the grade
						if (name != viewModel.problem.gradingSystem)
						{
							viewModel.problem.grade = ""
						}

						// Set the grading system
						viewModel.problem.gradingSystem = name

						// Done
						onDone()
					},
					onLongClick = { name ->
						exampleGradingSystem = name
					}
				)

				// Show the example grade
				if (exampleGradingSystem.isNotEmpty())
				{
					val example = getExampleGrade(exampleGradingSystem)

					Toast.makeText(LocalContext.current, example, Toast.LENGTH_SHORT).show()

					exampleGradingSystem = ""
				}

			}
		}
	}
}
