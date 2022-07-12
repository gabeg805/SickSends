package me.gabeg.sicksends.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import me.gabeg.sicksends.problem.ui.SsBoulderIcon
import me.gabeg.sicksends.problem.ui.SsSportIcon
import me.gabeg.sicksends.problem.ui.SsTopRopeIcon
import me.gabeg.sicksends.problem.ui.SsTradIcon
import me.gabeg.sicksends.shared.*
import me.gabeg.sicksends.ui.SsButtonToggleGroup

/**
 * Page asking the user what type of grades they will use.
 */
@Composable
fun SsAskAboutGradingSystemPage()
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

	// Get all data stores
	val boulderDataStore = SsSharedBoulderDataStore(LocalContext.current)
	val sportDataStore = SsSharedSportDataStore(LocalContext.current)
	val topRopeDataStore = SsSharedTopRopeDataStore(LocalContext.current)
	val tradDataStore = SsSharedTradDataStore(LocalContext.current)
	val allDataStores = listOf(boulderDataStore, sportDataStore,
		topRopeDataStore, tradDataStore)

	// Page
	SsOnboardingPage(
		title = "What type of grading systems do you use?",
		subtitle = "This can always be changed later.\nLong press on a grading system to see an example.\nD = Default")
	{

		// List all grading systems
		// TODO: Maybe use a regular column instead so all items are always
		// visible
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth())
		{

			// Iterate over each type of data store and type of climbing
			for (ds in allDataStores)
			{

				item {

					val willUse = ds.observeAllGradingSystemsWillUse()

					buildGradingSystems(ds) { gradingSystem, isEnabled ->

						// Edit the grading system(s) being used for bouldering
						scope.launch {
							ds.editWillGradeWith(gradingSystem, isEnabled)

							// Enabled a grading system
							if (isEnabled)
							{
								// No other grading system has been clicked.
								// This one is the default grading system
								if (willUse.size == 0)
								{
									ds.editDefaultGradingSystem(gradingSystem)
								}
							}
							// Disabled a grading system
							else
							{
								// Get the current default grading system
								val defaultGradingSystem = ds.getDefaultGradingSystem()

								// The current default grading system was just
								// deselected. Need to find the next grading
								// system that should be the default
								if (gradingSystem == defaultGradingSystem)
								{
									// Find the next grading system that should
									// be the default
									val nextDefaultGradingSystem = willUse.firstOrNull {
										it != gradingSystem
									}

									// Set the default grading system. If no
									// defaultwas found, this will clear the
									// default
									ds.editDefaultGradingSystem(nextDefaultGradingSystem
										?: "")
								}
							}

						}
					}
				}

			}

		}

	}
}

/**
 * Build the grading systems for a type of climbing.
 */
@Composable
fun buildGradingSystems(
	dataStore: SsSharedBaseClimbingDataStore,
	onGradingSystemToggled: (gradingSystem: String, isEnabled: Boolean) -> Unit)
{

	// Title
	val title = dataStore.getClimbName()

	// Whether or not the grading system for a type of climbing are visible
	val isVisible by dataStore.getWillClimbFlow().asLiveData().observeAsState(false)

	// Things needed to show an example of a grading system
	var exampleGradingSystem by remember { mutableStateOf("") }

	// Animate the visibility of the grading systems
	AnimatedVisibility(visible = isVisible)
	{

		Column(modifier = Modifier.padding(vertical = 8.dp))
		{

			// Type of climb
			buildGradingSystemTitle(title)

			// Buttons for each grading system
			buildGradingSystemButtons(
				dataStore = dataStore,
				onGradingSystemToggled = { gradingSystem : String, isEnabled : Boolean ->
					onGradingSystemToggled(gradingSystem, isEnabled)
				},
				onGradingSystemLongClicked = { gradingSystem ->
					exampleGradingSystem = gradingSystem
				}
			)

			// Example of the grading system
			//buildGradingSystemExampleGrade(example, anim)
		}
	}

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
 * Build the grading system buttons.
 */
@Composable
fun buildGradingSystemButtons(
	dataStore: SsSharedBaseClimbingDataStore,
	onGradingSystemToggled : (gradingSystem : String, isEnabled : Boolean) -> Unit,
	onGradingSystemLongClicked : (gradingSystem : String) -> Unit)
{

	// All grading systems
	val allGradingSystems = dataStore.getAllGradingSystems()

	// Default grading system
	val defaultGradingSystem = dataStore.observeDefaultGradingSystem()

	// Buttons
	SsButtonToggleGroup(
		items = allGradingSystems,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp, vertical = 12.dp),
		numPerRow = 2,
		checkCriteria = { dataStore.observeWillGradeWith(it) },
		startButtonContent = { name ->

			AnimatedVisibility(visible = (name == defaultGradingSystem))
			{
				Box(
					modifier = Modifier
						.size(20.dp)
						.background(color = Color.White, shape = CircleShape),
					contentAlignment = Alignment.Center)
				{
					Text("D",
						modifier = Modifier
							.padding(start = 1.dp),
						color = Color.Magenta,
						fontSize = MaterialTheme.typography.caption.fontSize,
						fontWeight = FontWeight.Bold,
						textAlign = TextAlign.Center)
				}
			}

		},
		textButtonContent = { name ->
			Text(name,
				modifier = Modifier
					.padding(horizontal = if (name == defaultGradingSystem) 8.dp else 0.dp),
				overflow = TextOverflow.Ellipsis,
				softWrap = false)
		},
		onClick = { index, name, isChecked ->
			onGradingSystemToggled(name, isChecked)
		},
		onLongClick = { name ->
			onGradingSystemLongClicked(name)
		}
	)

}

///**
// * Build the grading system buttons.
// */
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun buildGradingSystemButtons(
//	dataStore: SsSharedBaseClimbingDataStore,
//	onGradingSystemToggled : (gradingSystem : String, isEnabled : Boolean) -> Unit,
//	onGradingSystemLongClicked : (gradingSystem : String) -> Unit)
//{
//
//	// Width of the buttons
//	val buttonWidth : Dp = LocalConfiguration.current.screenWidthDp.dp / 2 - 48.dp
//
//	// All grading systems
//	val allGradingSystems = dataStore.getAllGradingSystems()
//
//	// Default grading system
//	val defaultGradingSystem = dataStore.observeDefaultGradingSystem()
//
//	// Organize each grading system  in a row that goes to the next line if
//	// there is not enough space
//	FlowRow(
//		modifier = Modifier
//			.padding(vertical = 4.dp),
//		mainAxisSize = SizeMode.Expand,
//		mainAxisAlignment = FlowMainAxisAlignment.Center)
//	{
//
//		// Iterate over each grading system
//		allGradingSystems.forEach { name ->
//
//			// Whether or not the grading system button is selected
//			val isChecked = dataStore.observeWillGradeWith(name)
//			val isDefault = (name == defaultGradingSystem)
//
//			// Colors for the button
//			val backgroundColor = if (isChecked) Color.Magenta else Color.White
//			val textColor = if (isChecked) Color.White else Color.Black
//
//			// Create grading system button
//			SsLongClickOutlinedButton(
//				modifier = Modifier
//					.width(buttonWidth),
//				colors = ButtonDefaults.buttonColors(
//					backgroundColor = backgroundColor,
//					contentColor = textColor),
//				onClick = {
//					onGradingSystemToggled(name, !isChecked)
//				},
//				onLongClick = {
//					onGradingSystemLongClicked(name)
//				})
//			{
//
//				AnimatedVisibility(visible = isDefault)
//				{
//					Box(
//						modifier = Modifier
//							.size(20.dp)
//							.background(color = Color.White, shape = CircleShape),
//						contentAlignment = Alignment.Center)
//					{
//						Text("D",
//							modifier = Modifier
//								.padding(start = 1.dp),
//							color = Color.Magenta,
//							fontSize = MaterialTheme.typography.caption.fontSize,
//							fontWeight = FontWeight.Bold,
//							textAlign = TextAlign.Center)
//					}
//				}
//
//				Text(name,
//					modifier = Modifier
//						.padding(horizontal = if (isDefault) 8.dp else 0.dp),
//					overflow = TextOverflow.Ellipsis,
//					softWrap = false)
//
//			}
//
//		}
//
//	}
//}

/**
 * Build the title of each grading system section.
 */
@Composable
fun buildGradingSystemTitle(title : String)
{

	// Title row
	Row(
		modifier = Modifier
			.padding(vertical = 16.dp),
		verticalAlignment = Alignment.CenterVertically)
	{

		// Icon
		val climbNames = getAllClimbNames()
		val size = 24.dp

		when (title)
		{
			climbNames[0] -> SsBoulderIcon(modifier = Modifier.size(size))
			climbNames[1] -> SsSportIcon(modifier = Modifier.size(size))
			climbNames[2] -> SsTopRopeIcon(modifier = Modifier.size(size))
			climbNames[3] -> SsTradIcon(modifier = Modifier.size(size))
			else -> {}
		}

		// Space
		Spacer(modifier = Modifier.padding(horizontal = 12.dp))

		// Title
		Text(title,
			//modifier = Modifier
			//	.padding(vertical = 8.dp),
			fontSize = MaterialTheme.typography.body1.fontSize,
			fontWeight = FontWeight.Bold,
			overflow = TextOverflow.Ellipsis)

	}
}

/**
 * Preview the page.
 */
@Composable
@Preview(showBackground = true)
fun TypeOfGradingSystemPagePreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		SsAskAboutGradingSystemPage()
	}
}
