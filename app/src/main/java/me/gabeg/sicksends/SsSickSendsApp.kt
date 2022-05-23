package me.gabeg.sicksends

import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.rememberNavController
import me.gabeg.sicksends.main.MAIN_SCREEN_ROUTE
import me.gabeg.sicksends.main.SsMainScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.gabeg.sicksends.onboarding.ONBOARDING_SCREEN_ROUTE
import me.gabeg.sicksends.onboarding.SsOnboardingScreen
import me.gabeg.sicksends.shared.SsSharedDataStore

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun SsSickSendsApp()
{
	MaterialTheme()
	{
		val navController = rememberNavController()
		//val dataStore = SsSharedDataStore(LocalContext.current)

		//LaunchedEffect(true)
		//{
		//		dataStore.editIsAppFirstRun(true)
		//}

		SsSickSendsNavHost(navController = navController)

	}
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SsSickSendsNavHost(navController: NavHostController)
{

	// Get the starting navigation destination
	val startDestination = getStartNavDestination()

	// Determine which activity to start first
	NavHost(
		navController = navController,
		startDestination = startDestination)
	{

		// Start the onboarding screen
		composable(route = ONBOARDING_SCREEN_ROUTE) {
			SsOnboardingScreen(navController = navController)
		}

		// Start the main screen
		composable(route = MAIN_SCREEN_ROUTE) {
			SsMainScreen(navController = navController)
		}

	}
}

/**
 * Get the name of the starting navigation destination.
 *
 * @return The name of the starting navigation destination.
 */
@Composable
fun getStartNavDestination() : String
{

	// Prepare to query the data store
	val dataStore = SsSharedDataStore(LocalContext.current)

	// Prepare to check whether or not this is the app's first run, as well as
	// what the name of the desired navigation route is
	var isAppFirstRun by remember { mutableStateOf(true) }
	var startDestination by remember { mutableStateOf(MAIN_SCREEN_ROUTE) }

	// Check whether this is the app's first run and decide the navigation route
	// accordingly
	LaunchedEffect(true)
	{
		isAppFirstRun = dataStore.getAppFirstRun().first()

		// This is not the app's first run. Set the navigation route to the main
		// screen
		if (isAppFirstRun)
		{
			startDestination = ONBOARDING_SCREEN_ROUTE
		}
	}

	return startDestination
}
