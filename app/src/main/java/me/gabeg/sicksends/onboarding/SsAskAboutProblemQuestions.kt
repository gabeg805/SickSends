package me.gabeg.sicksends.onboarding

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
	val boulderDataStore = SsSharedBoulderDataStore(LocalContext.current)
	val sportDataStore = SsSharedSportDataStore(LocalContext.current)
	val topRopeDataStore = SsSharedTopRopeDataStore(LocalContext.current)
	val tradDataStore = SsSharedTradDataStore(LocalContext.current)
	val allDataStores = listOf(boulderDataStore, sportDataStore,
		topRopeDataStore, tradDataStore)

	// All climb names
	val climbNames = getAllClimbNames()

	val screenWidth : Dp = LocalConfiguration.current.screenWidthDp.dp

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

			item {
				SsAskQuestions(
					icon = { SsGradeIcon() },
					title = "Grade")
				{

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsGradeIcon() },
						text = "Grade\n(Required)",
						description = "The difficulty rating of the climb. This is required and will always be asked.",
						initial = true,
						onClick = {
						},
						onLongClick = it)

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsHowDidItFeelIcon() },
						text = "How Did\nIt Feel",
						description = "How did the climb feel on a scale from:\n\n\t\u2022 Very Easy\n\t\u2022 Easy\n\t\u2022 Normal\n\t\u2022 Hard\n\t\u2022 Very Hard",
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionHowDidItFeel(it)
								}
							}
						},
						onLongClick = it)

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsPerceivedGradeIcon() },
						text = "Perceived\nGrade",
						description = "The grade you think the climb should really be.",
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

			item {
				SsAskQuestions(
					icon = { SsLocationIcon() },
					title = "Location")
				{

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsLocationNameIcon() },
						text = "Name",
						description = "The name of the location of the problem. This does not use your GPS. The name is entered manually.",
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionLocationName(it)
								}
							}
						},
						onLongClick = it)

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						icon = { SsLocationIcon() },
						text = "Map\n(GPS Required)",
						description = "The location of the problem on a map. This uses your GPS.",
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionLocation(it)
								}
							}
						},
						onLongClick = it)

				}
			}

			item {
				SsAskQuestions(
					icon = { SsFlashIcon() },
					title = "Flash")
				{

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						text = "Flash",
						description = "Whether or not the problem was flashed.",
						icon = { SsFlashIcon() },
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionIsFlash(it)
								}
							}
						},
						onLongClick = it)

					SsQuestionButton(
						modifier = Modifier
							.width(screenWidth / 4),
						text = "Number of\nAttempts",
						description = "Number of times the climb was attempted.",
						icon = { SsNumberOfAttemptsIcon() },
						onClick = {
							scope.launch {
								for (ds in allDataStores)
								{
									ds.editQuestionNumAttempt(it)
								}
							}
						},
						onLongClick = it)

				}
			}

			item {
				SsAskQuestion(
					icon = { SsProjectIcon() },
					title = "Project",
					description = "Whether or not the problem is a project.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionIsProject(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsOutdoorIcon() },
					title = "Outdoors",
					description = "Whether or not the problem is located outdoors.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionIsOutdoor(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsNameIcon() },
					title = "Name",
					description = "The name of the problem.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionName(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsMediaIcon() },
					title = "Images / Videos",
					description = "Any images or videos associated with the problem.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionMediaPath(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsNoteIcon() },
					title = "Notes",
					description = "Any written notes you want to save about the climb.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionNote(it)
							}
						}
					})
			}

			// Route features
			item {
				SsAskQuestion(
					icon = { SsWallIcon() },
					title = "Wall Features",
					description = "The different types of features that are on the climb, such as:\n\nRoof, Slab, Top Out, etc.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionWallFeatureType(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsTechniqueIcon() },
					title = "Climbing Techniques",
					description = "The different types of techniques you needed to employ on the climb, such as:\n\nBicycle, Heel Hook, Knee Bar, etc.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionClimbingTechniqueType(it)
							}
						}
					})
			}

			item {
				SsAskQuestion(
					icon = { SsTechniqueIcon() },
					title = "Holds",
					description = "The different types of holds on the climb, such as:\n\nCrimp, Pinch, Sloper, etc.",
					onClick = {
						scope.launch {
							for (ds in allDataStores)
							{
								ds.editQuestionHoldType(it)
							}
						}
					})
			}

		}

	}
}

@Composable
fun SsQuestionItem(
	modifier : Modifier = Modifier,
	icon : @Composable () -> Unit = {},
	title : String,
	description : String = "",
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

		// Description, shown when long pressing
		AnimatedVisibility(visible = description.isNotEmpty())
		{
			Text(description,
				modifier = Modifier
					.padding(horizontal = 16.dp, vertical = 4.dp),
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Light)
		}

	}

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SsAskQuestion(
	icon : @Composable () -> Unit = {},
	title : String,
	description : String = "",
	initial : Boolean = false,
	onClick : (Boolean) -> Unit = {})
{

	var isChecked by remember { mutableStateOf(initial) }
	var descriptionText by remember { mutableStateOf("") }

	SsQuestionItem(
		modifier = Modifier
			.combinedClickable(
				onClick = {
					isChecked = !isChecked

					onClick(isChecked)
				},
				onLongClick = {
					descriptionText = if (descriptionText.isEmpty()) description else ""
				}
			),
		icon = icon,
		title = title,
		description = descriptionText,
		titleContent = {

			Spacer(modifier = Modifier.weight(1f))

			Switch(
				modifier = Modifier
					.padding(start = 4.dp),
				checked = isChecked,
				onCheckedChange = {
					isChecked = !isChecked

					onClick(isChecked)
				},
				colors = SwitchDefaults.colors(Color.Magenta))

		})
}

@Composable
fun SsAskQuestions(
	icon : @Composable () -> Unit = {},
	title : String,
	content : @Composable (onLongClick : (String) -> Unit) -> Unit = {})
{

	var descriptionText by remember { mutableStateOf("") }
	var isExpanded by remember { mutableStateOf(false) }

	SsQuestionItem(
		modifier = Modifier
			.clickable {
				isExpanded = !isExpanded

				if (!isExpanded)
				{
					descriptionText = ""
				}
			},
		icon = icon,
		title = title,
		description = descriptionText,
		titleContent = {

			Spacer(modifier = Modifier.weight(1f))

			Text(if (isExpanded) "Hide" else "Show",
				fontSize = MaterialTheme.typography.caption.fontSize)

			SsExpandIcon(
				modifier = Modifier
					.then(Modifier.padding(vertical = 12.dp)))

		},
		bodyContent = {

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

					// One or more buttons
					content()
					{
						descriptionText = if (it != descriptionText) it else ""
					}

				}
			}

		})

}

@Composable
fun SsQuestionButton(
	modifier : Modifier = Modifier,
	icon : @Composable () -> Unit = {},
	text : String = "",
	description : String = "",
	initial : Boolean = false,
	onClick : (Boolean) -> Unit = {},
	onLongClick : (String) -> Unit = {})
{

	var isClicked by remember { mutableStateOf(initial) }

	//SsLongClickOutlinedButton(
	SsIconTextButton(
		modifier = Modifier.fillMaxHeight(),
		innerModifier = modifier,
		text = text,
		onClick = {
			isClicked = !isClicked

			onClick(isClicked)
		},
		onLongClick = {
			onLongClick(description)
		},
		colors = ButtonDefaults.outlinedButtonColors(
			backgroundColor = if (isClicked) Color.Magenta else Color.Transparent,
			contentColor = if (isClicked) Color.White else Color.DarkGray))
	{
		icon()
	}

}


//// Container
//Column(
//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 8.dp, vertical = 8.dp))
//{
//
//	var description by remember { mutableStateOf("") }
//
//	// Title row
//	Row(
//		modifier = Modifier
//			.padding(vertical = 16.dp),
//		verticalAlignment = Alignment.CenterVertically)
//	{
//		// Icon
//		icon()
//
//		// Space
//		Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//		// Title
//		Text(title,
//			fontWeight = FontWeight.Bold)
//	}
//
//	// Button row
//	Row(
//		modifier = Modifier
//			.fillMaxWidth()
//			.height(IntrinsicSize.Min)
//			.padding(vertical = 8.dp),
//		horizontalArrangement = Arrangement.SpaceEvenly,
//		verticalAlignment = Alignment.CenterVertically)
//	{
//
//		// One or more buttons
//		content()
//		{
//			description = if (it != description) it else ""
//		}
//
//	}
//
//	// Description, shown when long pressing
//	AnimatedVisibility(visible = description.isNotEmpty())
//	{
//		Text(description,
//			modifier = Modifier
//				.padding(horizontal = 16.dp, vertical = 8.dp),
//			fontSize = MaterialTheme.typography.body1.fontSize,
//			fontWeight = FontWeight.Light)
//	}
//
//}



//Column(
//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 8.dp, vertical = 12.dp))
//{
//
//	var description by remember { mutableStateOf("") }
//
//	Row()
//	{
//		SsGradeIcon()
//
//		Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//		Text("Grade",
//			fontWeight = FontWeight.Bold)
//	}
//
//	Row(
//		modifier = Modifier
//			.fillMaxWidth()
//			.height(IntrinsicSize.Min),
//		horizontalArrangement = Arrangement.SpaceEvenly,
//		verticalAlignment = Alignment.CenterVertically)
//	{
//
//		var isGradeClicked by remember { mutableStateOf(false) }
//		var isPerceivedGradeClicked by remember { mutableStateOf(false) }
//		var isHowDidItFeelClicked by remember { mutableStateOf(false) }
//
//		SsLongClickOutlinedButton(
//			modifier = Modifier.fillMaxHeight(),
//			onClick = { isGradeClicked = !isGradeClicked },
//			onLongClick = { description = "The difficulty rating of the climb. This is required and will always be asked." },
//			colors = ButtonDefaults.outlinedButtonColors(
//				backgroundColor = if (isGradeClicked) Color.Magenta else Color.Transparent,
//				contentColor = if (isGradeClicked) Color.White else Color.DarkGray))
//		//border = ButtonDefaults.outlinedBorder)
//		{
//			Text("Grade\n(Required)",
//				textAlign = TextAlign.Center)
//			//println("Grade question clicked!")
//		}
//
//		SsLongClickOutlinedButton(
//			modifier = Modifier.fillMaxHeight(),
//			onClick = { isPerceivedGradeClicked = !isPerceivedGradeClicked },
//			onLongClick = { description = "The grade you think the climb should really be." },
//			colors = ButtonDefaults.outlinedButtonColors(
//				backgroundColor = Color.Transparent,
//				contentColor = if (isPerceivedGradeClicked) Color.Magenta else Color.DarkGray))
//		{
//			Text("Perceived\nGrade",
//				textAlign = TextAlign.Center)
//			//println("Perceived grade question clicked!")
//		}
//
//		SsLongClickOutlinedButton(
//			modifier = Modifier.fillMaxHeight(),
//			onClick = { isHowDidItFeelClicked = !isHowDidItFeelClicked },
//			onLongClick = { description = "How did the climb feel on a scale from:\n\n\t\u2022 Very Easy\n\t\u2022 Easy\n\t\u2022 Normal\n\t\u2022 Hard\n\t\u2022 Very Hard" },
//			colors = ButtonDefaults.outlinedButtonColors(
//				backgroundColor = Color.Transparent,
//				contentColor = if (isHowDidItFeelClicked) Color.Magenta else Color.DarkGray))
//		{
//			Text("How Did\nIt Feel\nBro",
//				textAlign = TextAlign.Center)
//			//println("How did it feel question clicked!")
//		}
//
//	}
//
//	Text(description)
//}

//item {
//	SsAskQuestion(
//		title = "Perceived Grade",
//		description = "The grade you think the climb should really be.")
//	{
//		println("Perceived grade question clicked!")
//	}
//}

//item {
//	SsAskQuestion(
//		title = "How Did the Climb Feel",
//		description = "How did the climb feel on a scale from:\n\n\t\u2022 Very Easy\n\t\u2022 Easy\n\t\u2022 Normal\n\t\u2022 Hard\n\t\u2022 Very Hard")
//	{
//		println("How did it feel question clicked!")
//	}
//}

//item {
//	SsAskQuestion(
//		title = "Number of Attempts",
//		description = "Number of times the climb was attempted.")
//	{
//		println("Number of attempts question clicked!")
//	}
//}

//item {
//	SsAskQuestion(
//		title = "Flash",
//		description = "Did you flash climb or not.")
//	{
//		println("Flash question clicked!")
//	}
//}

@Composable
fun SsAskExclusiveQuestions(
	icon : @Composable () -> Unit = {},
	title : String,
	description: String = "",
	content : @Composable () -> Unit = {}
)
{

	// Whether to show the description or not. Triggered by long clicking
	var showQuestions by remember { mutableStateOf(false) }

	Column()
	{

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 8.dp, vertical = 12.dp)
				.clickable(onClick = { showQuestions = !showQuestions }),
			horizontalArrangement = Arrangement.Start,
			verticalAlignment = Alignment.CenterVertically)
		{

			// Title
			Text(
				text = title,
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Normal)

			Spacer(modifier = Modifier.weight(1f))

			// Switch
			Icon(Icons.Default.ArrowDropDown,
				contentDescription = null)

		}

		AnimatedVisibility(visible = showQuestions)
		{
			Column(
				modifier = Modifier
					.padding(start = 16.dp))
			{
				content()
			}
		}
	}
}

/**
 * Create a problem question.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SsAskQuestion(
	title : String,
	description : String = "",
	isChecked : Boolean = false,
	onClick : () -> Unit = {})
{

	// Whether to show the description or not. Triggered by long clicking
	var showDescription by remember { mutableStateOf(false) }

	// Create a row for each climbing type
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp, vertical = 12.dp)
			.combinedClickable(
				onClick = { onClick() },
				onLongClick = { showDescription = !showDescription }
			),
		horizontalArrangement = Arrangement.Start,
		verticalAlignment = Alignment.CenterVertically)
	{


		// Switch
		Column(
			modifier = Modifier
				.weight(1f))
		{

			// Title
			Text(
				text = title,
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Normal)

			// Description
			if (description.isNotEmpty())
			{
				AnimatedVisibility(visible = showDescription)
				{
					Text(
						text = description,
						modifier = Modifier
							.padding(top = 4.dp),
						fontSize = MaterialTheme.typography.body2.fontSize,
						fontWeight = FontWeight.Light)
				}
			}

		}

		// Switch
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
//@Composable
//@Preview(showBackground = true)
//fun SsAskAboutProblemQuestionsPagePreview() {
//	Column(modifier = Modifier.fillMaxSize()) {
//		SsAskAboutProblemQuestionsPage()
//	}
//}
