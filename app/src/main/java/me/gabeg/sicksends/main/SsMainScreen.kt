package me.gabeg.sicksends.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import me.gabeg.sicksends.R
import me.gabeg.sicksends.boulder.SsBoulderProblem
import me.gabeg.sicksends.boulder.SsBoulderRepository
import me.gabeg.sicksends.boulder.SsBoulderViewModel
import me.gabeg.sicksends.problem.SsGenericProblem
import me.gabeg.sicksends.problem.SsProblemDatabase
import me.gabeg.sicksends.shared.SsSharedDataStore
import me.gabeg.sicksends.shared.howDidItFeelScaleToString
import java.text.SimpleDateFormat
import java.util.*

const val MAIN_SCREEN_ROUTE = "main_screen_route"

// TODO: Look into the back button at the top bar and also subtitles in the top
//  bar
// TODO: Hide the floating action button when in the Home screen
@Composable
fun SsMainScreen(navController: NavController)
{
	val dataStore = SsSharedDataStore(LocalContext.current)
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	// TODO: rememberSaveable() pass this to function to decouple how the data
	//  is saved with how it is used.
	// TODO: Move this to Application and remove scope when done testing
	val db = SsProblemDatabase.getInstance(LocalContext.current, scope)
	//val boulderRepo = SsDummyRepository(db.dummyDao())
	//val boulderViewModel = SsDummyViewModel(boulderRepo)
	val boulderRepo = SsBoulderRepository(db.boulderDao())
	val boulderViewModel = SsBoulderViewModel(boulderRepo)
	val allProblems :  List<SsBoulderProblem> by boulderViewModel.allProblems.observeAsState(listOf())

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			TopAppBar(
				title = { Text(stringResource(id = R.string.app_name)) },
				actions = {
					buildDropdownMenu() { menuItem ->
						Log.i("SsMainScreen", "TODO: Launch the Settings component")
					}
				})
		},
		bottomBar = {
			buildBottomNavigationBar(dataStore = dataStore) { index, name ->
				Log.i("SsMainScreen", "TODO: Launch the climb type")
			}
		},
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = { buildFloatingActionButton() },
		content = { innerPadding -> buildAllProblems(allProblems, innerPadding) })
}

/**
 * Build the bottom navigation bar.
 */
@Composable
fun buildBottomNavigationBar(dataStore : SsSharedDataStore,
	onNavigationItemClicked : (index : Int, name : String) -> Unit)
{

	// Get whether the user will do a type of climb
	val allWillClimb = dataStore.getAllWillClimb()

	// Get the navigation names and icons
	var navItemNames = remember { dataStore.getAllNavigationNames() }
	var navItemIcons = remember { dataStore.getAllNavigationIcons() }
	var selectedIndex by remember { mutableStateOf(0) }

	// Create the bottom navigation bar
	// TODO: Maybe pass IntrisicSize?
	BottomNavigation {

		// Iterate over Home + each type of climb the user will do
		navItemNames.zip(navItemIcons).forEachIndexed { index, (name, icon) ->

			// Should navigation item be visible
			var isVisible = true

			// Observe changes as to whether the user will climb a certain
			// type of climb, and if so, change the visibility
			if (index > 0)
			{
				val willClimb by allWillClimb[index - 1].asLiveData().observeAsState()
				isVisible = (willClimb == true)
			}

			// Painter of the icon
			val painter = painterResource(id = icon)

			// Draw the bottom navigation item if the user will climb
			if (isVisible)
			{

				// Create a navigation item
				// TODO: Maybe set default size for icon?
				BottomNavigationItem(
					icon = {
						Icon(painter,
							modifier = Modifier
								.padding(top = 4.dp)
								.fillMaxHeight(0.5f)
								.aspectRatio(1f),
							contentDescription = name)
					},
					label = { Text(name) },
					selected = selectedIndex == index,
					onClick = {
						selectedIndex = index
						onNavigationItemClicked(index, name)
					},
					unselectedContentColor = Color.White.copy(alpha = 0.3f))
			}

		}

	}
}

/**
 * Build the dropdown menu.
 */
@Composable
fun buildDropdownMenu(onMenuItemClicked : (menuItem : String) -> Unit)
{
	val menuItems = listOf<String>("Settings")
	var isMenuVisible by remember { mutableStateOf(false) }

	// TODO: Place = Location icon
	// TODO: Visibility = Eye icon
	IconButton(onClick = { isMenuVisible = !isMenuVisible })
	{
		Icon(Icons.Default.MoreVert, contentDescription = "Localized description")

		DropdownMenu(
			expanded = isMenuVisible,
			onDismissRequest = { isMenuVisible = false })
		{
			DropdownMenuItem(
				onClick = {
					isMenuVisible = false
					onMenuItemClicked(menuItems[0])
				})
			{
				Text(menuItems[0])
			}

		}

	}
}

/**
 * Build the floating action button.
 */
@Composable
fun buildFloatingActionButton()
{
	FloatingActionButton(
		onClick = {
			Log.i("FloatingActionButton", "TODO: Add climb type, depending on which")
		})
	{
		Icon(Icons.Default.Add, contentDescription = "Localized description")
	}
}

/**
 * Convert a problem timestamp to a date.
 */
fun convertTimestampToDate(problem: SsGenericProblem) : String
{

	// No problem entered
	if (problem == null)
	{
		return ""
	}

	// Get today's date and the date of the problem
	var today = Date()
	var date = Date(problem.timestamp*1000)

	// Compare the two dates to see what the date text should say
	if (isSameDate(today, date))
	{
		return "Today"
	}
	else if (isDayBeforeDate(today, date))
	{
		return "Yesterday"
	}
	else if (isSameYear(today, date))
	{
		return SimpleDateFormat("EEE, MMM d").format(date)
	}
	else
	{
		return SimpleDateFormat("MMM d, yyyy").format(date)
	}
}

/**
 * Check if a date occurs before another date.
 */
fun isDayBeforeDate(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)
	cal2.add(Calendar.DAY_OF_MONTH, 1)

	// Get the year, month, and day
	// TODO: Can I just do this with year and day-of-year?
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)
	val month1 = cal1.get(Calendar.MONTH)
	val month2 = cal2.get(Calendar.MONTH)
	val day1 = cal1.get(Calendar.DAY_OF_MONTH)
	val day2 = cal2.get(Calendar.DAY_OF_MONTH)

	return (year1 == year2) && (month1 == month2) && (day1 == day2)
}

/**
 * Check if two dates occur on the same date.
 */
fun isSameDate(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)

	// Get the year, month, and day
	// TODO: Can I just do this with year and day-of-year?
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)
	val month1 = cal1.get(Calendar.MONTH)
	val month2 = cal2.get(Calendar.MONTH)
	val day1 = cal1.get(Calendar.DAY_OF_MONTH)
	val day2 = cal2.get(Calendar.DAY_OF_MONTH)

	return (year1 == year2) && (month1 == month2) && (day1 == day2)
}

/**
 * Check if two dates occur in the same year.
 */
fun isSameYear(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)

	// Get the year
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)

	return (year1 == year2)
}

/**
 * Find each group of problems that have occur on the same day.
 */
fun findEachGroupOfProblemsWithSameDay(allProblems: List<SsGenericProblem>)
	: List<List<SsGenericProblem>>
{

	// Store the group of problems that occur on the same day
	var sameDayList = mutableListOf<List<SsGenericProblem>>()

	// Find the indices of each new day
	var newDayIndices = findIndexForNewDays(allProblems)
	var prevIndex = 0

	// Iterate over each index of the new day list
	for (i in newDayIndices.indices)
	{

		// Get the current index
		var index = newDayIndices[i]

		// Something wrong with the indices. Continue to next element
		if ((prevIndex < 0) || (index < 0))
		{
			Log.i("POOPOPOPOPOP", "CONTINUE")
			continue
		}

		// Get the group of problems that occur on the same day
		var sameDayGroup = allProblems.subList(prevIndex, index+1)

		// Add the group to the list
		sameDayList.add(sameDayGroup)
		Log.i("POOPOPOPOPOP", "Getting sublist : $prevIndex to " + index)

		// Increment the previous index
		prevIndex = index+1
	}

	return sameDayList.toList()
}

/**
 * Find the indices that correspond to new days.
 */
fun findIndexForNewDays(allProblems: List<SsGenericProblem>) : List<Int>
{
	//if (allProblems !is List<T>)
	//{
	//	return listOf()
	//}

	// No problems found
	if (allProblems.size == 0)
	{
		return listOf()
	}

	// Store the indices
	var newDayIndices = mutableListOf<Int>()

	// Used to compare the previous date that was encountered with the current
	// date that is being looked at
	var prevDate = Date(0)

	// Iterate over each problem
	for ((i, p) in allProblems.withIndex())
	{

		// Get the date of the problem
		var curDate = Date(p.timestamp * 1000)
		//var curDate = Date(1000)
		//Log.i("YOOOOOO", "Timestamp : " + p.timestamp + " || Date : " + curDate)

		// This is the first problem. There is no previous date to compare with
		if (i == 0)
		{
			prevDate = curDate
		}
		// The previous date and the current date do not match. Save this index
		else if (!isSameDate(prevDate, curDate))
		{
			newDayIndices.add(i-1)
			prevDate = curDate
			//Log.i("AHHHHHHHHH", "Adding index : " + (i-1))
		}
	}

	// Add the last index in the list so that the last set of days are included
	newDayIndices.add(allProblems.size-1)

	return newDayIndices.toList()
}

/**
 * Build all problems.
 */
@Composable
fun buildAllProblems(allProblems : List<SsGenericProblem>,
	innerPadding : PaddingValues)
{

	// Organize all problems so that they are grouped by day
	val problemsGroupedByDay = findEachGroupOfProblemsWithSameDay(allProblems)

	// Number of problems to show per row
	val cols = 1

	// Create the scrollable column that will show all problems
	LazyColumn(contentPadding = innerPadding)
	{

		// Iterate over each group of problems, where each group corresponds to
		// a day
		for (problemsOnSameDay in problemsGroupedByDay)
		{

			// Display the date that all these problems occur on
			buildDateText(problemsOnSameDay[0])

			// Display all these problems that occur on the same day, and
			// organize them further so that the desired number of problems are
			// shown on each row
			buildProblemCardRow(problemsOnSameDay, cols)
		}

	}

}

/**
 * Build the date text.
 */
fun LazyListScope.buildDateText(problem: SsGenericProblem)
{
	item {
		var date = convertTimestampToDate(problem)

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 16.dp),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically)
		{
			Text(date,
				modifier = Modifier
					.alpha(1f),
				fontWeight = FontWeight.Bold,
				color = Color.Gray)
		}
	}
}

/**
 * Build a row of one or more problems.
 */
fun LazyListScope.buildProblemCardRow(problemList : List<SsGenericProblem>,
	numPerRow : Int)
{

	// Divvy out the list of problems into chunks, depending on how many
	// problems it is desired to be on a row
	itemsIndexed(problemList.chunked(numPerRow)) { itemIndex, problemChunk ->

		// Row for the problems
		Row()
		{

			// Iterate over each problem that will be in the row
			for ((chunkIndex, p) in problemChunk.withIndex())
			{

				// Compute the fraction of the full width that a problem
				// should take up
				var fractionOfWidth = 1f / (numPerRow - chunkIndex)

				// Padding around each problem
				var startPad = if (chunkIndex == 0) 12.dp else 0.dp
				var endPad = 12.dp
				var topPad = if (itemIndex == 0) 16.dp else 0.dp
				var bottomPad = 16.dp

				// Build a box and put each problem in the box
				Box(
					modifier = Modifier
						.fillMaxWidth(fractionOfWidth)
						.padding(start = startPad, end = endPad,
							top = topPad, bottom = bottomPad))
				{
					buildProblemCard(p)
				}
			}
		}
	}
}

/**
 * Build a problem.
 */
@Composable
fun buildProblemCard(problem : SsGenericProblem)
{

	// Problem card
	Card(
		modifier = Modifier
			.fillMaxWidth(),
		elevation = 10.dp)
	{

		// Container for all the content in the card
		Column(
			modifier = Modifier
				.padding(8.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center)
		{

			ConstraintLayout(
				modifier = Modifier
					.fillMaxWidth())
			{
				val (leftIcon, gradeText, rightIcon) = createRefs()

				// Project
				buildProjectIcon(problem, leftIcon)

				// Flash
				buildFlashIcon(problem, leftIcon)

				// Outdoor (Icon=Forest not found)
				buildOutdoorIcon(problem, rightIcon)

				// Grade
				buildGradeText(problem, leftIcon, gradeText, rightIcon)
			}

			// Perceived grade or How the climb felt, if specified
			buildGradeSubtitleText(problem)

			// Spacer
			buildGradeSubtitleToNameSpacer(problem)

			// Location of the climb
			buildLocationNameText(problem)

			// Name of the climb
			buildNameText(problem)

		}

		//Text("Num Attempt : ${problem.numAttempt}")
		//Text("Hold Type    : ${problem.holdType}")
		//Text("Route Feature: ${problem.routeFeatureType}")
		//Text("Technique    : ${problem.climbingTechniqueType}")
		//Text("Note         : ${problem.note}")
	}
}

/**
 * Build the text of the grade for a problem.
 */
@Composable
fun ConstraintLayoutScope.buildGradeText(problem: SsGenericProblem,
	leftRef: ConstrainedLayoutReference, gradeRef: ConstrainedLayoutReference,
	rightRef: ConstrainedLayoutReference)
{
	val hasLeftIcon = problem.isProject || problem.isFlash
	val hasRightIcon = problem.isOutdoor
	val leftMargin = if (hasLeftIcon) 0.dp else 20.dp
	val rightMargin = if (hasRightIcon) 0.dp else 20.dp

	Text("${problem.grade}",
		modifier = Modifier
			.constrainAs(gradeRef) {
				val startLink = if (hasLeftIcon) leftRef.end else parent.start
				val endLink = if (hasRightIcon) rightRef.start else parent.end

				top.linkTo(parent.top)
				start.linkTo(startLink, margin = leftMargin)
				end.linkTo(endLink, margin = rightMargin)
			},
		textAlign = TextAlign.Center,
		fontSize = MaterialTheme.typography.h5.fontSize,
		fontWeight = FontWeight.Bold)
}

@Composable
fun buildGradeSubtitleText(problem: SsGenericProblem)
{
	val feelScale = problem.howDidItFeelScale
	val altGrade = problem.perceivedGrade
	var text = ""

	// Perceived grade
	if (!altGrade.isNullOrEmpty())
	{
		text = altGrade
	}
	// How did it feel scale
	else if ((feelScale > 0) && (feelScale != 3))
	{
		text = howDidItFeelScaleToString(feelScale)
	}
	// Do not show subtitle text. This was causing a crash so it is commented
	else
	{
		//return
	}

	// Show the subtitle text
	if (!text.isNullOrEmpty())
	{
		Text(text,
			fontSize = MaterialTheme.typography.caption.fontSize)
	}
}

/**
 * Build the spacer between the subtitle and name.
 */
@Composable
fun buildGradeSubtitleToNameSpacer(problem: SsGenericProblem)
{
	var hasAltGrade = !problem.perceivedGrade.isNullOrEmpty()
	var hasFeelScale = (problem.howDidItFeelScale > 0) && (problem.howDidItFeelScale != 3)
	var hasSubtitle = hasAltGrade || hasFeelScale
	var hasName = !problem.locationName.isNullOrEmpty() || !problem.name.isNullOrEmpty()

	// Build the spacer if there is a subtitle present and a name present
	if (hasSubtitle && hasName)
	{
		Spacer(modifier = Modifier.padding(vertical = 4.dp))
	}
}

/**
 * Build the icon indicating if a problem was a flash or not.
 */
@Composable
fun ConstraintLayoutScope.buildFlashIcon(problem : SsGenericProblem,
	ref : ConstrainedLayoutReference)
{

	// Problem is not a flash. Do not do anything
	if (!problem.isFlash)
	{
		return
	}

	// Build the flash icon
	Icon(Icons.Default.Bolt,
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
			}
			.size(20.dp),
		contentDescription = "Flash")
}

/**
 * Build the text of the location name.
 */
@Composable
fun buildLocationNameText(problem: SsGenericProblem)
{
	val locationName = problem.locationName

	// No location name entered. Do not do anything
	if (locationName.isNullOrEmpty())
	{
		return
	}
	
	// Build the location name text
	Text("${locationName}",
		fontSize = MaterialTheme.typography.body2.fontSize,
		softWrap = false,
		overflow = TextOverflow.Ellipsis)
}
/**
 * Build the text of the problem's name.
 */
@Composable
fun buildNameText(problem: SsGenericProblem)
{
	val name = problem.name

	// No name entered. Do not do anything
	if (name.isNullOrEmpty())
	{
		return
	}

	// Show the name text
	Text("${name}",
		fontSize = MaterialTheme.typography.body2.fontSize,
		softWrap = false,
		overflow = TextOverflow.Ellipsis)
		//fontStyle = FontStyle.Italic,
}

/**
 * Build the icon indicating if a problem was climbed outdoors or not.
 */
@Composable
fun ConstraintLayoutScope.buildOutdoorIcon(problem : SsGenericProblem,
	ref : ConstrainedLayoutReference)
{
	// Outdoor (Icon=Forest not found)
	if (!problem.isOutdoor)
	{
		return
	}

	// Build the outdoor icon. (Icon=Forest not found)
	Icon(Icons.Default.Park,
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				end.linkTo(parent.end)
			}
			.size(16.dp),
		contentDescription = "Outdoor")
}

/**
 * Build the icon indicating if a problem is a project or not.
 */
@Composable
fun ConstraintLayoutScope.buildProjectIcon(problem : SsGenericProblem,
	ref : ConstrainedLayoutReference)
{

	// Problem is not a project. Do not do anything
	if (!problem.isProject)
	{
		return
	}

	// Build the project icon
	Icon(Icons.Default.Construction,
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
			}
			.size(18.dp),
		contentDescription = "Project")
}

//@Composable
//fun buildProblemCardV1(it : SsGenericProblem, width : Dp)
//{
//	// Problem card
//	Card(
//		modifier = Modifier
//			.fillMaxWidth()
//			.padding(horizontal = 16.dp, vertical = 8.dp),
//		elevation = 10.dp)
//	{
//
//		// Container for all the content in the card
//		Row(
//			modifier = Modifier
//				.fillMaxWidth()
//				.height(IntrinsicSize.Min)
//				.padding(12.dp))
//		{
//
//			// Show the grade and how the climb felt
//			Column(
//				modifier = Modifier
//					.fillMaxHeight(),
//				horizontalAlignment = Alignment.CenterHorizontally,
//				verticalArrangement = Arrangement.Center)
//			{
//
//				// Grade
//				Text("${it.grade}",
//					textAlign = TextAlign.Center,
//					fontSize = MaterialTheme.typography.h5.fontSize,
//					fontWeight = FontWeight.Bold)
//
//				// How the climb felt, if specified
//				if (it.howDidItFeelScale > 0 && it.howDidItFeelScale != 3)
//				{
//					Text(howDidItFeelScaleToString(it.howDidItFeelScale),
//						//modifier = Modifier
//						//	.width(IntrinsicSize.Min),
//						fontSize = MaterialTheme.typography.caption.fontSize)
//					//textAlign = TextAlign.Start)
//				}
//			}
//
//			//Text("Timestamp    : ${it.timestamp}")
//
//			Divider(
//				modifier = Modifier
//					.fillMaxHeight()
//					.padding(horizontal = 12.dp)
//					.width(1.dp),
//				color = Color.Black)
//
//			//Column()
//			//	{
//
//			//		Text("Num Attempt : ${it.numAttempt}")
//			//	}
//
//			//Spacer(modifier = Modifier.weight(1f))
//
//			Column()
//			{
//
//				// Align the icons in a row
//				Row(
//					modifier = Modifier
//						.fillMaxWidth(),
//					horizontalArrangement = Arrangement.End)
//				{
//
//					//// Project
//					//	if (it.isProject)
//					//	{
//					//		Icon(Icons.Default.Construction,
//					//			modifier = Modifier
//					//				.size(18.dp),
//					//			contentDescription = "Project")
//					//	}
//
//					//	// Flash
//					//	if (it.isFlash)
//					//	{
//					//		Icon(Icons.Default.Bolt,
//					//			modifier = Modifier
//					//				.size(18.dp),
//					//			contentDescription = "Flash")
//					//	}
//
//					//	// Outdoor (Icon=Forest not found)
//					//	if (it.isOutdoor)
//					//	{
//					//		Icon(Icons.Default.Park,
//					//			modifier = Modifier
//					//				.size(18.dp),
//					//			contentDescription = "Outdoor")
//					//	}
//
//				}
//
//				Row(
//					modifier = Modifier
//						.weight(1f))
//				{
//					Column(
//						modifier = Modifier
//							.width(IntrinsicSize.Max))
//					{
//
//						// Location of the climb
//						if (!it.locationName.isNullOrEmpty())
//						{
//							Text("${it.locationName}",
//								modifier = Modifier.fillMaxWidth(),
//								fontSize = MaterialTheme.typography.body2.fontSize,
//								textAlign = TextAlign.Start)
//						}
//
//						// Name of the climb
//						if (!it.name.isNullOrEmpty())
//						{
//							Text("${it.name}",
//								modifier = Modifier.fillMaxWidth(),
//								fontSize = MaterialTheme.typography.body2.fontSize,
//								//fontStyle = FontStyle.Italic,
//								textAlign = TextAlign.Start)
//						}
//
//						// How the climb felt, if specified
//						//if (it.howDidItFeelScale > 0 && it.howDidItFeelScale != 3)
//						//	{
//						//		Text(howDidItFeelScaleToString(it.howDidItFeelScale),
//						//			fontSize = MaterialTheme.typography.caption.fontSize,
//						//			textAlign = TextAlign.Start)
//						//	}
//
//					}
//				}
//
//				Row(
//					modifier = Modifier
//						.fillMaxWidth())
//				{
//					Row(
//						horizontalArrangement = Arrangement.Start)
//					{
//
//						// Project
//						if (it.isProject)
//						{
//							Icon(Icons.Default.Construction,
//								modifier = Modifier
//									.size(18.dp),
//								contentDescription = "Project")
//						}
//
//						// Flash
//						if (it.isFlash)
//						{
//							Icon(Icons.Default.Bolt,
//								modifier = Modifier
//									.size(18.dp),
//								contentDescription = "Flash")
//						}
//
//						// Outdoor (Icon=Forest not found)
//						if (it.isOutdoor)
//						{
//							Icon(Icons.Default.Park,
//								modifier = Modifier
//									.size(18.dp),
//								contentDescription = "Outdoor")
//						}
//
//					}
//
//					Spacer(modifier = Modifier.weight(1f))
//
//					Row(
//						horizontalArrangement = Arrangement.End)
//					{
//						Icon(Icons.Default.ExpandMore,
//							modifier = Modifier
//								.size(18.dp),
//							contentDescription = "Expand")
//					}
//				}
//
//			}
//
//		}
//
//		//Text("Hold Type    : ${it.holdType}")
//		//Text("Route Feature: ${it.routeFeatureType}")
//		//Text("Technique    : ${it.climbingTechniqueType}")
//		//Text("Note         : ${it.note}")
//	}
//}





//@Composable
//fun TestScreen() {
//	val rowItems = listOf("Item 1", "Item 2", "Item 3")
//	val gridItems = listOf("Grid 1", "Grid 2", "Grid 3", "Grid 4")
//
//	LazyColumn() {
//		items(rowItems) {
//			Text(it)
//		}
//
//		val cols = 2
//		items(gridItems.chunked(cols)) { items ->
//			Row {
//				for ((index, item) in items.withIndex()) {
//					Box(modifier = Modifier.fillMaxWidth(1f / (cols - index))) {
//						Text(
//							text = item,
//						)
//					}
//				}
//			}
//		}
//
//	}
//}

//@Composable
//fun TestScreen2() {
//    val rowItems = listOf("Item 1", "Item 2", "Item 3")
//    val gridItems = listOf("Grid 1", "Grid 2", "Grid 3", "Grid 4")
//
//    val cols = 2
//    LazyVerticalGrid(columns = GridCells.Fixed(cols)) {
//        rowItems.forEach {
//            item(span = { GridItemSpan(cols) }) { Text(it) }
//        }
//
//        items(gridItems) {
//            Text(it)
//        }
//    }
//}
