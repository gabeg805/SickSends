package me.gabeg.sicksends.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.gabeg.sicksends.shared.SsSharedDataStore

/*
 * Page asking the user what type of rock climbing they will do.
 */
@Composable
fun TypeOfClimbingPage()
{

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
			text = "What type of climbing do you do?",
			fontSize = MaterialTheme.typography.h4.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center)

		// Description of app
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 24.dp),
			text = "This can always be changed later.",
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Normal,
			textAlign = TextAlign.Center)

		// All supported types of climbing
		val dataStore = SsSharedDataStore(LocalContext.current)
		val climbingTypes = dataStore.getAllClimbNames()
		val scope = rememberCoroutineScope()

		// Create a row for each climbing type
		for (name in climbingTypes) {
			val isChecked = remember { mutableStateOf(false) }

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
					.clickable {
						isChecked.value = !isChecked.value
						scope.launch {
							saveTypeOfClimbUserWillDo(dataStore, name, isChecked.value)
						}
					},
				horizontalArrangement = Arrangement.Start,
				verticalAlignment = Alignment.CenterVertically)
			{

				Checkbox(
					colors = CheckboxDefaults.colors(Color.Magenta),
					checked = isChecked.value,
					onCheckedChange = {
						isChecked.value = it
						scope.launch {
							saveTypeOfClimbUserWillDo(dataStore, name, isChecked.value)
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
 * Save the type of climb a user will do.
 */
suspend fun saveTypeOfClimbUserWillDo(dataStore: SsSharedDataStore,
	name : String, status : Boolean)
{
	val climbingTypes = dataStore.getAllClimbNames()

	when (name)
	{
		climbingTypes[0] ->
		{
			dataStore.editWillClimbBoulder(status)
		}
		climbingTypes[1] ->
		{
			dataStore.editWillClimbSport(status)
		}
		climbingTypes[2] ->
		{
			dataStore.editWillClimbTopRope(status)
		}
		climbingTypes[3] ->
		{
			dataStore.editWillClimbTrad(status)
		}
	}
}

/**
 * Preview the page.
 */
@Composable
@Preview(showBackground = true)
fun TypeOfClimbingPagePreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		TypeOfClimbingPage()
	}
}
