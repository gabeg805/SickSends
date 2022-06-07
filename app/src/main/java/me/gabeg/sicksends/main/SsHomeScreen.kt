package me.gabeg.sicksends.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.launch
import me.gabeg.sicksends.boulder.SsBoulderGradingSystem
import me.gabeg.sicksends.problem.ui.*
import me.gabeg.sicksends.shared.SsSharedDataStore
import me.gabeg.sicksends.shared.getAllBoulderGradesForGradingSystem
import me.gabeg.sicksends.shared.getAllBoulderGradingSystems
import me.gabeg.sicksends.ui.SsAlertDialog
import me.gabeg.sicksends.ui.SsAlertDialogState
import me.gabeg.sicksends.ui.SsDropdownMenu
import me.gabeg.sicksends.ui.SsDropdownMenuState

/**
 * Home screen.
 *
 * TODO: how did it feel, perceived grade, location name, location lat/lon
 */
@Composable
fun SsHomeScreen(innerPadding : PaddingValues)
{

	val scrollState = rememberScrollState()

	// TODO: Switch to LazyColumn so that I can scroll to item
	//Column(modifier = Modifier.padding(innerPadding))
	Column(modifier = Modifier
		.verticalScroll(scrollState))
	{

		ConstraintLayout(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 24.dp, horizontal = 16.dp))
		{

			val (gradeIcon, gradeBody, gradeLine) = createRefs()
			val (nameIcon, nameBody, nameLine) = createRefs()
			val (numAttemptIcon, numAttemptBody, numAttemptLine) = createRefs()
			val (isFlashIcon, isFlashBody, isFlashLine) = createRefs()
			val (isOutdoorIcon, isOutdoorBody, isOutdoorLine) = createRefs()
			val (isProjectIcon, isProjectBody, isProjectLine) = createRefs()
			val (noteIcon, noteBody, noteLine) = createRefs()
			val nextIcon = createRef()

			var showGradeBody = remember { mutableStateOf(true) }
			var showNameBody = remember { mutableStateOf(false) }
			var showNumAttemptBody = remember { mutableStateOf(false) }
			var showIsFlashBody = remember { mutableStateOf(false) }
			var showIsOutdoorBody = remember { mutableStateOf(false) }
			var showIsProjectBody = remember { mutableStateOf(false) }
			var showNoteBody = remember { mutableStateOf(false) }

			var questionState = rememberSsQuestionState(
				listOf(showGradeBody, showNameBody, showNumAttemptBody,
					showIsFlashBody, showIsOutdoorBody, showIsProjectBody,
					showNoteBody))

			val scrollTo : Int by remember {
				derivedStateOf {
					if (showGradeBody.value)
					{
						0
					}
					else if (showNameBody.value)
					{
						200
					}
					else if (showNumAttemptBody.value)
					{
						400
					}
					else if (showIsFlashBody.value)
					{
						600
					}
					else if (showIsOutdoorBody.value)
					{
						800
					}
					else if (showIsProjectBody.value)
					{
						1000
					}
					else if (showNoteBody.value)
					{
						1200
					}
					else
					{
						50
					}
				}
			}

			println("Scroll : $scrollTo")

			LaunchedEffect(scrollTo) {
				scrollState.animateScrollTo(scrollTo)
			}

			// TODO: Should highlight if on it and already done. Line should
			// only get highlighted after it is complete.
			/**
			 * Grade
			 */
			// TODO: This might change the color in an unwanted way
			SsQuestion(
				iconRef = gradeIcon,
				bodyRef = gradeBody,
				lineRef = gradeLine,
				anchor = null,
				icon = { modifier ->
					SsGradeIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsGradeBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 0)

			/**
			 * Name
			 */
			SsQuestion(
				iconRef = nameIcon,
				bodyRef = nameBody,
				lineRef = nameLine,
				anchor = gradeLine.bottom,
				icon = { modifier ->
					SsNameIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsNameBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 1)

			/**
			 * Number of attempts
			 */
			SsQuestion(
				iconRef = numAttemptIcon,
				bodyRef = numAttemptBody,
				lineRef = numAttemptLine,
				anchor = nameLine.bottom,
				icon = { modifier ->
					SsNumberOfAttemptsIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsNumAttemptBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 2)

			/**
			 * Is flash?
			 */
			SsQuestion(
				iconRef = isFlashIcon,
				bodyRef = isFlashBody,
				lineRef = isFlashLine,
				anchor = numAttemptLine.bottom,
				icon = { modifier ->
					SsFlashIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsIsFlashBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 3)

			/**
			 * Is outdoor?
			 */
			SsQuestion(
				iconRef = isOutdoorIcon,
				bodyRef = isOutdoorBody,
				lineRef = isOutdoorLine,
				anchor = isFlashLine.bottom,
				icon = { modifier ->
					SsOutdoorIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsIsOutdoorBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 4)

			/**
			 * Is project?
			 */
			SsQuestion(
				iconRef = isProjectIcon,
				bodyRef = isProjectBody,
				lineRef = isProjectLine,
				anchor = isOutdoorLine.bottom,
				icon = { modifier ->
					SsProjectIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsIsProjectBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 5)

			/**
			 * Notes
			 */
			SsQuestion(
				iconRef = noteIcon,
				bodyRef = noteBody,
				lineRef = noteLine,
				anchor = isProjectLine.bottom,
				icon = { modifier ->
					SsNoteIcon(modifier = modifier)
				},
				body = { iconRef, bodyRef, visible, onClick, onDone ->
					SsNoteBody(iconRef, bodyRef, visible, onClick = onClick, onDone = onDone)
				},
				state = questionState,
				stateIndex = 6)
			{
				// TODO: Set show next here and in vertical line
			}

			/**
			 * NEXT
			 */
			SsIcon(
				ref = nextIcon,
				anchor = noteLine.bottom,
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
fun ConstraintLayoutScope.SsQuestion(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	lineRef : ConstrainedLayoutReference,
	anchor : ConstraintLayoutBaseScope.HorizontalAnchor? = null,
	icon : @Composable BoxScope.(
		modifier : Modifier) -> Unit,
	body : @Composable ConstraintLayoutScope.(
		iconRef : ConstrainedLayoutReference,
		bodyRef : ConstrainedLayoutReference,
		visible : Boolean,
		onClick : () -> Unit,
		onDone : () -> Unit) -> Unit,
	state : SsQuestionState = rememberSsQuestionState(),
	stateIndex : Int = -1,
	onClick: () -> Unit = {
		state.showOnly(stateIndex)
	},
	onDone : () -> Unit = {})
{

	// Whether the current element should be visible
	var isVisible = remember {
		state.getVisibility(stateIndex) ?: mutableStateOf(false)
	}

	// Whether to highlight the icon and line
	var isIconHighlighted by remember { mutableStateOf(false) }

	var isLineHighlighted by remember {
		mutableStateOf(state.getVisibility(stateIndex+1)?.value ?: false)
	}

	// Always show the icon
	if (isVisible.value)
	{
		isIconHighlighted = true
	}

	// Icon
	SsIcon(
		ref = iconRef,
		anchor = anchor,
		focus = isIconHighlighted,
		onClick = onClick)
	{
		icon(modifier = Modifier
			.align(Alignment.Center)
			.size(24.dp))
	}

	// Body
	body(iconRef, bodyRef, isVisible.value, onClick)
	{
		isLineHighlighted = true

		state.showOnly(stateIndex+1)
		onDone()
	}

	// Vertical line
	SsVerticalLine(
		ref = lineRef,
		topRef = iconRef,
		bottomRef = bodyRef,
		focus = isLineHighlighted,
		onClick = onClick)

}

/**
 * Remember the question state.
 */
@Composable
fun rememberSsQuestionState(initial : List<MutableState<Boolean>> = listOf())
	: SsQuestionState
{
	return remember { SsQuestionState(initial) }
}

/**
 * Question state.
 */
data class SsQuestionState(
	val initial : List<MutableState<Boolean>> = listOf()
)
{

	val allVisibility : MutableList<MutableState<Boolean>> = mutableListOf()

	init
	{
		allVisibility.addAll(initial)
	}

	fun getVisibility(index : Int) : MutableState<Boolean>?
	{
		if (!isValidIndex(index))
		{
			return null
		}

		return allVisibility[index]
	}

	fun hide(index : Int)
	{
		if (!isValidIndex(index))
		{
			return
		}

		allVisibility[index].value = false
	}

	fun hideAll()
	{
		for (i in allVisibility.indices)
		{
			hide(i)
		}
	}

	fun isValidIndex(index : Int) : Boolean
	{
		return (index >= 0) && (index < allVisibility.size)
	}

	fun isVisible(index : Int) : Boolean
	{
		if (!isValidIndex(index))
		{
			return false
		}

		return allVisibility[index].value
	}

	fun show(index : Int)
	{
		if (!isValidIndex(index))
		{
			return
		}

		allVisibility[index].value = true
	}

	fun showOnly(index : Int)
	{
		hideAll()
		show(index)
	}

}

/**
 * Create the body.
 *
 * TODO: Change the order of the body and icon refs to match everything else.
 */
@Composable
fun ConstraintLayoutScope.SsBody(
	title : String,
	subtitle : String,
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	onClick: () -> Unit = {},
	content : @Composable () -> Unit = {})
{

	Column(
		modifier = Modifier
			.constrainAs(bodyRef) {
				top.linkTo(iconRef.top)
				start.linkTo(iconRef.end)
				end.linkTo(parent.end)

				width = Dimension.fillToConstraints
			}
			.clickable { onClick() }
			.padding(top = 0.dp, bottom = 32.dp, start = 8.dp, end = 8.dp),
		horizontalAlignment = Alignment.Start)
	{

		// Title
		Text(title,
			fontSize = MaterialTheme.typography.h5.fontSize,
			fontWeight = FontWeight.Bold)

		// Subtitle
		if (subtitle.isNotEmpty())
		{
			Text(subtitle,
				modifier = Modifier
					.padding(bottom = 24.dp),
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Light)
		}

		// Body content
		content()

	}

}

/**
 * Dialog to choose a different grading system.
 */
@Composable
fun SsChooseGradingSystemDialog(
	selectedGradingSystem : MutableState<String>,
	state : SsAlertDialogState,
	onConfirmClicked : (String) -> Unit = {})
{

	// The currently selected item in the dropdown menu
	var selectedName by remember { mutableStateOf("") }

	// Prepare the dropdown menu to contain all grading systems, as well as
	// automatically select the currently selected grading system
	val allGradingSystems  = getAllBoulderGradingSystems()
	val index = allGradingSystems.indexOf(selectedGradingSystem.value)

	// Show the dialog
	SsAlertDialog(
		title = "Choose a grading system...",
		onConfirmClicked = { onConfirmClicked(selectedName) },
		state = state)
	{

		// Dropdown menu
		SsDropdownMenu(
			options = allGradingSystems,
			index = index)
		{ index, name ->
			selectedName = name
		}

	}

}

/**
 * Grade.
 */
@Composable
fun ConstraintLayoutScope.SsGradeBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Grade : $visible")
	// Get all the grading systems that the user will use
	val dataStore = SsSharedDataStore(LocalContext.current)
	val allGradingSystemsWillUse = dataStore.getAllBoulderGradingSystemsWillUse()

	// Choose a grading system
	var selectedGradingSystem = remember { mutableStateOf("V Scale") }
	var selectedGrade = remember { mutableStateOf("") }
	var subtitle = remember {
		derivedStateOf {
			if (selectedGrade.value.isNullOrEmpty())
			{
				selectedGradingSystem.value
			}
			else
			{
				selectedGradingSystem.value + ", " + selectedGrade.value
			}
		}
	}

	// Get all grades for that grading system
	val allGrades = getAllBoulderGradesForGradingSystem(
			selectedGradingSystem.value)
		.toMutableList()
	allGrades.add(0, "Change grading system...")

	// Save the state of the dropdown menu
	val menuState = remember { SsDropdownMenuState() }

	// Whether or not to show the change grading system dialog
	val dialogState = remember { SsAlertDialogState() }

	// Regular body of the grade section
	SsBody("Grade", subtitle.value, iconRef, bodyRef, onClick = onClick)
	{

		AnimatedVisibility(visible = visible)
		{

			// Dropdown menu with all the grades
			SsDropdownMenu(options = allGrades, state = menuState)
			{ index, name ->

				// Show the change the grading system dialog
				if (index == 0)
				{
					menuState.clearSelected()
					dialogState.show()
				}
				// Done with this section
				else
				{
					selectedGrade.value = name
					onDone()
				}

			}

		}

	}

	// Show the dialog to choose/change the grading system
	if (dialogState.isVisible)
	{
		SsChooseGradingSystemDialog(selectedGradingSystem, dialogState)
		{ name ->
			selectedGradingSystem.value = name
			selectedGrade.value = ""
		}
	}

}

/**
 * Create an icon bubble that appears next to each question.
 */
@Composable
fun ConstraintLayoutScope.SsIcon(
	ref : ConstrainedLayoutReference,
	anchor : ConstraintLayoutBaseScope.HorizontalAnchor? = null,
	focus : Boolean = true,
	onClick: () -> Unit = {},
	content : @Composable BoxScope.() -> Unit = {})
{

	var borderColor = if (focus) Color.Green else Color.Gray

	Box(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(anchor ?: parent.top)
				start.linkTo(parent.start)
			}
			.clickable { onClick() }
			.padding(horizontal = 16.dp)
			.size(48.dp)
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
fun ConstraintLayoutScope.SsIsFlashBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Is Flash : $visible")

	SsYesNoBody(
		title = "Is Flash?",
		iconRef = iconRef,
		bodyRef = bodyRef,
		visible = visible,
		onClick = onClick,
		onDone = onDone)

}

/**
 * Was the problem outdoors or not.
 */
@Composable
fun ConstraintLayoutScope.SsIsOutdoorBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Is Outdoor : $visible")

	SsYesNoBody(
		title = "Is Outdoor?",
		iconRef = iconRef,
		bodyRef = bodyRef,
		visible = visible,
		onClick = onClick,
		onDone = onDone)

}

/**
 * Was the problem projected or not.
 *
 * TODO: If is flash, then can't be project.
 */
@Composable
fun ConstraintLayoutScope.SsIsProjectBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Is Project : $visible")

	SsYesNoBody(
		title = "Is Project?",
		iconRef = iconRef,
		bodyRef = bodyRef,
		visible = visible,
		onClick = onClick,
		onDone = onDone)

}

/**
 * Name of a climb.
 */
@Composable
fun ConstraintLayoutScope.SsNameBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Name : $visible")

	// TODO: Capture "Enter" key press and go to next line
	SsTextFieldBody(
		title = "Name",
		iconRef = iconRef,
		bodyRef = bodyRef,
		singleLine = true,
		visible = visible,
		onDone = onDone)

}

/**
 * Notes for a climb.
 */
@Composable
fun ConstraintLayoutScope.SsNoteBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Note : $visible")

	SsTextFieldBody(
		title = "Notes",
		iconRef = iconRef,
		bodyRef = bodyRef,
		singleLine = false,
		visible = visible,
		onDone = onDone)

}

/**
 * Number of attempts.
 */
@Composable
fun ConstraintLayoutScope.SsNumAttemptBody(
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	println("Num attempt : $visible")
	var subtitle = remember { mutableStateOf("") }

	SsBody("Attempts", subtitle.value, iconRef, bodyRef, onClick = onClick)
	{

		AnimatedVisibility(visible = visible)
		{

			OutlinedTextField(
				value = subtitle.value,
				onValueChange = { subtitle.value = it },
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next,
					keyboardType = KeyboardType.Number),
				keyboardActions = KeyboardActions(onNext = { onDone() }))

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
@Composable
fun ConstraintLayoutScope.SsTextFieldBody(
	title : String,
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	singleLine : Boolean = false,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	var subtitle = remember { mutableStateOf("") }

	SsBody(title, subtitle.value, iconRef, bodyRef, onClick = onClick)
	{

		AnimatedVisibility(visible = visible)
		{

			var imeAction = if (singleLine) ImeAction.Next else ImeAction.Default

			// TODO: Capture "Enter" key press and go to next line
			OutlinedTextField(
				value = subtitle.value,
				onValueChange = { subtitle.value = it },
				singleLine = singleLine,
				keyboardOptions = KeyboardOptions(imeAction = imeAction),
				keyboardActions = KeyboardActions(onNext = { onDone() }))

		}

	}

}

/**
 * Create a vertical line that connects two icon bubbles.
 */
@Composable
fun ConstraintLayoutScope.SsVerticalLine(
	ref : ConstrainedLayoutReference,
	topRef : ConstrainedLayoutReference,
	bottomRef : ConstrainedLayoutReference,
	focus : Boolean = false,
	onClick: () -> Unit = {})
{

	var color = if (focus) Color.Green else Color.Gray

	Divider(
		modifier = Modifier
			.constrainAs(ref) {
				centerHorizontallyTo(topRef)

				top.linkTo(topRef.bottom)
				bottom.linkTo(bottomRef.bottom)

				height = Dimension.fillToConstraints
			}
			.clickable { onClick() }
			.width(3.dp),
		color = color)
}

/**
 * A body asking a yes/no question.
 */
@Composable
fun ConstraintLayoutScope.SsYesNoBody(
	title : String,
	iconRef : ConstrainedLayoutReference,
	bodyRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onClick: () -> Unit = {},
	onDone : () -> Unit = {})
{

	var subtitle = remember { mutableStateOf("") }
	var isYes by remember { mutableStateOf<Boolean?>(false) }

	SsBody(title, subtitle.value, iconRef, bodyRef, onClick = onClick)
	{

		AnimatedVisibility(visible = visible)
		{
			var iconPadding = 8.dp
			var buttonSpacing = 16.dp

			var borderWidth = 2.dp
			var yesBorderColor = if (isYes == true) Color.Magenta else Color.Gray
			var yesContentColor = if (isYes == true) Color.Magenta else Color.Gray
			var noBorderColor = if (isYes == false) Color.Magenta else Color.Gray
			var noContentColor = if (isYes == false) Color.Magenta else Color.Gray

			// TODO: If the buttons took up 50% of the width, that might look
			// better?
			//modifier = Modifier.fillMaxWidth())
			//horizontalArrangement = Arrangement.SpaceAround)
			Row()
			{

				OutlinedButton(
					onClick = {
						isYes = true
						subtitle.value = "Yes"

						onDone()
					},
					border = BorderStroke(
						width = borderWidth,
						color = yesBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = yesContentColor,
						backgroundColor = Color.Transparent))
				{
					Icon(Icons.Default.Check,
						modifier = Modifier
							.padding(end = iconPadding),
						contentDescription = "Yes")
					Text("YES")
				}

				Spacer(modifier = Modifier.padding(horizontal = buttonSpacing))

				OutlinedButton(
					onClick = {
						isYes = false
						subtitle.value = "No"

						onDone()
					},
					border = BorderStroke(
						width = borderWidth,
						color = noBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = noContentColor,
						backgroundColor = Color.Transparent))
				{
					Icon(Icons.Default.Close,
						modifier = Modifier
							.padding(end = iconPadding),
					contentDescription = "No")
					Text("NO")
				}

			}

		}

	}

}
