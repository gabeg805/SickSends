package me.gabeg.sicksends.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.shared.*

/**
 * Page asking the user what type of grades they will use.
 *
 * TODO: Change OutlinedButton to Button. Copy from the android github.
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
		subtitle = "This can always be changed later.\nLong press on a grading system to see an example.")
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
					buildGradingSystems(ds) { gradingSystem, isEnabled ->

						// Edit the grading system(s) being used for bouldering
						scope.launch {
							ds.editWillGradeWith(gradingSystem, isEnabled)
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun buildGradingSystemButtons(
	dataStore: SsSharedBaseClimbingDataStore,
	onGradingSystemToggled : (gradingSystem : String, isEnabled : Boolean) -> Unit,
	onGradingSystemLongClicked : (gradingSystem : String) -> Unit)
{

	// Width of the buttons
	val buttonWidth: Dp = LocalConfiguration.current.screenWidthDp.dp / 3

	// All grading systems
	val allGradingSystems = dataStore.getAllGradingSystems()

	// Organize each grading system  in a row that goes to the next line if
	// there is not enough space
	FlowRow(
		modifier = Modifier
			.padding(vertical = 4.dp),
		mainAxisSize = SizeMode.Expand,
		mainAxisAlignment = FlowMainAxisAlignment.Center)
	{

		// Iterate over each grading system
		allGradingSystems.forEach { name ->

			// Whether or not the grading system button is selected
			//var isChecked by remember { mutableStateOf(false) }
			val isChecked by dataStore.getWillGradeWith(name).asLiveData().observeAsState(false)

			// Colors for the button
			val backgroundColor = if (isChecked) Color.Magenta else Color.White
			val textColor = if (isChecked) Color.White else Color.Black

			// Create grading system button
			SsLongClickOutlinedButton(
				modifier = Modifier
					.width(buttonWidth),
				colors = ButtonDefaults.buttonColors(
					backgroundColor = backgroundColor,
					contentColor = textColor),
				onClick = {
					//isChecked = !isChecked

					onGradingSystemToggled(name, !isChecked)
				},
				onLongClick = {
					onGradingSystemLongClicked(name)
				})
			{
				Text(name, softWrap = false)
			}

		}

	}
}

/**
 * Build the title of each grading system section.
 */
@Composable
fun buildGradingSystemTitle(title : String)
{
	Text(title,
		modifier = Modifier
			.padding(vertical = 8.dp),
		fontSize = MaterialTheme.typography.body1.fontSize,
		fontWeight = FontWeight.Bold)
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
