package me.gabeg.sicksends.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
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
fun SsAskAboutProblemQuestionsPage()
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

	// Data store preferences
	val boulderDataStore = SsSharedBoulderDataStore(LocalContext.current)
	val sportDataStore = SsSharedSportDataStore(LocalContext.current)
	val topRopeDataStore = SsSharedTopRopeDataStore(LocalContext.current)
	val tradDataStore = SsSharedTradDataStore(LocalContext.current)
	val allDataStores = listOf(boulderDataStore, sportDataStore,
		topRopeDataStore, tradDataStore)

	// All climb names
	val climbNames = getAllClimbNames()

	// Page
	SsOnboardingPage(
		title = "When adding a climb, what do you want to be asked?",
		subtitle = "This can always be changed later.")
	{

		// Scrollable column
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth())
		{

			item {

				SsAskQuestion(
					title = "Grade")
				{
					println("Grade question clicked!")
				}

			}

			item {

				SsAskQuestion(
					title = "How Did the Climb Feel",
					description = "On a scale from Very Easy to Very Hard, how did the climb feel?")
				{
					println("How did it feel question clicked!")
				}

			}

			item {

				SsAskQuestion(
					title = "Perceived Grade",
					description = "What grade do you think the climb really was?")
				{
					println("Percevied grade question clicked!")
				}

			}

		}

	}
}

/**
 * Create a problem question.
 */
@Composable
fun SsAskQuestion(
	title : String,
	description : String = "",
	isChecked : Boolean = false,
	onClick : () -> Unit = {}
)
{

	// Create a row for each climbing type
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp)
			.clickable { onClick() },
		horizontalArrangement = Arrangement.Start,
		verticalAlignment = Alignment.CenterVertically)
	{


		Column(
			modifier = Modifier
				.weight(1f)
		)
		{

			// Title
			Text(
				text = title,
				fontWeight = FontWeight.SemiBold)

			// Description
			if (description.isNotEmpty())
			{
				Text(
					text = description,
					modifier = Modifier
						.padding(top = 4.dp),
					fontWeight = FontWeight.Light)
			}

		}

		Switch(
			modifier = Modifier
				.padding(start = 4.dp),
			checked = isChecked,
			onCheckedChange = { onClick() },
			colors = SwitchDefaults.colors(Color.Magenta))
	}
}

/**
 * Build the title of each grading system section.
 */
//@Composable
//fun buildGradingSystemTitle(title : String)
//{
//	Text(title,
//		modifier = Modifier
//			.padding(vertical = 8.dp),
//		fontSize = MaterialTheme.typography.body1.fontSize,
//		fontWeight = FontWeight.Bold)
//}

/**
 * Preview the page.
 */
@Composable
@Preview(showBackground = true)
fun SsAskAboutProblemQuestionsPagePreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		SsAskAboutProblemQuestionsPage()
	}
}
