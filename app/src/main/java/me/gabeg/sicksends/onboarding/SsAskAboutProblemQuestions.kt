package me.gabeg.sicksends.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.problem.ui.*
import me.gabeg.sicksends.shared.*
import me.gabeg.sicksends.ui.SsIconTextButton

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
	val generalDataStore = SsSharedGeneralClimbingDataStore(LocalContext.current)
	val boulderDataStore = SsSharedBoulderDataStore(LocalContext.current)
	val sportDataStore = SsSharedSportDataStore(LocalContext.current)
	val topRopeDataStore = SsSharedTopRopeDataStore(LocalContext.current)
	val tradDataStore = SsSharedTradDataStore(LocalContext.current)

	// All data stores
	val allDataStores = listOf(generalDataStore, boulderDataStore,
		sportDataStore, topRopeDataStore, tradDataStore)

	// Screen width
	val screenWidth : Dp = LocalConfiguration.current.screenWidthDp.dp

	// Each question and whether it should be selected or not
	val grade = true
	val howDidItFeel = generalDataStore.observeQuestionHowDidItFeel()
	val perceivedGrade = generalDataStore.observeQuestionPerceivedGrade()
	val isFlash = generalDataStore.observeQuestionIsFlash()
	val numAttempt = generalDataStore.observeQuestionNumAttempt()
	val isProject = generalDataStore.observeQuestionIsProject()
	val isOutdoor = generalDataStore.observeQuestionIsOutdoor()
	val name = generalDataStore.observeQuestionName()
	val note = generalDataStore.observeQuestionNote()
	val mediaPath = generalDataStore.observeQuestionMediaPath()
	val location = generalDataStore.observeQuestionLocation()
	val wallFeature = generalDataStore.observeQuestionWallFeature()
	val climbingTechnique = generalDataStore.observeQuestionClimbingTechnique()
	val hold = generalDataStore.observeQuestionHold()

	// Page
	SsOnboardingPage(
		title = "When adding a climb, what do you want to be asked?",
		subtitle = "This can always be changed later.\nLong press on an item to see a description.")
	{

		// Scrollable column
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth())
		{

			// Grade
			item {
				SsAskQuestions(
					icon = { SsGradeIcon() },
					title = "Grade")
				{

					// Grade
					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsGradeIcon() },
						text = "Grade\n(Required)",
						description = "The difficulty rating of the climb. This is required and will always be asked.",
						selected = grade,
						onClick = {
						},
						onLongClick = it)

					// How did it feel
					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsHowDidItFeelIcon() },
						text = "How Did\nIt Feel",
						description = "How did the climb feel on a scale from:\n\n\t\u2022 Very Easy\n\t\u2022 Easy\n\t\u2022 Normal\n\t\u2022 Hard\n\t\u2022 Very Hard",
						selected = howDidItFeel,
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionHowDidItFeel(it)
								}
							}
						},
						onLongClick = it)

					// Perceived grade
					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsPerceivedGradeIcon() },
						text = "Perceived\nGrade",
						description = "The grade you think the climb should really be.",
						selected = perceivedGrade,
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionPerceivedGrade(it)
								}
							}
						},
						onLongClick = it)

				}
			}

			// Flash
			//item {
			//	SsAskQuestions(
			//		icon = { SsFlashIcon() },
			//		title = "Flash")
			//	{

			//		// Flash
			//		SsQuestionButton(
			//			modifier = Modifier
			//				.width(screenWidth / 4),
			//			icon = { SsFlashIcon() },
			//			text = "Flash",
			//			description = "Whether or not the problem was flashed.",
			//			selected = isFlash,
			//			onClick = {
			//				scope.launch {
			//					for (ds in allDataStores)
			//					{
			//						ds.editQuestionIsFlash(it)
			//					}
			//				}
			//			},
			//			onLongClick = it)

			//		// Number of attempts
			//		SsQuestionButton(
			//			modifier = Modifier
			//				.width(screenWidth / 4),
			//			icon = { SsNumberOfAttemptsIcon() },
			//			text = "Number of\nAttempts",
			//			description = "Number of times the climb was attempted.",
			//			selected = numAttempt,
			//			onClick = {
			//				scope.launch {
			//					for (ds in allDataStores)
			//					{
			//						ds.editQuestionNumAttempt(it)
			//					}
			//				}
			//			},
			//			onLongClick = it)

			//	}
			//}

			// Number of attempts
			item {
				SsAskQuestion(
					icon = {
						SsNumberOfAttemptsIcon(
							modifier = Modifier
								.size(28.dp))
					},
					title = "Attempts",
					description = "Number of times the climb was attempted.",
					selected = numAttempt,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionNumAttempt(it)
							}
						}
					})
			}

			// Project
			item {
				SsAskQuestion(
					icon = { SsProjectIcon() },
					title = "Project",
					description = "Whether or not the problem is a project.",
					selected = isProject,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionIsProject(it)
							}
						}
					})
			}

			// Outdoors
			item {
				SsAskQuestion(
					icon = { SsOutdoorIcon() },
					title = "Outdoors",
					description = "Whether or not the problem is located outdoors.",
					selected = isOutdoor,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionIsOutdoor(it)
							}
						}
					})
			}

			// Name
			item {
				SsAskQuestion(
					icon = { SsNameIcon() },
					title = "Name",
					description = "The name of the problem.",
					selected = name,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionName(it)
							}
						}
					})
			}

			// Notes
			item {
				SsAskQuestion(
					icon = { SsNoteIcon() },
					title = "Notes",
					description = "Any written notes you want to save about the climb.",
					selected = note,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionNote(it)
							}
						}
					})
			}

			// Media path
			item {
				SsAskQuestion(
					icon = { SsMediaIcon() },
					title = "Images / Videos",
					description = "Any images or videos associated with the problem.",
					selected = mediaPath,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionMediaPath(it)
							}
						}
					})
			}

			// Location
			item {
				SsAskQuestion(
					icon = { SsLocationIcon() },
					title = "Location",
					description = "The location of the problem. You can decide if this uses GPS or not.",
					selected = location,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionLocation(it)
							}
						}
					})
			}

			// Wall features
			item {
				SsAskQuestion(
					icon = { SsWallIcon() },
					title = "Wall Features",
					description = "The different types of features that are on the climb, such as:\n\nRoof, Slab, Top Out, etc.",
					selected = wallFeature,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionWallFeature(it)
							}
						}
					})
			}

			// Climbing techniques
			item {
				SsAskQuestion(
					icon = { SsTechniqueIcon() },
					title = "Climbing Techniques",
					description = "The different types of techniques you needed to employ on the climb, such as:\n\nBicycle, Heel Hook, Knee Bar, etc.",
					selected = climbingTechnique,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionClimbingTechnique(it)
							}
						}
					})
			}

			// Holds
			item {
				SsAskQuestion(
					icon = {
						SsBoulderIcon(
							modifier = Modifier
								.size(24.dp))
					},
					title = "Holds",
					description = "The different types of holds on the climb, such as:\n\nCrimp, Pinch, Sloper, etc.",
					selected = hold,
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionHold(it)
							}
						}
					})
			}

		}

	}
}

/**
 * General question container that can be modified to have one or more
 * questions.
 */
@Composable
fun SsQuestionItem(
	modifier : Modifier = Modifier,
	icon : @Composable () -> Unit = {},
	title : String,
	description : String = "",
	showDescription : Boolean = false,
	titleContent : @Composable RowScope.() -> Unit = {},
	bodyContent : @Composable () -> Unit = {})
{

	// Container
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp, vertical = 12.dp))
	{

		// Title row
		Row(
			modifier = modifier
				.padding(vertical = 8.dp),
			verticalAlignment = Alignment.CenterVertically)
		{
			// Icon
			icon()

			// Space
			Spacer(modifier = Modifier.padding(horizontal = 8.dp))

			// Title
			Text(title,
				fontWeight = FontWeight.Bold,
				overflow = TextOverflow.Ellipsis)

			// Content
			titleContent()
		}

		// Body
		bodyContent()

		// Description
		// This is shown when long pressing. The text is not changed to an empty
		// string, as was done before to dictate the visibility, because the
		// disappear animation looks weird when the text is an empty string
		AnimatedVisibility(
			visible = showDescription,
			enter = expandVertically(
				expandFrom = Alignment.Top
			),
			exit = shrinkVertically(
				shrinkTowards = Alignment.Top
			))
		{
			Text(description,
				modifier = Modifier
					.padding(horizontal = 16.dp, vertical = 4.dp),
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Light)
		}

	}

}

/**
 * Ask a single type of question.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SsAskQuestion(
	icon : @Composable () -> Unit = {},
	title : String,
	description : String = "",
	selected : Boolean = false,
	onClick : (Boolean) -> Unit = {})
{

	// Whether or not to show the description
	var showDescription by remember { mutableStateOf(false) }

	// Question
	SsQuestionItem(
		modifier = Modifier
			.combinedClickable(
				onClick = {
					onClick(!selected)
				},
				onLongClick = {
					showDescription = !showDescription
				}
			),
		icon = icon,
		title = title,
		description = description,
		showDescription = showDescription,
		titleContent = {

			Spacer(modifier = Modifier.weight(1f))

			Switch(
				modifier = Modifier
					.padding(start = 4.dp),
				checked = selected,
				onCheckedChange = {
					onClick(!selected)
				},
				colors = SwitchDefaults.colors(Color.Magenta))

		})
}

/**
 * Ask multiple questions for a general type of question.
 */
@Composable
fun SsAskQuestions(
	icon : @Composable () -> Unit = {},
	title : String,
	content : @Composable (onLongClick : (String) -> Unit) -> Unit = {})
{

	// Whether or not the questions are expanded
	var isExpanded by remember { mutableStateOf(false) }

	// The description as well as whether to show it or not
	var description by remember { mutableStateOf("") }
	var showDescription by remember { mutableStateOf(false) }

	// Questions
	SsQuestionItem(
		modifier = Modifier
			.clickable {
				isExpanded = !isExpanded

				// Make sure the description is no longer showed when the
				// questions are collapsed
				if (!isExpanded)
				{
					description = ""
					showDescription = false
				}
			},
		icon = icon,
		title = title,
		description = description,
		showDescription = showDescription,
		titleContent = {

			Spacer(modifier = Modifier.weight(1f))

			Text(if (isExpanded) "Hide" else "Show",
				fontSize = MaterialTheme.typography.caption.fontSize)

			SsExpandIcon(
				modifier = Modifier
					.then(Modifier.padding(vertical = 12.dp)))

		},
		bodyContent = {

			// Show or hide the buttons
			AnimatedVisibility(visible = isExpanded)
			{
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.height(IntrinsicSize.Min)
						.padding(vertical = 12.dp),
					horizontalArrangement = Arrangement.SpaceEvenly,
					verticalAlignment = Alignment.CenterVertically)
				{

					// Buttons
					content(
						onLongClick = { text ->

							// Toggle the description
							showDescription = !showDescription

							// Set the text of the description. Because there
							// are multiple buttons, this has to be determined
							// after a long click
							if (showDescription)
							{
								description = text
							}
						})

				}
			}

		})

}

/**
 * A toggleable question button for when a general type of question involves
 * multiple other questions.
 */
@Composable
fun SsQuestionButton(
	modifier : Modifier = Modifier,
	icon : @Composable () -> Unit = {},
	text : String = "",
	description : String = "",
	selected : Boolean = false,
	onClick : (Boolean) -> Unit = {},
	onLongClick : (String) -> Unit = {})
{

	// Button
	SsIconTextButton(
		modifier = Modifier.fillMaxHeight(),
		innerModifier = modifier,
		text = text,
		onClick = {
			onClick(!selected)
		},
		onLongClick = {
			onLongClick(description)
		},
		colors = ButtonDefaults.outlinedButtonColors(
			backgroundColor = if (selected) Color.Magenta else Color.Transparent,
			contentColor = if (selected) Color.White else Color.DarkGray))
	{
		icon()
	}

}
