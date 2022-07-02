package me.gabeg.sicksends.addproblem

import android.view.KeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import me.gabeg.sicksends.addproblem.boulder.SsAddBoulderProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.question.*
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.ui.*

const val ADD_PROBLEM_SCREEN_ROUTE = "Add problem"

/**
 * Add climb screen.
 *
 * TODO: perceived grade, location name, location lat/lon,
 * route features, hold type, climbing technique, image
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsAddClimbScreen(
	innerPadding : PaddingValues,
	viewModel: SsAddBoulderProblemViewModel = hiltViewModel())
{

	val scrollState = rememberLazyListState()
	val pagerState = rememberPagerState()
	val dataStore = viewModel.dataStore

	val askName = dataStore.observeQuestionName()
	val askFlash = dataStore.observeQuestionIsFlash()
	val askNumAttempt = dataStore.observeQuestionNumAttempt()
	val askProject = dataStore.observeQuestionIsProject()
	val askOutdoor = dataStore.observeQuestionIsOutdoor()
	val askLocation = dataStore.observeQuestionLocation() || dataStore.observeQuestionLocationName()
	val askNote = dataStore.observeQuestionNote()

	println("Ask name ? $askName")
	println("Ask flash ? $askFlash")
	println("Ask attempt ? $askNumAttempt")
	println("Ask project ? $askProject")
	println("Ask outdoor ? $askOutdoor")
	println("Ask location ? $askLocation")
	println("Ask note ? $askNote")

	val isFlash by viewModel.problem.observableIsFlash.observeAsState()
	val isProject by viewModel.problem.observableIsProject.observeAsState()

	LaunchedEffect(true)
	{
		println("Showing 0 index!")
		viewModel.show(0)
	}

	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.padding(vertical = 24.dp, horizontal = 16.dp),
			//.padding(innerPadding),
		state = scrollState)
	{

		/**
		 * Grade
		 */
		item {
			SsGradeQuestion(
				viewModel = viewModel,
				scrollState = scrollState)
		}

		/**
		 * Name
		 */
		if (askName)
		{
			item {
				SsNameQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		/**
		 *  Is Flash / Number of attempts
		 */
		if (askFlash || askNumAttempt)
		{
			item {

				// Is flash
				if (askFlash)
				{
					AnimatedVisibility(visible =  (isProject != true))
					{
						SsFlashQuestion(
							viewModel = viewModel,
							scrollState = scrollState)
					}
				}
				// Number of attempts
				else if (askNumAttempt)
				{
					SsNumAttemptQuestion(
						viewModel = viewModel,
						scrollState = scrollState)
				}

			}
		}

		/**
		 * Is project?
		 *
		 * TODO: Which question should come first, is project or is flash?
		 */
		if (askProject)
		{
			item {
				AnimatedVisibility(visible = (isFlash != true))
				{
					SsProjectQuestion(
						viewModel = viewModel,
						scrollState = scrollState)
				}
			}
		}

		/**
		 * Is outdoor?
		 */
		if (askOutdoor)
		{
			item {
				SsOutdoorQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		/**
		 * Location
		 */
		if (askLocation)
		{
			item {
				SsLocationQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		/**
		 * Notes
		 */
		if (askNote)
		{
			item {
				SsNoteQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		// TODO: Media/images/videos
		// TODO: Climbing technique
		// TODO: Wall features
		// TODO: Holds

	}

}

/**
 * Question widget.
 */
@Composable
fun SsQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	icon : @Composable BoxScope.(
		modifier : Modifier) -> Unit,
	body : @Composable (
		visible : Boolean,
		onDone : (String) -> Unit) -> Unit = {_,_ -> },
	index : Int,
	scrollState : LazyListState = rememberLazyListState(),
	onClick : () -> Unit = {
		viewModel.showOnly(index)
	},
	onDone : () -> Unit = {

		println("Doing onDone!")
		viewModel.showOnly(index+1)
		println("TEST Body : ${viewModel.getVisible(index)} || $index")

		//scrollState.animateScrollToItem(index+1)
	})
{

	var scope = rememberCoroutineScope()
	var isDone by remember { mutableStateOf(false) }
	val currentOnDone by rememberUpdatedState(onDone)

	// Whether the current element should be visible
	var isVisible by remember { viewModel.getVisible(index) }
	var isHighlighted = isVisible || viewModel.isAnswered(index)

	// Whether to show the line or not
	val showLine = ((index+1) != viewModel.size)

	// Container
	Row()
	{
		SsQuestionSubcompose(
			dependentContent = {

				val localDensity = LocalDensity.current
				val navHeight = with(localDensity) { it.height.toDp() }

				Column(
					modifier = Modifier
						.height(navHeight),
					horizontalAlignment = Alignment.CenterHorizontally)
				{

					// Icon
					SsIcon(
						modifier = Modifier
							.padding(horizontal = 16.dp)
							.size(48.dp),
						focus = isHighlighted,
						onClick = onClick)
					{
						icon(modifier = Modifier
							.align(Alignment.Center)
							.size(24.dp))
					}

					// Vertical line
					if (showLine)
					{
						SsVerticalLine(
							modifier = Modifier.fillMaxHeight(),
							focus = isHighlighted,
							onClick = onClick)
					}

				}
			},
			mainContent = {

				// Body
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { onClick() }
						.padding(top = 0.dp, bottom = 32.dp, start = 8.dp, end = 8.dp))
				//.onGloballyPositioned { coord ->
				//		bodyHeight = with(localDensity) { coord.size.height.toDp() + 32.dp }
				//		println("Height : $index   $bodyHeight")
				//	})
				{

					body(isVisible)
					{ subtitle ->
						if (viewModel.answers.size >= index + 1)
						{
							println("Answer in proper index! $index")
							viewModel.answers[index] = subtitle
						}
						else
						{
							println("Answer added index! $index")
							viewModel.answers.add(index, subtitle)
						}

						println("Cancelling scope! $index")
						isDone = true
					}

				}

			})

	}

	// Done with question
	if (isDone)
	{
		LaunchedEffect(true)
		{
			println("Delay! $index")
			delay(500)
			println("CAAAAAALL onDone! $index")
			currentOnDone()
			isDone = false
		}
	}

}

/**
 */
@Composable
fun SsQuestionSubcompose(
	modifier: Modifier = Modifier,
	mainContent: @Composable () -> Unit,
	dependentContent: @Composable (IntSize) -> Unit)
{

	// Subcompose layout
	SubcomposeLayout(modifier = modifier) { constraints ->

		// Main
		val mainMeasureables = subcompose(SlotsEnum.Main, mainContent)
		var mainPlaceables = mainMeasureables.map { it.measure(constraints) }

		// Get max width and height of the main component
		val maxSize = mainPlaceables.fold(IntSize.Zero) { currentMax, placeable ->
			IntSize(
				width = maxOf(currentMax.width, placeable.width),
				height = maxOf(currentMax.height, placeable.height)
			)
		}

		// Dependent
		val depMeasureables = subcompose(SlotsEnum.Dependent) {
			dependentContent(maxSize)
		}
		val depPlaceables = depMeasureables.map {
			it.measure(constraints)
		}
		var depWidth = depPlaceables.maxOf { it.width }

		// Recompute width of main
		mainPlaceables = subcompose(SlotsEnum.New, mainContent).map {
			it.measure(Constraints(depWidth, constraints.maxWidth - depWidth))
		}

		// Layout
		layout(maxSize.width, maxSize.height) {
			mainPlaceables.forEach { it.placeRelative(depWidth, 0) }
			depPlaceables.forEach { it.placeRelative(0, 0) }
		}
	}
}

enum class SlotsEnum { Main, Dependent, New }

/**
 * Create the body.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsBody(
	title : String,
	subtitle : String,
	modifier : Modifier = Modifier,
	pagerState : PagerState? = null,
	content : @Composable () -> Unit = {})
{

	// Parent
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.Start)
	{

		Row(
			verticalAlignment = Alignment.CenterVertically
		)
		{

			// Title
			Text(title,
				fontSize = MaterialTheme.typography.body1.fontSize,
				fontWeight = FontWeight.Normal)

			// Page navigator
			if ((pagerState != null) && (pagerState.pageCount > 1))
			{
				Spacer(modifier = Modifier.padding(horizontal = 8.dp))

				Text("${pagerState.currentPage + 1} of ${pagerState.pageCount}",
					fontSize = MaterialTheme.typography.subtitle2.fontSize,
					fontWeight = FontWeight.Light)
			}

		}

		// Subtitle
		Text(subtitle,
			modifier = Modifier
				.padding(bottom = 24.dp),
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			fontSize = MaterialTheme.typography.h6.fontSize,
			fontWeight = FontWeight.SemiBold)

		// Body
		content()

	}

}

/**
 * Create an icon bubble that appears next to each question.
 */
@Composable
fun SsIcon(
	modifier : Modifier = Modifier,
	focus : Boolean = true,
	onClick: () -> Unit = {},
	content : @Composable BoxScope.() -> Unit = {})
{

	var borderColor = if (focus) Color.Green else Color.Gray

	Box(
		modifier = modifier
			.clickable { onClick() }
			.background(color = Color.White, shape = CircleShape)
			.border(width = 3.dp, color = borderColor, shape = CircleShape))
	{
		content()
	}
}

/**
 * A body with a text field.
 *
 * TODO: Possible parameters:
 * singleLine
 * imeAction, might want new line if single line is not true, but then how to
 * go to next thing?
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsTextFieldBody(
	title : String,
	question : String = "",
	initial : String = "",
	singleLine : Boolean = false,
	visible : Boolean = true,
	onDone : (String) -> Unit = {})
{

	// Subtitle
	var subtitle by remember { mutableStateOf(question) }

	// Text
	var text by remember { mutableStateOf(initial) }

	// Request focus to show the keyboard
	val focusRequester = remember { FocusRequester() }
	var imeAction = if (singleLine) ImeAction.Next else ImeAction.Default

	// Body
	SsBody(title, if (visible || (subtitle != question)) subtitle else "")
	{

		// Animate visibility as needed
		AnimatedVisibility(visible = visible)
		{

			// TODO: Capture "Enter" key press and go to next line
			OutlinedTextField(
				modifier = Modifier
					.focusRequester(focusRequester)
					.onKeyEvent {

						// Check if Enter or Tab or pressed to indicate that the
						// user is done with this question
						val key = it.nativeKeyEvent.keyCode
						val isDone = (key == KeyEvent.KEYCODE_ENTER) ||
							(key == KeyEvent.KEYCODE_TAB)

						// The user is done with this question
						if (singleLine && isDone)
						{
							subtitle = text

							onDone(subtitle)
							return@onKeyEvent true
						}
						else
						{
							return@onKeyEvent false
						}
					},
				value = text,
				onValueChange = {
					text = if (singleLine) it.replace("\n", "") else it
				},
				singleLine = singleLine,
				keyboardOptions = KeyboardOptions(imeAction = imeAction),
				keyboardActions = KeyboardActions(
					onNext = {
						subtitle = text

						onDone(subtitle)
					}))

			// Focus the text field, if it is visible
			if (visible)
			{
				focusRequester.requestFocus()
			}

		}

	}

}

/**
 * Create a vertical line that connects two icon bubbles.
 */
@Composable
fun SsVerticalLine(
	modifier : Modifier = Modifier,
	focus : Boolean = false,
	onClick: () -> Unit = {})
{

	var color = if (focus) Color.Green else Color.Gray

	Divider(
		modifier = modifier
			.clickable { onClick() }
			.width(3.dp),
		color = color)
}

/**
 * A body asking a yes/no question.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsYesNoBody(
	title : String,
	question: String,
	initial: Boolean? = null,
	visible : Boolean = true,
	onDone : (Boolean?, String) -> Unit = {_,_ -> })
{

	var subtitle by remember { mutableStateOf(question) }
	var isYes by remember { mutableStateOf<Boolean?>(initial) }

	// Body
	//SsBody(title, subtitle)
	SsBody(title, if (visible || (subtitle != question)) subtitle else "")
	{

		// Animate the visibility
		AnimatedVisibility(
			visible = visible,
			enter = expandVertically(
				expandFrom = Alignment.Top
			),
			exit = shrinkVertically(
				shrinkTowards = Alignment.Top
			))
		{

			var buttonSpacing = 16.dp
			var borderWidth = 2.dp
			var yesBorderColor = if (isYes == true) Color.Magenta else Color.LightGray
			var yesContentColor = if (isYes == true) Color.Magenta else Color.Black
			var noBorderColor = if (isYes == false) Color.Magenta else Color.LightGray
			var noContentColor = if (isYes == false) Color.Magenta else Color.Black

			// TODO: If the buttons took up 50% of the width, that might look
			// better?
			//modifier = Modifier.fillMaxWidth())
			//horizontalArrangement = Arrangement.SpaceAround)
			Row()
			{

				// Yes button
				SsYesIconTextButton(
					border = BorderStroke(
						width = borderWidth,
						color = yesBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = yesContentColor,
						backgroundColor = Color.Transparent))
				{
					isYes = true
					subtitle = it

					onDone(isYes, subtitle)
				}

				// Space
				Spacer(
					modifier = Modifier
						.padding(horizontal = buttonSpacing))

				// No button
				SsNoIconTextButton(
					border = BorderStroke(
						width = borderWidth,
						color = noBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = noContentColor,
						backgroundColor = Color.Transparent))
				{
					isYes = false
					subtitle = it

					onDone(isYes, subtitle)
				}

			}

		}

	}

}
