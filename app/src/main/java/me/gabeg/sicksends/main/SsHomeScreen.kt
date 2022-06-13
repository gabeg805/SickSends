package me.gabeg.sicksends.main

import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.*
import me.gabeg.sicksends.addproblem.SsAddBoulderProblemViewModel
import me.gabeg.sicksends.addproblem.SsAddProblemViewModel
import me.gabeg.sicksends.onboarding.buildGradingSystemButtons
import me.gabeg.sicksends.problem.ui.*
import me.gabeg.sicksends.shared.*
import me.gabeg.sicksends.ui.*
import java.lang.Exception
import kotlin.math.round

/**
 * Home screen.
 *
 * TODO: how did it feel, perceived grade, location name, location lat/lon
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsHomeScreen(
	innerPadding : PaddingValues,
	viewModel: SsAddBoulderProblemViewModel = hiltViewModel())
{

	val scrollState = rememberLazyListState()
	val pagerState = rememberPagerState()

	LaunchedEffect(true)
	{
		viewModel.show(0)
	}

	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 24.dp, horizontal = 16.dp),
		state = scrollState)
	{

		// TODO: Should highlight if on it and already done. Line should
		// only get highlighted after it is complete.
		/**
		 * Grade
		 */
		// TODO: This might change the color in an unwanted way
		item {
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
				index = 0,
				scrollState = scrollState)
		}

		/**
		 * Name
		 */
		item {
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
				index = 1,
				scrollState = scrollState)
		}

		/**
		 * Number of attempts
		 */
		item {
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
				index = 2,
				scrollState = scrollState)
		}

		/**
		 * Is flash?
		 */
		item {
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
				index = 3,
				scrollState = scrollState)
		}

		/**
		 * Is outdoor?
		 */
		item {
			SsQuestion(
				viewModel = viewModel,
				icon = { modifier ->
					SsOutdoorIcon(modifier = modifier)
				},
				body = { visible, onDone ->
					SsIsOutdoorBody(
						viewModel = viewModel,
						visible = visible,
						onDone = onDone)
				},
				index = 4,
				scrollState = scrollState)
		}

		/**
		 * Is project?
		 */
		item {
			SsQuestion(
				viewModel = viewModel,
				icon = { modifier ->
					SsProjectIcon(modifier = modifier)
				},
				body = { visible, onDone ->
					SsIsProjectBody(
						viewModel = viewModel,
						visible = visible,
						onDone = onDone)
				},
				index = 5,
				scrollState = scrollState)
		}

		/**
		 * Location
		 */
		item {
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
				index = 6,
				scrollState = scrollState)
		}

		/**
		 * Notes
		 */
		item {
			SsQuestion(
				viewModel = viewModel,
				icon = { modifier ->
					SsNoteIcon(modifier = modifier)
				},
				body = { visible, onDone ->
					SsNoteBody(
						viewModel = viewModel,
						visible = visible,
						onDone = onDone)
				},
				index = 7,
				scrollState = scrollState)
		}

		/**
		 * NEXT
		 */
		item {
			SsIcon(
				focus = false)
			{
				Icon(Icons.Default.Home,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}
		}

	}

}

/**
 * Question widget.
 */
@Composable
fun SsQuestion(
	viewModel: SsAddBoulderProblemViewModel,
	icon : @Composable BoxScope.(
		modifier : Modifier) -> Unit,
	body : @Composable (
		visible : Boolean,
		onDone : (String) -> Unit) -> Unit = {_,_ -> },
	index : Int,
	scrollState : LazyListState = rememberLazyListState(),
	onClick: () -> Unit = {
		viewModel.showOnly(index)
	},
	onDone : () -> Unit = {

		println("Doing onDone!")
		viewModel.showOnly(index+1)
		println("TEST Body : ${viewModel.getVisible(index)} || $index")

		//scrollState.animateScrollToItem(index+1)
	})
{

	var scope = rememberCoroutineScope()
	var isDone by remember { mutableStateOf(false) }
	val currentOnDone by rememberUpdatedState(onDone)

	// Whether the current element should be visible
	var isVisible by remember { viewModel.getVisible(index) }
	var isHighlighted = isVisible || viewModel.isAnswered(index)

	var bodyHeight by remember { mutableStateOf(0.dp) }
	val localDensity = LocalDensity.current

	Row()
	{

		Column(
			modifier = Modifier
				.height(bodyHeight),
			horizontalAlignment = Alignment.CenterHorizontally)
		{

			// Icon
			SsIcon(
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.size(48.dp),
				focus = isHighlighted,
				onClick = onClick)
			{
				icon(modifier = Modifier
					.align(Alignment.Center)
					.size(24.dp))
			}

			// Vertical line
			SsVerticalLine(
				modifier = Modifier.fillMaxHeight(),
				focus = isHighlighted,
				onClick = onClick)

		}

		// Body
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.clickable { onClick() }
				.padding(top = 0.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)
				.onGloballyPositioned { coord ->
					bodyHeight = with(localDensity) { coord.size.height.toDp() + 32.dp }
					//println("Height : $index   $bodyHeight")
				})
		{

			println("Body : $index || $isVisible")
			body(isVisible)
			{ subtitle ->
				if (viewModel.answers.size >= index+1)
				{
					viewModel.answers[index] = subtitle
				}
				else
				{
					viewModel.answers.add(index, subtitle)
				}

				println("Cancelling scope! $index")
				isDone = true
			}

		}

	}

	// Done with question
	if (isDone)
	{
		LaunchedEffect(true)
		{
			println("Delay! $index")
			delay(500)
			println("CAAAAAALL onDone! $index")
			currentOnDone()
			isDone = false
		}
	}

}

/**
 * Create the body.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsBody(
	title : String,
	subtitle : String,
	modifier : Modifier = Modifier,
	pagerState : PagerState? = null,
	content : @Composable () -> Unit = {})
{

	// Parent
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.Start)
	{

		Row(
			verticalAlignment = Alignment.CenterVertically
		)
		{

			// Title
			Text(title,
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Normal)

			// Page navigator
			if ((pagerState != null) && (pagerState.pageCount > 1))
			{
				Spacer(modifier = Modifier.padding(horizontal = 8.dp))

				Text("${pagerState.currentPage + 1} of ${pagerState.pageCount}",
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					fontWeight = FontWeight.Light)
			}

		}

		// Subtitle
		Text(subtitle,
			modifier = Modifier
				.padding(bottom = 24.dp),
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			fontSize = MaterialTheme.typography.h6.fontSize,
			fontWeight = FontWeight.SemiBold)

		// Body
		content()

	}

}

@Composable
fun SsGradeBodyGradingSystemPage(
	viewModel: SsAddBoulderProblemViewModel,
	onGradingSystemSelected : (gradingSystem: String) -> Unit)
{

	// Get the data store
	val dataStore = SsSharedBoulderDataStore(LocalContext.current)

	// Get all the grading systems that are used
	val allGradingSystems = dataStore.getAllGradingSystemsWillUse()

	// Determine the initial grading system
	// TODO: Make a way so that I can highlight this initial one
	val initialGradingSystem = viewModel.getInitialGradingSystem(dataStore)

	// Things needed to show an example of a grading system
	var exampleGradingSystem by remember { mutableStateOf("") }

	// Build all the grading systems
	buildGradingSystemButtons(allGradingSystems,
		onGradingSystemToggled = { gradingSystem, isEnabled ->
			viewModel.problem.gradingSystem = gradingSystem

			onGradingSystemSelected(gradingSystem)
		},
		onGradingSystemLongClicked = { gradingSystem ->
			exampleGradingSystem = gradingSystem
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

@Composable
fun SsGradeBodyGradePage(
	viewModel: SsAddBoulderProblemViewModel,
	gradingSystem: String,
	onGradeSelected : (grade : String) -> Unit)
{

	// Get all grades for that grading system
	val allGrades = getAllBoulderGradesForGradingSystem(gradingSystem)

	// Save the state of the dropdown menu
	val menuState = remember { SsDropdownMenuState() }

	// Dropdown menu with all the grades
	SsDropdownMenu(options = allGrades, state = menuState)
	{ index, name ->

		viewModel.problem.grade = name

		onGradeSelected(name)

	}
}

/**
 * Grade.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsGradeBody(
	viewModel: SsAddBoulderProblemViewModel,
	scrollState : LazyListState,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Grade : $visible")

	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState()
	var isDone by remember { mutableStateOf(false) }

	var gradingSystem by remember { mutableStateOf(viewModel.problem.gradingSystem) }
	var grade by remember { mutableStateOf(viewModel.problem.grade) }
	var feel by remember { mutableStateOf(viewModel.problem.howDidItFeel) }

	// Choose a grading system
	val questions = listOf(
		"What grading system was used?",
		"What was the grade?",
		"How did it feel?")
	var subtitle by remember { mutableStateOf(questions[0]) }
	var finalSubtitle by remember { mutableStateOf("") }

	// Regular body of the grade section
	SsBody("Grade", subtitle, pagerState = if (visible) pagerState else null)
	{

		if (!visible)
		{
			subtitle = viewModel.getGradeSubtitle()
		}

		AnimatedVisibility(visible = visible)
		{

			HorizontalPager(
				state = pagerState,
				count = 3,
				modifier = Modifier
					.fillMaxHeight()
					.fillMaxWidth()
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

						SsGradeBodyGradingSystemPage(
							viewModel = viewModel)
						{
							gradingSystem = it
							subtitle = it
							finalSubtitle = gradingSystem
							isDone = false

							scope.launch {
								pagerState.animateScrollToPage(1)
							}
						}

					}

					1 ->
					{

						SsGradeBodyGradePage(
							viewModel = viewModel,
							gradingSystem = gradingSystem)
						{
							grade = it
							subtitle = it
							finalSubtitle = "$gradingSystem  |  $grade"
							isDone = false

							scope.launch {
								pagerState.animateScrollToPage(2)
							}
						}

					}

					2 ->
					{

						SsGradeBodyHowDidItFeel(viewModel = viewModel)
						{
							feel = it
							finalSubtitle = "$gradingSystem  |  $grade  |  $feel"
							subtitle = finalSubtitle
							isDone = false

							onDone(finalSubtitle)

							isDone = true
						}

					}

				}

				if (isDone)
				{
					//viewModel.problem.gradingSystem
				//	viewModel.problem.grade
				//	viewModel.problem.howDidItFeelScale
				//	subtitle = "\u2027"
				}
				else
				{
					when (pagerState.currentPage)
					{
						0 -> subtitle = questions[0]
						1 -> subtitle = questions[1]
						2 -> subtitle = questions[2]
					}
				}

			}

		}

	}

}

/**
 * How did it feel?
 */
@Composable
fun SsGradeBodyHowDidItFeel(
	viewModel: SsAddBoulderProblemViewModel,
	onDone : (String) -> Unit = {})
{

	// Determine the initial grading system and slider position
	val initialFeelScale = viewModel.getInitialHowDidItFeelScale()
	var sliderPosition by remember { mutableStateOf(2f) }

	// Subtitle
	var subtitle by remember { mutableStateOf(initialFeelScale) }

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

/**
 * Create an icon bubble that appears next to each question.
 */
@Composable
fun SsIcon(
	modifier : Modifier = Modifier,
	focus : Boolean = true,
	onClick: () -> Unit = {},
	content : @Composable BoxScope.() -> Unit = {})
{

	var borderColor = if (focus) Color.Green else Color.Gray

	Box(
		modifier = modifier
			.clickable { onClick() }
			.background(color = Color.White, shape = CircleShape)
			.border(width = 3.dp, color = borderColor, shape = CircleShape))
	{
		content()
	}
}

/**
 * Was the problem flashed or not.
 */
@Composable
fun SsIsFlashBody(
	viewModel: SsAddBoulderProblemViewModel,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Flash : $visible")
	val isFlash = viewModel.problem.isFlash

	SsYesNoBody(
		title = "Flash",
		question = "Did you flash the problem?",
		initial = isFlash,
		visible = visible,
		onDone = { status, subtitle ->
			viewModel.problem.isFlash = status

			onDone(subtitle)
		})

}

/**
 * Was the problem outdoors or not.
 */
@Composable
fun SsIsOutdoorBody(
	viewModel: SsAddBoulderProblemViewModel,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Outdoor : $visible")
	val isOutdoor = viewModel.problem.isOutdoor

	SsYesNoBody(
		title = "Outdoor",
		question = "Was the problem outdoors?",
		initial = isOutdoor,
		visible = visible,
		onDone = { status, subtitle ->
			 viewModel.problem.isOutdoor = status
			 viewModel.current = status

			onDone(subtitle)
		})

}

/**
 * Was the problem projected or not.
 *
 * TODO: If is flash, then can't be project.
 */
@Composable
fun SsIsProjectBody(
	viewModel: SsAddBoulderProblemViewModel,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Is Project : $visible")
	val isProject = viewModel.problem.isProject

	SsYesNoBody(
		title = "Project",
		question = "Are you projecting this problem?",
		initial = isProject,
		visible = visible,
		onDone = { status, subtitle ->
			viewModel.problem.isProject = status
			viewModel.current = status

			onDone(subtitle)
		})

}

/**
 * Location of a climb.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsLocationBody(
	viewModel: SsAddBoulderProblemViewModel,
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

/**
 * Name of a climb.
 */
@Composable
fun SsNameBody(
	viewModel: SsAddBoulderProblemViewModel,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Name : $visible")
	val name = viewModel.getInitialName()

	SsTextFieldBody(
		title = "Name",
		question = "What is the name of the climb?",
		initial = name,
		singleLine = true,
		visible = visible,
		onDone = { name ->
			viewModel.problem.name = name

			onDone(name)
		})

}

/**
 * Notes for a climb.
 */
@Composable
fun SsNoteBody(
	viewModel: SsAddBoulderProblemViewModel,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	println("Note : $visible")
	val note = viewModel.getInitialNote()

	SsTextFieldBody(
		title = "Notes",
		question = "Do you have any notes for the climb?",
		initial = note,
		singleLine = false,
		visible = visible,
		onDone = { note ->
			viewModel.problem.note = note

			onDone(note)
		})

}

/**
 * Number of attempts.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsNumAttemptBody(
	viewModel: SsAddBoulderProblemViewModel,
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

/**
 * A body with a text field.
 *
 * TODO: Possible parameters:
 * singleLine
 * imeAction, might want new line if single line is not true, but then how to
 * go to next thing?
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsTextFieldBody(
	title : String,
	question : String = "",
	initial : String = "",
	singleLine : Boolean = false,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	// Subtitle
	var subtitle by remember { mutableStateOf(question) }

	// Text
	var text by remember { mutableStateOf(initial) }

	// Request focus to show the keyboard
	val focusRequester = remember { FocusRequester() }
	var imeAction = if (singleLine) ImeAction.Next else ImeAction.Default

	// Body
	SsBody(title, if (visible || (subtitle != question)) subtitle else "")
	{

		// Animate visibility as needed
		AnimatedVisibility(visible = visible)
		{

			// TODO: Capture "Enter" key press and go to next line
			OutlinedTextField(
				modifier = Modifier
					.focusRequester(focusRequester)
					.onKeyEvent {

						// Check if Enter or Tab or pressed to indicate that the
						// user is done with this question
						val key = it.nativeKeyEvent.keyCode
						val isDone = (key == KeyEvent.KEYCODE_ENTER) ||
							(key == KeyEvent.KEYCODE_TAB)

						// The user is done with this question
						if (singleLine && isDone)
						{
							subtitle = text

							onDone(subtitle)
							return@onKeyEvent true
						}
						else
						{
							return@onKeyEvent false
						}
					},
				value = text,
				onValueChange = {
					text = if (singleLine) it.replace("\n", "") else it
				},
				singleLine = singleLine,
				keyboardOptions = KeyboardOptions(imeAction = imeAction),
				keyboardActions = KeyboardActions(
					onNext = {
						subtitle = text

						onDone(subtitle)
					}))

			// Focus the text field, if it is visible
			if (visible)
			{
				focusRequester.requestFocus()
			}

		}

	}

}

/**
 * Create a vertical line that connects two icon bubbles.
 */
@Composable
fun SsVerticalLine(
	modifier : Modifier = Modifier,
	focus : Boolean = false,
	onClick: () -> Unit = {})
{

	var color = if (focus) Color.Green else Color.Gray

	Divider(
		modifier = modifier
			.clickable { onClick() }
			.width(3.dp),
		color = color)
}

/**
 * A body asking a yes/no question.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsYesNoBody(
	title : String,
	question: String,
	initial: Boolean? = null,
	visible : Boolean = true,
	onDone : (Boolean?, String) -> Unit = {_,_ -> })
{

	var subtitle by remember { mutableStateOf(question) }
	var isYes by remember { mutableStateOf<Boolean?>(initial) }

	// Body
	//SsBody(title, subtitle)
	SsBody(title, if (visible || (subtitle != question)) subtitle else "")
	{

		// Animate the visibility
		AnimatedVisibility(
			visible = visible,
			enter = expandVertically(
				expandFrom = Alignment.Top
			),
			exit = shrinkVertically(
				shrinkTowards = Alignment.Top
			))
		{

			var buttonSpacing = 16.dp
			var borderWidth = 2.dp
			var yesBorderColor = if (isYes == true) Color.Magenta else Color.LightGray
			var yesContentColor = if (isYes == true) Color.Magenta else Color.Black
			var noBorderColor = if (isYes == false) Color.Magenta else Color.LightGray
			var noContentColor = if (isYes == false) Color.Magenta else Color.Black

			// TODO: If the buttons took up 50% of the width, that might look
			// better?
			//modifier = Modifier.fillMaxWidth())
			//horizontalArrangement = Arrangement.SpaceAround)
			Row()
			{

				// Yes button
				SsYesIconTextButton(
					border = BorderStroke(
						width = borderWidth,
						color = yesBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = yesContentColor,
						backgroundColor = Color.Transparent))
				{
					isYes = true
					subtitle = it

					onDone(isYes, subtitle)
				}

				// Space
				Spacer(
					modifier = Modifier
						.padding(horizontal = buttonSpacing))

				// No button
				SsNoIconTextButton(
					border = BorderStroke(
						width = borderWidth,
						color = noBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = noContentColor,
						backgroundColor = Color.Transparent))
				{
						isYes = false
						subtitle = it

						onDone(isYes, subtitle)
				}

			}

		}

	}

}
