package me.gabeg.sicksends

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.gabeg.sicksends.main.MAIN_SCREEN_ROUTE
import me.gabeg.sicksends.main.SsMainScreen
import me.gabeg.sicksends.onboarding.ONBOARDING_SCREEN_ROUTE
import me.gabeg.sicksends.onboarding.SsOnboardingScreen
import me.gabeg.sicksends.shared.SsSharedDataStore


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun SsSickSendsApp()
{
	MaterialTheme()
	{

		//val dataStore = SsSharedDataStore(LocalContext.current)
		//LaunchedEffect(true)
		//{
		//		dataStore.editIsAppFirstRun(true)
		//}

		// Navigation controller
		val navController = rememberNavController()

		// Get the starting navigation destination
		val startDestination = getStartNavDestination()

		// Determine which activity to start first
		println("START : $startDestination")
		NavHost(
			navController = navController,
			startDestination = startDestination)
		{

			// Onboarding screen
			composable(route = ONBOARDING_SCREEN_ROUTE) {
				SsOnboardingScreen(navController = navController)
			}

			// Main screen
			composable(route = MAIN_SCREEN_ROUTE) {
				val otherNavController = rememberNavController()

				SsMainScreen(navController = otherNavController)
			}

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

	// Check whether this is the app's first run and decide the navigation route
	// accordingly
	//val isAppFirstRun = dataStore.observeAppFirstRun()
	var isAppFirstRun: Boolean

	runBlocking {
		isAppFirstRun = dataStore.getAppFirstRunFlow().first()
	}

	// Return the appropriate starting destination
	return if (isAppFirstRun) ONBOARDING_SCREEN_ROUTE else MAIN_SCREEN_ROUTE
}
