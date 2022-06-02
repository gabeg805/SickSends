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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import me.gabeg.sicksends.boulder.SsBoulderGradingSystem
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

			var showGradeBody by remember { mutableStateOf(true) }
			var showNameBody by remember { mutableStateOf(false) }
			var showNumAttemptBody by remember { mutableStateOf(false) }
			var showIsFlashBody by remember { mutableStateOf(false) }
			var showIsOutdoorBody by remember { mutableStateOf(false) }
			var showIsProjectBody by remember { mutableStateOf(false) }
			var showNoteBody by remember { mutableStateOf(false) }

			var hasShownGradeBody by remember { mutableStateOf(true) }
			var hasShownNameBody by remember { mutableStateOf(false) }
			var hasShownNumAttemptBody by remember { mutableStateOf(false) }
			var hasShownIsFlashBody by remember { mutableStateOf(false) }
			var hasShownIsOutdoorBody by remember { mutableStateOf(false) }
			var hasShownIsProjectBody by remember { mutableStateOf(false) }
			var hasShownNoteBody by remember { mutableStateOf(false) }

			// TODO: Should highlight if on it and already done. Line should
			// only get highlighted after it is complete.
			/**
			 * Grade
			 */
			SsIcon(
				ref = gradeIcon,
				focus = showGradeBody)
			{
				Icon(Icons.Default.CloudDone,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsGradeBody(gradeBody, gradeIcon, showGradeBody)
			{
				showGradeBody = false
				showNameBody = true
			}

			SsVerticalLine(
				ref = gradeLine,
				topRef = gradeIcon,
				bottomRef = gradeBody,
				focus = showGradeBody)

			/**
			 * Name
			 */
			SsIcon(
				ref = nameIcon,
				anchor = gradeLine.bottom,
				focus = showNameBody)
			{
				Icon(Icons.Default.Face,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsNameBody(nameBody, nameIcon, showNameBody)
			{
				showNameBody = false
				showNumAttemptBody = true
			}

			SsVerticalLine(
				ref = nameLine,
				topRef = nameIcon,
				bottomRef = nameBody,
				focus = showNameBody)

			/**
			 * Number of attempts
			 */
			SsIcon(
				ref = numAttemptIcon,
				anchor = nameLine.bottom,
				focus = showNumAttemptBody)
			{
				Icon(Icons.Default.Lock,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsNumAttemptBody(numAttemptBody, numAttemptIcon, showNumAttemptBody)
			{
				showNumAttemptBody = false
				showIsFlashBody = true
			}

			SsVerticalLine(
				ref = numAttemptLine,
				topRef = numAttemptIcon,
				bottomRef = numAttemptBody,
				focus = showNumAttemptBody)

			/**
			 * Is flash?
			 */
			SsIcon(
				ref = isFlashIcon,
				anchor = numAttemptLine.bottom,
				focus = showIsFlashBody)
			{
				Icon(Icons.Default.Bolt,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsIsFlashBody(isFlashBody, isFlashIcon, showIsFlashBody)
			{
				showIsFlashBody = false
				showIsOutdoorBody = true
			}

			SsVerticalLine(
				ref = isFlashLine,
				topRef = isFlashIcon,
				bottomRef = isFlashBody,
				focus = showIsFlashBody)

			/**
			 * Is outdoor?
			 */
			SsIcon(
				ref = isOutdoorIcon,
				anchor = isFlashLine.bottom,
				focus = showIsOutdoorBody)
			{
				Icon(Icons.Default.Park,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsIsOutdoorBody(isOutdoorBody, isOutdoorIcon, showIsOutdoorBody)
			{
				showIsOutdoorBody = false
				showIsProjectBody = true
			}

			SsVerticalLine(
				ref = isOutdoorLine,
				topRef = isOutdoorIcon,
				bottomRef = isOutdoorBody,
				focus = showIsOutdoorBody)

			/**
			 * Is project?
			 */
			SsIcon(
				ref = isProjectIcon,
				anchor = isOutdoorLine.bottom,
				focus = showIsProjectBody)
			{
				Icon(Icons.Default.Construction,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsIsProjectBody(isProjectBody, isProjectIcon, showIsProjectBody)
			{
				showIsProjectBody = false
				showNoteBody = true
			}

			SsVerticalLine(
				ref = isProjectLine,
				topRef = isProjectIcon,
				bottomRef = isProjectBody,
				focus = showIsProjectBody)

			/**
			 * Notes
			 */
			SsIcon(
				ref = noteIcon,
				anchor = isProjectLine.bottom,
				focus = showNoteBody)
			{
				Icon(Icons.Default.Note,
					modifier = Modifier
						.align(Alignment.Center)
						.size(24.dp),
					contentDescription = "Yo")
			}

			SsNoteBody(noteBody, noteIcon, showNoteBody)
			{
				showNoteBody = true
			}

			SsVerticalLine(
				ref = noteLine,
				topRef = noteIcon,
				bottomRef = noteBody,
				focus = showNoteBody)

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
 * Create the body.
 */
@Composable
fun ConstraintLayoutScope.SsBody(
	title : String,
	subtitle : String,
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
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
			.padding(top = 0.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
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
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
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
	SsBody("Grade", subtitle.value, bodyRef, iconRef)
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
	content : @Composable BoxScope.() -> Unit = {})
{

	var borderColor = if (focus) Color.Green else Color.Gray

	Box(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(anchor ?: parent.top)
				start.linkTo(parent.start)
			}
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
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Is Flash : $visible")

	SsYesNoBody(
		title = "Is Flash?",
		bodyRef = bodyRef,
		iconRef = iconRef,
		visible = visible,
		onDone = onDone)

}

/**
 * Was the problem outdoors or not.
 */
@Composable
fun ConstraintLayoutScope.SsIsOutdoorBody(
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Is Outdoor : $visible")

	SsYesNoBody(
		title = "Is Outdoor?",
		bodyRef = bodyRef,
		iconRef = iconRef,
		visible = visible,
		onDone = onDone)

}

/**
 * Was the problem projected or not.
 *
 * TODO: If is flash, then can't be project.
 */
@Composable
fun ConstraintLayoutScope.SsIsProjectBody(
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Is Project : $visible")

	SsYesNoBody(
		title = "Is Project?",
		bodyRef = bodyRef,
		iconRef = iconRef,
		visible = visible,
		onDone = onDone)

}

/**
 * Name of a climb.
 */
@Composable
fun ConstraintLayoutScope.SsNameBody(
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Name : $visible")

	// TODO: Capture "Enter" key press and go to next line
	SsTextFieldBody(
		title = "Name",
		bodyRef = bodyRef,
		iconRef = iconRef,
		singleLine = true,
		visible = visible,
		onDone = onDone)

}

/**
 * Notes for a climb.
 */
@Composable
fun ConstraintLayoutScope.SsNoteBody(
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Note : $visible")

	SsTextFieldBody(
		title = "Notes",
		bodyRef = bodyRef,
		iconRef = iconRef,
		singleLine = false,
		visible = visible,
		onDone = onDone)

}

/**
 * Number of attempts.
 */
@Composable
fun ConstraintLayoutScope.SsNumAttemptBody(
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	println("Num attempt : $visible")
	var subtitle = remember { mutableStateOf("") }

	SsBody("Attempts", subtitle.value, bodyRef, iconRef)
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
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	singleLine : Boolean = false,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	var subtitle = remember { mutableStateOf("") }

	SsBody(title, subtitle.value, bodyRef, iconRef)
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
	focus : Boolean = false)
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
			.width(3.dp),
		color = color)
}

/**
 * A body asking a yes/no question.
 */
@Composable
fun ConstraintLayoutScope.SsYesNoBody(
	title : String,
	bodyRef : ConstrainedLayoutReference,
	iconRef : ConstrainedLayoutReference,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	var subtitle = remember { mutableStateOf("") }
	var isYes by remember { mutableStateOf<Boolean?>(null) }

	SsBody(title, subtitle.value, bodyRef, iconRef)
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
