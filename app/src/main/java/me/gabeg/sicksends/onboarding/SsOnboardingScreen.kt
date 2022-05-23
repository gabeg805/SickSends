package me.gabeg.sicksends.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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

const val ONBOARDING_SCREEN_ROUTE = "onboarding_screen_route"

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
	// TODO: Change the color/grading system button color
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Brush.verticalGradient(
				colors = listOf(colorResource(R.color.cyan_300), colorResource(R.color.blue_600)))))
	{

		HorizontalPager(
				modifier = Modifier.weight(10f),
				count = 3,
				state = pagerState,
				verticalAlignment = Alignment.Top)
		{ position ->

			 // Welcome screen. Call the callback when the "Get started"
			 // button is clicked
			 if (position == 0)
			 {
				  WelcomePage()
				  {
					  scope.launch {
						   pagerState.animateScrollToPage(1)
					  }
				  }
			 }

			 // Type of climbing the user will do
			 else if (position == 1)
			 {
				  TypeOfClimbingPage()
			 }

			 // Type of grades the user will use
			 else if (position == 2)
			 {
				  TypeOfGradingSystemPage()
			 }
		}

		HorizontalPagerIndicator(
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.weight(1f),
				pagerState = pagerState)

		DoneButton(
				modifier = Modifier.weight(1f),
				pagerState = pagerState)
		{

			// Indicate in the data store that this is no longer the app's first
			// run. The user has gone through the onboarding screen
			scope.launch {
				dataStore.editIsAppFirstRun(false)
			}

			// Go to the main screen
			navController.popBackStack()
			navController.navigate(MAIN_SCREEN_ROUTE)
		}

	}
}

/*
 * Done button beneath the page indicator.
 */
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun DoneButton(
	modifier: Modifier,
	pagerState: PagerState,
	onClick: () -> Unit)
{

	// Take up space that the button will occupy, so it does not suddenly pop into existence
	// TODO: Why does passing modifier here make it so that it does not suddenly pop in?
	Row(
		modifier = modifier
			.padding(horizontal = 32.dp),
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.Center)
	{

		// Show the button when "visible" is true
		AnimatedVisibility(
			modifier = Modifier.fillMaxWidth(),
			visible = pagerState.currentPage == 2)
		{

			// Done button
			Button(
				colors = ButtonDefaults.buttonColors(
					backgroundColor = Color.Magenta,
					contentColor = Color.White),
				shape = RoundedCornerShape(32.dp),
				onClick = onClick)
			{
				Text(text = "DONE", modifier = Modifier.padding(4.dp))
			}

		}
	}
}