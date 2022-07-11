package me.gabeg.sicksends.addproblem

import android.view.KeyEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.gabeg.sicksends.SsLongClickButton
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.addproblem.boulder.SsAddBoulderProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel.Companion.getSubtitle
import me.gabeg.sicksends.addproblem.generic.question.*
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.shared.getAllHoldNames
import me.gabeg.sicksends.shared.getYesOrNo
import me.gabeg.sicksends.ui.*
import me.gabeg.sicksends.util.toNames
import me.gabeg.sicksends.util.toggle
import java.util.*

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
	val askLocation = dataStore.observeQuestionLocation()
	val askNote = dataStore.observeQuestionNote()
	val askHold = dataStore.observeQuestionHold()
	val askWallFeature = dataStore.observeQuestionWallFeature()
	val askClimbingTechnique = dataStore.observeQuestionClimbingTechnique()

	println("Ask name     ? $askName")
	println("Ask flash    ? $askFlash")
	println("Ask attempt  ? $askNumAttempt")
	println("Ask project  ? $askProject")
	println("Ask outdoor  ? $askOutdoor")
	println("Ask location ? $askLocation")
	println("Ask note     ? $askNote")
	println("Ask hold     ? $askHold")

	LaunchedEffect(true)
	{
		println("Showing 0 index!")
		viewModel.show(0)
	}

	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.padding(vertical = 24.dp, horizontal = 16.dp),
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
					SsFlashQuestion(
						viewModel = viewModel,
						scrollState = scrollState)
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
		 */
		if (askProject)
		{
			item {
				SsProjectQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
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

		/**
		 * Holds
		 */
		if (askHold)
		{
			item {
				SsHoldQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		/**
		 * Wall features
		 */
		if (askWallFeature)
		{
			item {
				SsWallFeatureQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		/**
		 * Climbing techniques
		 */
		if (askClimbingTechnique)
		{
			item {
				SsClimbingTechniqueQuestion(
					viewModel = viewModel,
					scrollState = scrollState)
			}
		}

		// TODO: Media/images/videos

		item {
			Spacer(modifier = Modifier.padding(vertical = 32.dp))
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
 * Question widget.
 */
@Composable
fun SsQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	icon : @Composable BoxScope.(
		modifier : Modifier) -> Unit,
	body : @Composable (
		visible : Boolean,
		onDone : () -> Unit) -> Unit = {_,_ -> },
	index : Int,
	scrollState : LazyListState = rememberLazyListState(),
	onClick : () -> Unit = {
		viewModel.showOnly(index)
	},
	onDone : suspend CoroutineScope.() -> Unit = {
		viewModel.showOnly(index+1)
		delay(250)
		scrollState.animateScrollToItem(index)
	})
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

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

				val bottom : Dp by animateDpAsState(
					if ((index == 0) && isVisible)
						64.dp
					else
						32.dp
				)

				// Body
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { onClick() }
						.padding(top = 0.dp, bottom = bottom, start = 0.dp, end = 16.dp))
				//.onGloballyPositioned { coord ->
				//		bodyHeight = with(localDensity) { coord.size.height.toDp() + 32.dp }
				//		println("Height : $index   $bodyHeight")
				//	})
				{
					body(isVisible)
					{
						scope.launch {
							println("CAAAAAALL onDone! $index")
							onDone()
						}
					}
				}

			})

	}

}

/**
 * Create the body.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsBody(
	title : String,
	subtitle : String,
	note : String = "",
	modifier : Modifier = Modifier,
	pagerState : PagerState? = null,
	content : @Composable () -> Unit = {})
{

	val titlePadding : Dp by animateDpAsState(if (subtitle.isEmpty()) 12.dp else 0.dp)
	val subtitlePadding : Dp by animateDpAsState(if (subtitle.isEmpty()) 16.dp else 32.dp)

	// Parent
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.Start)
	{

		Row(
			modifier = Modifier
				.padding(top = titlePadding),
			verticalAlignment = Alignment.CenterVertically)
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
				.padding(bottom = if (note.isEmpty()) subtitlePadding else 0.dp),
			//maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			fontSize = MaterialTheme.typography.h6.fontSize,
			fontWeight = FontWeight.SemiBold)

		// Note
		if (note.isNotEmpty())
		{
			Text(note,
				modifier = Modifier
					.padding(bottom = subtitlePadding),
				fontSize = MaterialTheme.typography.subtitle2.fontSize,
				fontWeight = FontWeight.Light)
		}

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
			.clickable {
				onClick()
			}
			.background(
				color = Color.White,
				shape = CircleShape)
			.border(
				width = 3.dp,
				color = borderColor,
				shape = CircleShape))
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
	modifier : Modifier = Modifier,
	singleLine : Boolean = false,
	maxLines : Int = Int.MAX_VALUE,
	visible : Boolean = true,
	onTextChange : (String) -> Unit = {},
	onDone : (String) -> Unit = {})
{

	// Subtitle
	val subtitle = getSubtitle(initial, question, visible)

	// Text
	var text by remember { mutableStateOf(initial) }

	// Request focus to show the keyboard
	val focusRequester = remember { FocusRequester() }
	var imeAction = if (singleLine) ImeAction.Next else ImeAction.Default

	// Body
	SsBody(title, subtitle)
	{

		// Animate visibility as needed
		AnimatedVisibility(visible = visible)
		{

			OutlinedTextField(
				modifier = modifier
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
							onDone(text)
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

					onTextChange(text)
				},
				singleLine = singleLine,
				maxLines = maxLines,
				keyboardOptions = KeyboardOptions(imeAction = imeAction),
				keyboardActions = KeyboardActions(
					onNext = {
						onDone(text)
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
	question : String,
	note : String = "",
	initialState : Boolean? = null,
	disableYesButton : Boolean = false,
	disableNoButton : Boolean = false,
	visible : Boolean = true,
	onDisabled : (Boolean?) -> Unit = {},
	onDone : (Boolean?) -> Unit = {})
{

	// Get the subtitle
	val subtitle = getSubtitle(initialState, question, visible)

	// Body
	SsBody(title, subtitle, note = note)
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

			val buttonSpacing = 16.dp
			val borderWidth = 2.dp
			val disabledAlpha = 0.3f

			// Yes button attributes
			val yesBorderColor = if (initialState == true) Color.Magenta else Color.LightGray
			val yesContentColor = if (initialState == true) Color.Magenta else Color.Black
			val yesAlpha = if (disableYesButton) disabledAlpha else 1f

			// No button attributes
			val noBorderColor = if (initialState == false) Color.Magenta else Color.LightGray
			val noContentColor = if (initialState == false) Color.Magenta else Color.Black
			val noAlpha = if (disableNoButton) disabledAlpha else 1f

			// Skip button attributes
			val skipBorderColor = if (initialState == null) Color.Magenta else Color.LightGray
			val skipContentColor = if (initialState == null) Color.Magenta else Color.Black

			// TODO: If the buttons took up 50% of the width, that might look
			// better?
			//modifier = Modifier.fillMaxWidth())
			//horizontalArrangement = Arrangement.SpaceAround)
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center)
			{

				// Yes button
				SsYesIconTextButton(
					modifier = Modifier
						.alpha(yesAlpha),
					border = BorderStroke(
						width = borderWidth,
						color = yesBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = yesContentColor,
						backgroundColor = Color.Transparent))
				{
					// Disabled
					if (disableYesButton)
					{
						onDisabled(true)
					}
					// Done
					else
					{
						//state = true
						//onDone(state)

						onDone(true)
					}
				}

				// Space
				Spacer(
					modifier = Modifier
						.padding(horizontal = buttonSpacing))

				// No button
				SsNoIconTextButton(
					modifier = Modifier
						.alpha(noAlpha),
					border = BorderStroke(
						width = borderWidth,
						color = noBorderColor),
					colors = ButtonDefaults.buttonColors(
						contentColor = noContentColor,
						backgroundColor = Color.Transparent))
				{
					// Disabled
					if (disableNoButton)
					{
						onDisabled(false)
					}
					// Done
					else
					{
						onDone(false)
					}
				}

				//// Space
			//	Spacer(
			//		modifier = Modifier
			//			.padding(horizontal = buttonSpacing))

			//	// Skip button
			//	SsSkipIconTextButton(
			//		border = BorderStroke(
			//			width = borderWidth,
			//			color = skipBorderColor),
			//		colors = ButtonDefaults.buttonColors(
			//			contentColor = skipContentColor,
			//			backgroundColor = Color.Transparent))
			//	{
			//		onDone(null)
			//	}

			}

		}

	}

}

/**
 * Body that contains a button toggle group.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
inline fun <reified T : Enum<T>> SsButtonToggleGroupBody(
	title : String,
	question : String,
	initialState : EnumSet<T>,
	allStateNames : List<String>,
	visible : Boolean = true,
	noinline onClick : (EnumSet<T>) -> Unit = {},
	noinline onDone : () -> Unit = {})
{

	// State names
	val names = initialState.toNames(allStateNames)

	// Subtitle
	val subtitle = getSubtitle(names, question, visible)

	println("$title : $initialState || Visible : $visible || Names : $names")

	// Body
	SsBody(title, subtitle)
	{

		AnimatedVisibility(visible = visible)
		{

			Column()
			{

				// Show all the buttons
				SsButtonToggleGroup(
					items = allStateNames,
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 16.dp, vertical = 4.dp),
					numPerRow = 2,
					select = names,
					onClick = { index, name, isChecked ->

						// Get the enum that was selected
						val allEnums = enumValues<T>()
						val enum = allEnums[index]

						// Create a copy of the new state
						val newState = EnumSet.copyOf(initialState)

						// Toggle the enum
						newState.toggle(enum)

						// Call the onClick() function with the new state
						onClick(newState)
					})


				// Continue/Skip button
				SsContinueSkipButton(
					state = initialState.isNotEmpty(),
					onClick = {
						onDone()
					})

			}
		}

	}

}

/**
 * Continue or skip button.
 *
 * @param state Whether to continue or skip. True is for continue and False is
 *              for skip.
 */
@Composable
fun SsContinueSkipButton(
	modifier : Modifier = Modifier
		.fillMaxWidth()
		.padding(vertical = 32.dp),
	state : Boolean = true,
	colors : ButtonColors = ButtonDefaults.buttonColors(
		backgroundColor = Color.Magenta,
		contentColor = Color.White),
	shape : Shape = RoundedCornerShape(32.dp),
	onContinue : () -> Unit = {},
	onSkip : () -> Unit = {},
	onClick : (() -> Unit)? = null)
{
	SsLongClickButton(
		modifier = modifier,
		colors = colors,
		shape = shape,
		onClick = {

			// Click
			if (onClick != null)
			{
				onClick()
			}
			else
			{
				// Continue
				if (state)
				{
					onContinue()
				}
				// Skip
				else
				{
					onSkip()
				}
			}

		})
	{
		Crossfade(targetState = state)
		{
			val text = if (it) "CONTINUE" else "SKIP"

			Text(text,
				modifier = Modifier
					.fillMaxWidth(),
				textAlign = TextAlign.Center)
		}
	}
}
