package me.gabeg.sicksends.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

		// Coroutine scope
		val scope = rememberCoroutineScope()

		// Get all the data stores
		val boulderDataStore = SsSharedBoulderDataStore(LocalContext.current)
		val sportDataStore = SsSharedSportDataStore(LocalContext.current)
		val topRopeDataStore = SsSharedTopRopeDataStore(LocalContext.current)
		val tradDataStore = SsSharedTradDataStore(LocalContext.current)
		val allDataStores = listOf(boulderDataStore, sportDataStore,
			topRopeDataStore, tradDataStore)

		// Iterate over each type of data store and type of climbing
		for (ds in allDataStores)
		{

			// Get the name of the type of climbing
			val name = ds.getClimbName()

			// Get whether this type of climbing will be done or not
			val isChecked by ds.getWillClimbFlow().asLiveData().observeAsState(false)

			// Create a row for each climbing type
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
					.clickable {
						scope.launch {
							ds.editWillClimb(!isChecked)
						}
					},
				horizontalArrangement = Arrangement.Start,
				verticalAlignment = Alignment.CenterVertically)
			{

				Checkbox(
					colors = CheckboxDefaults.colors(Color.Magenta),
					checked = isChecked,
					onCheckedChange = {
						scope.launch {
							ds.editWillClimb(!isChecked)
						}
					})

				Text(
					text = name,
					fontWeight = FontWeight.SemiBold)

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
