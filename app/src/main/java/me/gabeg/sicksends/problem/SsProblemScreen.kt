package me.gabeg.sicksends.problem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import me.gabeg.sicksends.datetime.convertProblemToRelativeDate
import me.gabeg.sicksends.datetime.isSameDate
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.ui.SsFlashIcon
import me.gabeg.sicksends.problem.ui.SsOutdoorIcon
import me.gabeg.sicksends.problem.ui.SsProjectIcon
import java.util.*


/**
 * Show all problems on a screen.
 */
@Composable
fun SsProblemScreen(allProblems : List<SsGenericProblem>,
	innerPadding : PaddingValues,
	lazyListState : LazyListState = rememberLazyListState())
{

	// Organize all problems so that they are grouped by day
	val problemsGroupedByDay = findEachGroupOfProblemsWithSameDay(allProblems)

	// Number of problems to show per row
	val cols = 1

	// Create the scrollable column that will show all problems
	LazyColumn(
		contentPadding = innerPadding,
		state = lazyListState)
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
@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.buildDateText(problem: SsGenericProblem)
{

	// Add some spacing above the date header
	item {
		Spacer(modifier = Modifier.padding(vertical = 4.dp))
	}

	// Build the date as a sticky header
	// Note: If the background color changes, the above and below spacing will
	// look weird
	stickyHeader {

		var date = convertProblemToRelativeDate(problem)

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(Color.White)
				.padding(vertical = 4.dp),
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

	// Add some spacing below the date header
	item {
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
	if (problem.isFlash != true)
	{
		return
	}

	// Build the flash icon
	SsFlashIcon(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
			}
			.size(20.dp))
}

/**
 * Build the text of the grade for a problem.
 */
@Composable
fun ConstraintLayoutScope.buildGradeText(problem: SsGenericProblem,
	leftRef: ConstrainedLayoutReference, gradeRef: ConstrainedLayoutReference,
	rightRef: ConstrainedLayoutReference)
{
	val hasLeftIcon = (problem.isProject == true) || (problem.isFlash == true)
	val hasRightIcon = (problem.isOutdoor == true)
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

/**
 * Build the text that will be displayed under the grade, as a subtitle.
 */
@Composable
fun buildGradeSubtitleText(problem: SsGenericProblem)
{
	val altGrade = problem.perceivedGrade
	var text = ""

	// Perceived grade
	if (!altGrade.isNullOrEmpty())
	{
		text = altGrade
	}
	// How did it feel scale
	else if (problem.hasHowDidItFeelScale)
	{
		text = problem.howDidItFeel
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
	val altGrade = problem.perceivedGrade
	val locationName = problem.locationName
	val name = problem.name

	var hasSubtitle = !altGrade.isNullOrEmpty() || problem.hasHowDidItFeelScale
	var hasName = !locationName.isNullOrEmpty() || !name.isNullOrEmpty()

	// Build the spacer if there is a subtitle present and a name present
	if (hasSubtitle && hasName)
	{
		Spacer(modifier = Modifier.padding(vertical = 4.dp))
	}
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
	if (problem.isOutdoor != true)
	{
		return
	}

	// Build the outdoor icon. (Icon=Forest not found)
	SsOutdoorIcon(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				end.linkTo(parent.end)
			}
			.size(18.dp))
}

/**
 * Build a problem card.
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
 * Build the icon indicating if a problem is a project or not.
 */
@Composable
fun ConstraintLayoutScope.buildProjectIcon(problem : SsGenericProblem,
	ref : ConstrainedLayoutReference)
{

	// Problem is not a project. Do not do anything
	if (problem.isProject != true)
	{
		return
	}

	// Build the project icon
	SsProjectIcon(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
			}
			.size(18.dp))
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
			continue
		}

		// Get the group of problems that occur on the same day
		var sameDayGroup = allProblems.subList(prevIndex, index+1)

		// Add the group to the list
		sameDayList.add(sameDayGroup)

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
		}
	}

	// Add the last index in the list so that the last set of days are included
	newDayIndices.add(allProblems.size-1)

	return newDayIndices.toList()
}
