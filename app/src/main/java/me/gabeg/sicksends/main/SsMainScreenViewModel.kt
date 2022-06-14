package me.gabeg.sicksends.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import me.gabeg.sicksends.R
import me.gabeg.sicksends.addproblem.ADD_PROBLEM_SCREEN_ROUTE
import me.gabeg.sicksends.boulder.BOULDER_SCREEN_ROUTE
import me.gabeg.sicksends.sport.SPORT_SCREEN_ROUTE
import me.gabeg.sicksends.toprope.TOP_ROPE_SCREEN_ROUTE
import me.gabeg.sicksends.trad.TRAD_SCREEN_ROUTE
import javax.inject.Inject
import kotlin.math.abs


/**
 * Add boulder problem view model.
 */
@HiltViewModel
class SsMainScreenViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle) : ViewModel()
	//navController : NavHostController) : ViewModel()
{

	/**
	 * Current navigation entry.
	 */
	//var currentNavEntry : NavBackStackEntry? = null
	var currentNavEntry : State<NavBackStackEntry?> = mutableStateOf(null)

	/**
	 * Height of the FAB, in pixels.
	 */
	var fabHeightPx : Int = 0

	/**
	 * Navigation entries to show the back button for.
	 */
	val navEntriesToShowBackButtonFor = listOf(ADD_PROBLEM_SCREEN_ROUTE)

	/**
	 * Nested scroll connection.
	 */
	val scrollConnection = object : NestedScrollConnection
	{

		override fun onPreScroll(available: Offset,
			source: NestedScrollSource): Offset
		{

			val delta = available.y
			//val newTopBarOffset = topBarOffsetHeightPx.value + delta

			//topBarOffsetHeightPx.value = newTopBarOffset.coerceIn(-topBarHeightPx, 0f)

			scrollBy(delta)

			//isFabVisible.value = shouldFabBeVisible()
			shouldFabBeVisible.value = abs(scrollTotal).toInt() != fabHeightPx

			return Offset.Zero
		}

	}

	/**
	 * The amount the screen has scrolled by. The max value of this is the
	 * height of the FAB.
	 */
	var scrollTotal : Float = 0f

	/**
	 * Whether the FAB should be visible.
	 */
	var shouldFabBeVisible = mutableStateOf(false)

	/**
	 * Get a list of all the navigation icons.
	 *
	 * @return A list of all the navigation icons.
	 */
	@Composable
	fun getAllNavigationIcons() : List<Painter>
	{
		return listOf(
			painterResource(R.mipmap.home),
			painterResource(R.mipmap.boulder),
			painterResource(R.mipmap.sport),
			painterResource(R.mipmap.top_rope),
			painterResource(R.mipmap.trad))
	}

	/**
	 * Get a list of all the navigation names.
	 *
	 * @return A list of all the navigation names.
	 */
	@Composable
	fun getAllNavigationNames() : List<String>
	{
		return listOf(
			stringResource(R.string.home),
			stringResource(R.string.boulder),
			stringResource(R.string.sport),
			stringResource(R.string.top_rope),
			stringResource(R.string.trad))
	}

	/**
	 * Current navigation route.
	 */
	fun getCurrentNavRoute() : String?
	{
		return currentNavEntry.value?.destination?.route
	}

	/**
	 * Get the navigation icon of the top bar.
	 */
	@Composable
	fun getTopBarNavigationIcon(
		content : @Composable () -> Unit = {}) : @Composable() (() -> Unit)?
	{
		if (shouldShowBackButtonInTopBar())
		{
			return content
		}
		else
		{
			return null
		}
	}

	/**
	 * Get the title of the top bar.
	 */
	@Composable
	fun getTopBarTitle() : String
	{
		val appName = stringResource(R.string.app_name)

		if (shouldShowBackButtonInTopBar())
		{
			return getCurrentNavRoute() ?: appName
		}
		else
		{
			return appName
		}
	}

	/**
	 * Convert a navigation route to a name.
	 */
	@Composable
	fun navigationRouteToName(route : String) : String
	{
		return when (route)
		{
			HOME_SCREEN_ROUTE        -> stringResource(R.string.home)
			BOULDER_SCREEN_ROUTE     -> stringResource(R.string.boulder)
			SPORT_SCREEN_ROUTE       -> stringResource(R.string.sport)
			TOP_ROPE_SCREEN_ROUTE    -> stringResource(R.string.top_rope)
			TRAD_SCREEN_ROUTE        -> stringResource(R.string.trad)
			ADD_PROBLEM_SCREEN_ROUTE -> "Add Problem"
			else                     -> ""
		}
	}

	/**
	 * Indicate to the FAB how much the screen has scrolled by in order to
	 * accurately determine if the FAB should be visible or not.
	 *
	 * This does not actually scroll the screen.
	 */
	fun scrollBy(delta : Float)
	{
		val newTotal = scrollTotal + delta

		scrollTotal = newTotal.coerceIn((-fabHeightPx).toFloat(), 0f)
	}

	/**
	 * Set the height of the FAB.
	 *
	 * @param height Height of the FAB (in pixels).
	 */
	fun setFabHeight(height : Int)
	{
		if (fabHeightPx == 0)
		{
			fabHeightPx = height
		}
	}

	/**
	 * Setup the navigation controller.
	 */
	//@Composable
	//fun setupNavController(navController: NavHostController)
	//{
	//	navController.enableOnBackPressed(true)

	//	//val currentEntry by navController.currentBackStackEntryAsState()

	//	//currentNavEntry = currentEntry
	//	currentNavEntry = navController.currentBackStackEntryAsState()
	//}

	/**
	 * Check whether to show the back button in the top bar.
	 *
	 * @return True if the back button should be shown in the top bar, and False
	 *     otherwise.
	 */
	@Composable
	fun shouldShowBackButtonInTopBar() : Boolean
	{
		return (getCurrentNavRoute() in navEntriesToShowBackButtonFor)
	}

	/**
	 * Check whether the FAB should be visible or not.
	 *
	 * @return True if the FAB should be visible, and False otherwise.
	 */
	//fun shouldFabBeVisible() : Boolean
	//{
	//	return abs(scrollTotal).toInt() != fabHeightPx
	//}

}

//@InstallIn(SingletonComponent::class)
//@Module
//class SsMainScreenModule
//{
//
//	/**
//	 * Provide the navigation host controller.
//	 */
//	@Composable
//	@Provides
//	fun provideNavHostController() : NavHostController
//	{
//		return rememberNavController()
//	}
//
//}