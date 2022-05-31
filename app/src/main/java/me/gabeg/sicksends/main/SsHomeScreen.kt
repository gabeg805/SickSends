package me.gabeg.sicksends.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import me.gabeg.sicksends.boulder.SsBoulderGradingSystem
import me.gabeg.sicksends.shared.SsSharedDataStore
import me.gabeg.sicksends.shared.getAllBoulderGradesForGradingSystem
import me.gabeg.sicksends.shared.getAllBoulderGradingSystems
import me.gabeg.sicksends.ui.SsDropdownMenu
import me.gabeg.sicksends.ui.SsDropdownMenuState

@Composable
fun SsHomeScreen(innerPadding : PaddingValues)
{

	//Column(modifier = Modifier.padding(innerPadding))
	ConstraintLayout(
		modifier = Modifier
			.padding(vertical = 24.dp, horizontal = 16.dp))
	{
		val (gradeIcon, gradeBody, gradeLine, feelIcon) = createRefs()

		var showGradeBody by remember { mutableStateOf(true) }
		var gradeSubtitle by remember { mutableStateOf("") }

		SsIcon(ref = gradeIcon)
		{
			Icon(Icons.Default.CloudDone,
				modifier = Modifier
					.align(Alignment.Center)
					.size(24.dp),
				contentDescription = "Yo")
		}

		SsGradeBody(gradeBody, gradeIcon, showGradeBody)
		{
			//showGradeBody = false
		}

		SsVerticalLine(
			ref = gradeLine,
			topRef = gradeIcon,
			bottomRef = gradeBody)

		SsIcon(
			ref = feelIcon,
			anchor = gradeLine.bottom)
		{
			Icon(Icons.Default.Face,
				modifier = Modifier
					.align(Alignment.Center)
					.size(24.dp),
				contentDescription = "Yo")
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
			}
			.padding(top = 0.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
	{

		// Title
		Text(title,
			fontSize = MaterialTheme.typography.h5.fontSize,
			fontWeight = FontWeight.Bold)

		// Subtitle
		Text(subtitle,
			modifier = Modifier
				.padding(bottom = 24.dp),
			fontSize = MaterialTheme.typography.body1.fontSize,
			fontWeight = FontWeight.Light)

		// Body content
		content()

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
	//var subtitle by remember { mutableStateOf(selectedGradingSystem.value) }

	// Get all grades for that grading system
	val allGrades = getAllBoulderGradesForGradingSystem(
			selectedGradingSystem.value)
		.toMutableList()
	allGrades.add(0, "Change grading system...")

	// Save the state of the dropdown menu
	val menuState = remember { SsDropdownMenuState() }

	// Whether or not to show the change grading system dialog
	val showDialog = remember { mutableStateOf(false) }

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
					showDialog.value = true
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
	if (showDialog.value)
	{
		SsChooseGradingSystemDialog(selectedGradingSystem, showDialog)
		{ name ->
			selectedGradingSystem.value = name
			selectedGrade.value = ""
		}
	}

}

@Composable
fun SsChooseGradingSystemDialog(
	selectedGradingSystem : MutableState<String>,
	showDialog : MutableState<Boolean>,
	onConfirmButton : (String) -> Unit = {})
{

	// The currently selected item in the dropdown menu
	var selectedName by remember { mutableStateOf("") }

	// Prepare the dropdown menu to contain all grading systems, as well as
	// automatically select the currently selected grading system
	val allGradingSystems  = getAllBoulderGradingSystems()
	val index = allGradingSystems.indexOf(selectedGradingSystem.value)
	val menuState = remember { SsDropdownMenuState(index) }

	// Show the dialog
	AlertDialog(

		// Title
		title = {
			Text("Choose a grading system...",
				fontWeight = FontWeight.Bold)
		},

		// Body
		text = {

			Column()
			{

				// Space between the title and the dropdown menu
				Text("")

				// Dropdown menu
				SsDropdownMenu(options = allGradingSystems, state = menuState)
				{ index, name ->
					selectedName = name
				}

			}
		},

		onDismissRequest = {
			showDialog.value = false
		},

		confirmButton = {
			TextButton(
				onClick = {
					showDialog.value = false
					onConfirmButton(selectedName)
				})
			{
				Text("OK")
			}
		},

		dismissButton = {
			TextButton(
				onClick = {
					showDialog.value = false
				})
			{
				Text("CANCEL")
			}
		})

}

/**
 * Create an icon bubble that appears next to each question.
 */
@Composable
fun ConstraintLayoutScope.SsIcon(
	ref : ConstrainedLayoutReference,
	anchor : ConstraintLayoutBaseScope.HorizontalAnchor? = null,
	content : @Composable BoxScope.() -> Unit = {})
{

	Box(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(anchor ?: parent.top)
				start.linkTo(parent.start)
			}
			.padding(horizontal = 16.dp)
			.size(48.dp)
			.background(color = Color.White, shape = CircleShape)
			.border(width = 3.dp, color = Color.Green, shape = CircleShape))
	{
		content()
	}
}

/**
 * Create a vertical line that connects two icon bubbles.
 */
@Composable
fun ConstraintLayoutScope.SsVerticalLine(
	ref : ConstrainedLayoutReference,
	topRef : ConstrainedLayoutReference,
	bottomRef : ConstrainedLayoutReference)
{
	Divider(
		modifier = Modifier
			.constrainAs(ref) {
				centerHorizontallyTo(topRef)

				top.linkTo(topRef.bottom)
				bottom.linkTo(bottomRef.bottom)

				height = Dimension.fillToConstraints
			}
			.width(3.dp),
		color = Color.Green)
}