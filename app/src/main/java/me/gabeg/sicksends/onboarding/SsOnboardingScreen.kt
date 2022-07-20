package me.gabeg.sicksends.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import me.gabeg.sicksends.R
import me.gabeg.sicksends.main.MAIN_SCREEN_ROUTE
import me.gabeg.sicksends.shared.SsSharedDataStore

/**
 * Route name.
 */
const val ONBOARDING_SCREEN_ROUTE = "onboarding_screen_route"

/**
 * Onboarding screen.
 */
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SsOnboardingScreen(navController: NavHostController)
{

	// Coroutine scope
	val scope = rememberCoroutineScope()

	// Pager state
	val pagerState = rememberPagerState()

	// Data store
	val dataStore = SsSharedDataStore(LocalContext.current)

	// Onboarding screen
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Brush.verticalGradient(
				colors = listOf(colorResource(R.color.cyan_300), colorResource(R.color.blue_600)))))
	{

		HorizontalPager(
			modifier = Modifier.weight(1f),
			count = 4,
			state = pagerState,
			verticalAlignment = Alignment.Top) { position ->

			// Determine which page to show
			when (position)
			{

				// Welcome
				0 ->
				{
					WelcomePage()
					{
						scope.launch {
							pagerState.animateScrollToPage(3)
						}
					}
				}

				// Type of climbing the user will do
				1 ->
				{
					SsAskAboutTypeOfClimbsPage()
				}

				// Type of grades the user will use
				2 ->
				{
					SsAskAboutGradingSystemPage()
				}

				// Type of problem questions the user wants to be asked
				3 ->
				{
					SsAskAboutProblemQuestionsPage()
				}
			}
		}

		HorizontalPagerIndicator(
				modifier = Modifier
					.align(Alignment.CenterHorizontally),
				pagerState = pagerState)

		SsNavigationButtons(
			pagerState = pagerState,
			onDone = {
				// Indicate in the data store that this is no longer the app's first
				// run. The user has gone through the onboarding screen
				//scope.launch {
			//		dataStore.editIsAppFirstRun(false)
			//	}

				// Go to the main screen
				navController.popBackStack()
				navController.navigate(MAIN_SCREEN_ROUTE)
			})

	}
}

/**
 * Navigation buttons to be Done, or go between the Next and Previous pages.
 */
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SsNavigationButtons(
	pagerState : PagerState,
	onDone : () -> Unit = {})
{

	// Take up space that the button will occupy, so it does not suddenly pop into existence
	// TODO: Why does passing modifier here make it so that it does not suddenly pop in?
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center)
	{

		TextButton(
			modifier = Modifier
				.width(0.dp),
			onClick = {},
			content = {})

		SsDoneButton(
			pagerState = pagerState,
			onDone = onDone)

		TextButton(
			modifier = Modifier
				.width(0.dp),
			onClick = {},
			content = {})

	}
}

/**
 * Done button beneath the page indicator.
 */
@ExperimentalPagerApi
@Composable
fun SsDoneButton(
	pagerState : PagerState,
	onDone : () -> Unit = {})
{
	AnimatedVisibility(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 32.dp),
		visible = pagerState.currentPage == 3)
	{

		// Done button
		Button(
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Color.Magenta,
				contentColor = Color.White),
			shape = RoundedCornerShape(32.dp),
			onClick = onDone)
		{
			Text(text = "DONE", modifier = Modifier.padding(4.dp))
		}

	}
}
