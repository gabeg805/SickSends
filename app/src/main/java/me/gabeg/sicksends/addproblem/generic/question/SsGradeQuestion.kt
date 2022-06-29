package me.gabeg.sicksends.addproblem.generic.question

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.gabeg.sicksends.addproblem.*
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.problem.ui.SsGradeIcon
import me.gabeg.sicksends.shared.getAllBoulderGradesForGradingSystem
import me.gabeg.sicksends.shared.getExampleGrade
import me.gabeg.sicksends.shared.getHowDidItFeelScaleName
import me.gabeg.sicksends.ui.SsButtonToggleGroup
import me.gabeg.sicksends.ui.SsDropdownMenuState
import me.gabeg.sicksends.ui.SsExposedDropdownMenu
import kotlin.math.round

/**
 * Grade question.
 */
@Composable
fun SsGradeQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Get the index
	val index = viewModel.gradeIndex

	// Create the question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsGradeIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsGradeBody(
				viewModel = viewModel,
				scrollState = scrollState,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the grade question.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsGradeBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState()
	val delayMilliSec = 500L

	// Whether or not the grade is done or a question has been answered
	var isDone by remember { mutableStateOf(false) }
	var isAnswered by remember { mutableStateOf(false) }

	// Determine the subtitle
	val subtitle by remember { derivedStateOf {
		if (isDone)
		{
			viewModel.getGradeSubtitle()
		}
		else if (isAnswered)
		{
			when (pagerState.currentPage)
			{
				0 -> viewModel.problem.gradingSystem
				1 -> viewModel.problem.grade
				2 -> viewModel.problem.howDidItFeel
				else -> ""
			}
		}
		else
		{
			when (pagerState.targetPage)
			{
				0 -> viewModel.gradingSystemQuestion
				1 -> viewModel.gradeQuestion
				2 -> viewModel.howDidItFeelQuestion
				else -> ""
			}
		}
	} }

	// Regular body of the grade section
	SsBody("Grade", subtitle, pagerState = if (visible) pagerState else null)
	{

		AnimatedVisibility(visible = visible)
		{

			HorizontalPager(
				state = pagerState,
				count = 3,
				modifier = Modifier
					.fillMaxHeight()
					//.fillMaxWidth()
					.nestedScroll(remember {
						object : NestedScrollConnection
						{
							override fun onPreScroll(
								available: Offset,
								source: NestedScrollSource
							): Offset
							{
								return if (available.y > 0) Offset.Zero
								else Offset(
									x = 0f,
									y = -scrollState.dispatchRawDelta(-available.y)
								)
							}
						}
					}))
			{ page: Int ->

				when (page)
				{
					0 ->
					{
						SsGradingSystemPage(
							viewModel = viewModel,
							onGradingSystemSelected = {
								isDone = false
								isAnswered = true

								scope.launch {
									delay(delayMilliSec)
									pagerState.animateScrollToPage(1)
									isAnswered = false
								}
							})
					}

					1 ->
					{
						SsGradePage(
							viewModel = viewModel,
							onGradeSelected = {
								isDone = false
								isAnswered = true

								scope.launch {
									delay(delayMilliSec)
									pagerState.animateScrollToPage(2)
									isAnswered = false
								}
							})
					}

					2 ->
					{
						SsHowDidItFeelPage(
							viewModel = viewModel,
							onDone = {
								isDone = false
								isAnswered = true

								scope.launch {
									delay(delayMilliSec)
									isDone = true
									isAnswered = false
									onDone(viewModel.getGradeSubtitle())
								}

							})

					}

				}
			}

		}

	}

}

/**
 * A page asking a user to select a grade.
 */
@Composable
fun SsGradePage(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	onGradeSelected : (grade : String) -> Unit)
{

	// Get all grades for a grading system
	val gradingSystem = viewModel.problem.gradingSystem
	val allGrades = getAllBoulderGradesForGradingSystem(gradingSystem)

	// Index of the saved grade
	val index = allGrades.indexOf(viewModel.problem.grade)

	// Save the state of the dropdown menu
	val menuState = remember { SsDropdownMenuState(index = index) }

	// Dropdown menu with all the grades
	SsExposedDropdownMenu(
		options = allGrades,
		state = menuState,
		onMenuItemClickedListener = { index, name ->
			viewModel.problem.grade = name

			onGradeSelected(name)
		})
}

/**
 * A page asking a user to select a grading system.
 */
@Composable
fun SsGradingSystemPage(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	onGradingSystemSelected : (gradingSystem: String) -> Unit)
{

	// Get all the grading systems that are used
	val allGradingSystems = viewModel.dataStore.observeAllGradingSystemsWillUse()

	// Things needed to show an example of a grading system
	var exampleGradingSystem by remember { mutableStateOf("") }

	// Show all the grading systems
	SsButtonToggleGroup(
		items = allGradingSystems,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp, vertical = 4.dp),
		numPerRow = 2,
		singleSelection = true,
		toggleable = false,
		initialSelect = viewModel.problem.gradingSystem,
		onClick = { name, isChecked ->
			viewModel.problem.gradingSystem = name

			onGradingSystemSelected(name)
		},
		onLongClick = { name ->
			exampleGradingSystem = name
		}
	)

	// Show the example grade
	if (exampleGradingSystem.isNotEmpty())
	{
		val context = LocalContext.current
		var example = getExampleGrade(exampleGradingSystem)

		LaunchedEffect(true)
		{
			Toast.makeText(context, example, Toast.LENGTH_SHORT).show()
		}

		exampleGradingSystem = ""
	}

}

/**
 * A page asking a user how a climb felt.
 */
@Composable
fun SsHowDidItFeelPage(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	onDone : (String) -> Unit = {})
{

	// Determine the initial how did it feel scale
	val initialFeelScale = viewModel.getInitialHowDidItFeelScale()

	// Determine the initial slider position
	val feelScale = viewModel.problem.howDidItFeelScale
	val normalFeel = SsHowDidItFeelType.NORMAL.value
	var sliderPosition by remember {
		mutableStateOf(feelScale?.toFloat() ?: normalFeel.toFloat())
	}

	// Subtitle
	var subtitle by remember { mutableStateOf(initialFeelScale) }

	// View
	Column()
	{

		Slider(
			value = sliderPosition,
			onValueChange = {
				sliderPosition = round(it)
				subtitle = getHowDidItFeelScaleName(sliderPosition.toInt())
			},
			valueRange = 0f..4f,
			onValueChangeFinished = {
				viewModel.problem.howDidItFeelScale = sliderPosition.toInt()

				onDone(subtitle)
			},
			steps = 3,
			colors = SliderDefaults.colors(
				thumbColor = MaterialTheme.colors.secondary,
				activeTrackColor = MaterialTheme.colors.secondary
			)
		)


		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically)
		{
			Text("Very\nEasy",
				textAlign = TextAlign.Center,
				fontSize = MaterialTheme.typography.body2.fontSize)

			Text("Easy",
				textAlign = TextAlign.Center,
				fontSize = MaterialTheme.typography.body2.fontSize)

			Text("Normal",
				textAlign = TextAlign.Center,
				fontSize = MaterialTheme.typography.body2.fontSize)

			Text("Hard",
				textAlign = TextAlign.Center,
				fontSize = MaterialTheme.typography.body2.fontSize)

			Text("Very\nHard",
				textAlign = TextAlign.Center,
				fontSize = MaterialTheme.typography.body2.fontSize)

		}

	}
}
