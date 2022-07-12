package me.gabeg.sicksends.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.gabeg.sicksends.problem.ui.SsBoulderIcon
import me.gabeg.sicksends.problem.ui.SsSportIcon
import me.gabeg.sicksends.problem.ui.SsTopRopeIcon
import me.gabeg.sicksends.problem.ui.SsTradIcon
import me.gabeg.sicksends.shared.*

/*
 * Page asking the user what type of rock climbing they will do.
 */
@Composable
fun SsAskAboutTypeOfClimbsPage()
{

	// Page
	SsOnboardingPage(
		title = "What type of climbing do you do?",
		subtitle = "This can always be changed later.")
	{

		// Context
		val context = LocalContext.current

		// Coroutine scope
		val scope = rememberCoroutineScope()

		// Get all the data stores
		val boulderDataStore = SsSharedBoulderDataStore(context)
		val sportDataStore = SsSharedSportDataStore(context)
		val topRopeDataStore = SsSharedTopRopeDataStore(context)
		val tradDataStore = SsSharedTradDataStore(context)
		val allDataStores = listOf(boulderDataStore, sportDataStore,
			topRopeDataStore, tradDataStore)

		// Climb names and icon size
		val climbNames = getAllClimbNames()
		val size = 24.dp

		// Iterate over each type of data store and type of climbing
		for (ds in allDataStores)
		{

			// Get the name of the type of climbing
			val name = ds.getClimbName()

			// Get whether this type of climbing will be done or not
			val isChecked = ds.observeWillClimb()

			// Create a row for each climbing type
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp)
					.clickable {
						scope.launch {
							ds.editWillClimb(!isChecked)
						}
					},
				horizontalArrangement = Arrangement.Start,
				verticalAlignment = Alignment.CenterVertically)
			{

				// Icon
				when (name)
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
				Text(name,
					fontSize = MaterialTheme.typography.body1.fontSize,
					fontWeight = FontWeight.Bold,
					overflow = TextOverflow.Ellipsis)

				// Space
				Spacer(modifier = Modifier.weight(1f))

				// Switch
				Switch(
					modifier = Modifier
						.padding(start = 4.dp),
					checked = isChecked,
					onCheckedChange = {
						scope.launch {
							ds.editWillClimb(!isChecked)
						}
					},
					colors = SwitchDefaults.colors(Color.Magenta))

			}

		}

	}
}

/**
 * Preview the page.
 */
@Composable
@Preview(showBackground = true)
fun SsAskAboutTypeOfClimbsPagePreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		SsAskAboutTypeOfClimbsPage()
	}
}
