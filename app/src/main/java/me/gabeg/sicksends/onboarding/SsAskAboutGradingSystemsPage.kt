package me.gabeg.sicksends.onboarding

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.gabeg.sicksends.SsLongClickButton
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.shared.SsSharedConstants
import me.gabeg.sicksends.shared.SsSharedDataStore

/**
 * Page asking the user what type of grades they will use.
 *
 * TODO: Change OutlinedButton to Button. Copy from the android github.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TypeOfGradingSystemPage()
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

	// Data store preferences
	val dataStore = SsSharedDataStore(LocalContext.current)
	val cons = SsSharedConstants(LocalContext.current)

	// All climb names
	val climbNames = dataStore.getAllClimbNames()

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(32.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Top)
	{

		// Title of app
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 16.dp),
			text = "What type of grading systems do you use?",
			fontSize = MaterialTheme.typography.h4.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center)

		// Description of app
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 24.dp),
			text = "This can always be changed later.\nLong press on a grading system to see an example.",
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Normal,
			textAlign = TextAlign.Center)

		// List all grading systems
		// TODO: Maybe use a regular column instead so all items are always
		// visible
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth())
		{

			// Boulder grading systems
			item {
				buildGradingSystems(climbNames[0], cons.allBoulderGrades,
					dataStore.getWillClimbBoulder()) { gradingSystem, isEnabled ->

					// Edit the grading system(s) being used for bouldering
					scope.launch {
						dataStore.editWillGradeBoulderWith(gradingSystem, isEnabled)
					}
				}
			}

			// Sport grading systems
			item {
				buildGradingSystems(climbNames[1], cons.allRopeGrades,
					dataStore.getWillClimbSport()) { gradingSystem, isEnabled ->

					// Edit the grading system(s) being used for bouldering
					scope.launch {
						dataStore.editWillGradeBoulderWith(gradingSystem, isEnabled)
					}
				}
			}

			// Top rope grading systems
			item {
				buildGradingSystems(climbNames[2], cons.allRopeGrades,
					dataStore.getWillClimbTopRope()) { gradingSystem, isEnabled ->

					// Edit the grading system(s) being used for bouldering
					scope.launch {
						dataStore.editWillGradeBoulderWith(gradingSystem, isEnabled)
					}
				}
			}

			// Trad grading system
			item {
				buildGradingSystems(climbNames[3], cons.allTradGrades,
					dataStore.getWillClimbTrad()) { gradingSystem, isEnabled ->

					// Edit the grading system(s) being used for bouldering
					scope.launch {
						dataStore.editWillGradeBoulderWith(gradingSystem, isEnabled)
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
fun buildGradingSystems(title : String, gradingSystems : List<String>,
	flow : Flow<Boolean>, onGradingSystemToggled: (gradingSystem: String,
		isEnabled: Boolean) -> Unit)
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

	// Whether or not the grading system for a type of climbing are visible
	var isVisible by remember { mutableStateOf(false) }

	// Things needed to show an example of a grading system
	val context = LocalContext.current
	val cons = SsSharedConstants(context)

	// Change the visibility state when the data store preference changes
	//scope.launch {
	LaunchedEffect(true)
	{
		flow.collect { newState ->
			isVisible = newState
		}
	}

	// Animate the visibility of the grading systems
	AnimatedVisibility(visible = isVisible)
	{

		Column(modifier = Modifier.padding(vertical = 8.dp))
		{

			// Type of climb
			buildGradingSystemTitle(title)

			// Buttons for each grading system
			buildGradingSystemButtons(gradingSystems,
				onGradingSystemToggled = { gradingSystem : String, isEnabled : Boolean ->
					onGradingSystemToggled(gradingSystem, isEnabled)
				},
				onGradingSystemLongClicked = { gradingSystem ->
					var example = cons.getExampleGrade(gradingSystem)

					scope.launch {
						Toast.makeText(context, example, Toast.LENGTH_SHORT).show()
					}
				}
			)

			// Example of the grading system
			//buildGradingSystemExampleGrade(example, anim)
		}
	}

}

/**
 * Build the grading system buttons.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun buildGradingSystemButtons(gradingSystems : List<String>,
	onGradingSystemToggled : (gradingSystem : String, isEnabled : Boolean)
		-> Unit,
	onGradingSystemLongClicked : (gradingSystem : String) -> Unit)
{

	// Width of the buttons
	val buttonWidth: Dp = LocalConfiguration.current.screenWidthDp.dp / 3

	// Organize each grading system  in a row that goes to the next line if
	// there is not enough space
	FlowRow(
		modifier = Modifier
			.padding(vertical = 4.dp),
		mainAxisSize = SizeMode.Expand,
		mainAxisAlignment = FlowMainAxisAlignment.Center)
	{

		// Iterate over each grading system
		gradingSystems.forEach { name ->

			// Whether or not the grading system button is selected
			var isChecked by remember { mutableStateOf(false) }

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
					isChecked = !isChecked

					onGradingSystemToggled(name, isChecked)
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
		TypeOfGradingSystemPage()
	}
}
