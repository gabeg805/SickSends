package me.gabeg.sicksends.main

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import me.gabeg.sicksends.addproblem.boulder.SsAddBoulderProblemScreen
import me.gabeg.sicksends.addproblem.generic.ADD_PROBLEM_SCREEN_ROUTE
import me.gabeg.sicksends.addproblem.sport.SsAddSportProblemScreen
import me.gabeg.sicksends.addproblem.toprope.SsAddTopRopeProblemScreen
import me.gabeg.sicksends.addproblem.trad.SsAddTradProblemScreen
import me.gabeg.sicksends.boulder.BOULDER_SCREEN_ROUTE
import me.gabeg.sicksends.boulder.SsBoulderScreen
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.problem.ui.*
import me.gabeg.sicksends.shared.SsSharedDataStore
import me.gabeg.sicksends.sport.SPORT_SCREEN_ROUTE
import me.gabeg.sicksends.sport.SsSportScreen
import me.gabeg.sicksends.toprope.SsTopRopeScreen
import me.gabeg.sicksends.toprope.TOP_ROPE_SCREEN_ROUTE
import me.gabeg.sicksends.trad.SsTradScreen
import me.gabeg.sicksends.trad.TRAD_SCREEN_ROUTE
import me.gabeg.sicksends.ui.*

const val MAIN_SCREEN_ROUTE = "main_screen_route"

// TODO: Look into the back button at the top bar and also subtitles in the top
//  bar
// TODO: Hide the floating action button when in the Home screen
@Composable
fun SsMainScreen(
	navController : NavHostController,
	viewModel : SsMainScreenViewModel = hiltViewModel())
{

	val dataStore = SsSharedDataStore(LocalContext.current)
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	// TODO: rememberSaveable() pass this to function to decouple how the data
	//  is saved with how it is used.
	// TODO: Move this to Application and remove scope when done testing
	val db = SsProblemDatabase.getInstance(LocalContext.current, scope)

	// TODO: Testing out hiding FAB
	val lazyListState = rememberLazyListState()

	//val topBarHeight = 56.dp
	//val topBarHeightPx = with(LocalDensity.current) { topBarHeight.roundToPx().toFloat() }
	//val topBarOffsetHeightPx = remember { mutableStateOf(0f) }

	//LaunchedEffect(lazyListState) {
	//	snapshotFlow { lazyListState.isScrollInProgress }
	//		.collect {
	//			Log.i("QWEQRUYTIUT", "Is scrolling? ${lazyListState.isScrollInProgress} || Value : $it")
	//		}
	//}

	navController.enableOnBackPressed(true)

	/**
	 * Scaffold
	 */
	Scaffold(
		modifier = Modifier
			.nestedScroll(viewModel.scrollConnection),
		scaffoldState = scaffoldState,
		topBar = {
			viewModel.currentNavEntry = navController.currentBackStackEntryAsState()

			//SsTopAppBar(navController, viewModel, topBarOffsetHeightPx) { menuItem ->
			SsTopAppBar(
				viewModel = viewModel,
				onMenuItemClicked = { menuItem ->
					if (menuItem == "Search")
					{
						viewModel.drawerState.toggle()
					}
				},
				onBackPressed = {
					navController.navigateUp()
				})
		},
		bottomBar = {
			SsBottomNavigationBar(
				viewModel = viewModel) { index, name ->

				navController.popBackStack()
				navController.navigate(name)

			}
		},
		floatingActionButton = {
			SsFloatingActionButton(viewModel)
			{
				viewModel.previousRoute = viewModel.currentRoute

				navController.navigate(ADD_PROBLEM_SCREEN_ROUTE)
				//navController.navigate(ADD_PROBLEM_SCREEN_ROUTE + "/$previousRoute")
			}
		},
		floatingActionButtonPosition = FabPosition.End,
		content = { innerPadding ->

			SsSearchTopDrawer(viewModel, innerPadding)

			// Navigation host which allows navigation to occur
			NavHost(navController, startDestination = HOME_SCREEN_ROUTE)
			{

				// Home
				composable(route = HOME_SCREEN_ROUTE)
				{
					viewModel.hideFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = true
					SsHomeScreen(innerPadding)
				}

				// Add climb
				//composable(route = ADD_PROBLEM_SCREEN_ROUTE + "/{climbType}")
				//navBackStack.arguments?.getString("climbType")
				composable(route = ADD_PROBLEM_SCREEN_ROUTE)
				{
					viewModel.hideFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = false

					// What to do when the screen is done
					val onDone : () -> Unit = {
						navController.popBackStack()
					}

					// Show the screen
					when (viewModel.previousRoute)
					{
						BOULDER_SCREEN_ROUTE  -> SsAddBoulderProblemScreen(onDone = onDone)
						SPORT_SCREEN_ROUTE    -> SsAddSportProblemScreen(onDone = onDone)
						TOP_ROPE_SCREEN_ROUTE -> SsAddTopRopeProblemScreen(onDone = onDone)
						TRAD_SCREEN_ROUTE     -> SsAddTradProblemScreen(onDone = onDone)
						else                  -> {}
					}
				}

				// Boulder
				composable(route = BOULDER_SCREEN_ROUTE)
				{
					viewModel.showFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = true
					SsBoulderScreen(viewModel.queryState, lazyListState, innerPadding)
				}

				// Sport
				composable(route = SPORT_SCREEN_ROUTE)
				{
					viewModel.showFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = true
					SsSportScreen(viewModel.queryState, lazyListState, innerPadding)
				}

				// Top rope
				composable(route = TOP_ROPE_SCREEN_ROUTE)
				{
					viewModel.showFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = true
					SsTopRopeScreen(viewModel.queryState, lazyListState, innerPadding)
				}

				// Trad
				composable(route = TRAD_SCREEN_ROUTE)
				{
					viewModel.showFab()
					viewModel.shouldBottomNavigationBarBeVisible.value = true
					SsTradScreen(viewModel.queryState, lazyListState, innerPadding)
				}

			}

		})
}

/**
 * Create the bottom navigation bar.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SsBottomNavigationBar(
	viewModel: SsMainScreenViewModel,
	onNavigationItemClicked : (index : Int, name : String) -> Unit)
{

	// The currently selected item
	var selectedIndex by remember { mutableStateOf(0) }

	// Animate the visibility of the FAB
	AnimatedVisibility(
		visible = viewModel.shouldBottomNavigationBarBeVisible.value,
		enter = slideInVertically(
			animationSpec = tween(durationMillis = 250),
			initialOffsetY = { it }),
		exit = slideOutVertically(
			animationSpec = tween(durationMillis = 250),
			targetOffsetY = { it }))
	{

		// Create the bottom navigation bar
		// TODO: Maybe pass IntrisicSize?
		BottomNavigation {

			// Iterate over each navigation item the user will use
			for ((index, name, icon) in viewModel.getAllNavigationItemsWillUse())
			{

				// Create a navigation item
				// TODO: Maybe set default size for icon?
				BottomNavigationItem(
					icon = {
						Icon(icon,
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
 * Create the floating action button.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SsFloatingActionButton(
	viewModel: SsMainScreenViewModel,
	onClick: () -> Unit = {})
{

	// Animate the visibility of the FAB
	AnimatedVisibility(
		visible = viewModel.fabVisibilityOnScroll,
		enter = scaleIn(),
		exit = scaleOut())
	{

		// Create the FAB
		FloatingActionButton(
			modifier = Modifier
				.onGloballyPositioned { coordinates ->
					viewModel.setFabHeight(coordinates.size.height)
				},
			onClick = { onClick() })
		{
			SsAddIcon()
		}

	}
}

@Composable
fun SsSearchTopDrawer(viewModel: SsMainScreenViewModel, padding : PaddingValues)
{

	val outdoorOptions = listOf("Either", "Outdoor", "Indoor")
	val projectOptions = listOf("Either", "Project", "Send")
	val flashOptions = listOf("Either", "Flash", "Not flashed")

	val outdoorSearchFilter = remember { SsDropdownSearchFilterState(0) }
	val projectSearchFilter = remember { SsDropdownSearchFilterState(0) }
	val flashSearchFilter = remember { SsDropdownSearchFilterState(0) }

	SsTopDrawer(
		scaffoldPadding = padding,
		modifier = Modifier
			.padding(16.dp),
		state = viewModel.drawerState)
	{

		val iconPadding = 16.dp
		var textWidth = with(LocalDensity.current) {
			//MaterialTheme.typography.body1.fontSize.toDp() * (outdoorOptions[1].length+1) //* 3 / 2
			MaterialTheme.typography.body1.fontSize.toDp() * flashOptions[2].length //* 3 / 2
		}

		/**
		 */
		SsDropdownSearchFilter(
			state = outdoorSearchFilter,
			allOptions = outdoorOptions,
			description = "Outdoor or Indoor?",
			dropdownWidth = textWidth)
		{
			SsOutdoorIcon(
				modifier = Modifier
					.padding(horizontal = iconPadding)
					then(Modifier.size(16.dp)))
		}

		/**
		 */
		SsDropdownSearchFilter(
			state = projectSearchFilter,
			allOptions = projectOptions,
			description = "Project or Send?",
			dropdownWidth = textWidth)
		{
			SsProjectIcon(
				modifier = Modifier
					.padding(horizontal = iconPadding)
					then(Modifier.size(18.dp)))
		}

		/**
		 */
		SsDropdownSearchFilter(
			state = flashSearchFilter,
			allOptions = flashOptions,
			description = "Flash or not?",
			dropdownWidth = textWidth)
		{
			SsFlashIcon(
				modifier = Modifier
					.padding(horizontal = iconPadding)
					then(Modifier.size(20.dp)))
		}

		/**
		 */
		Button(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 16.dp),
			onClick = {
				viewModel.queryState.outdoor = outdoorSearchFilter.toQuery()
				viewModel.queryState.project = projectSearchFilter.toQuery()
				viewModel.queryState.flash = flashSearchFilter.toQuery()

				viewModel.drawerState.close()
			})
		{
			Text("Apply")
		}

	}

}

/**
 * Build the top bar.
 *
 * TODO: Expand this so that user can search for words.
 */
@Composable
fun SsTopAppBar(
	viewModel: SsMainScreenViewModel,
	//offsetHeight : MutableState<Float>,
	onMenuItemClicked : (menuItem: String) -> Unit = {},
	onBackPressed : () -> Unit = {})
{

	TopAppBar(
		//modifier = Modifier
		//	.offset { IntOffset(x = 0, y = offsetHeight.value.roundToInt()) },
		title = { Text(viewModel.getTopBarTitle()) },
		navigationIcon = viewModel.getTopBarNavigationIcon {
			IconButton(
				onClick = { onBackPressed() })
			{
				SsBackIcon()
			}
		},
		actions = {

			// Overflow menu items and state
			val menuItems = listOf("Settings")
			val state = rememberSsDropdownMenuState(0)

			// Search Icon
			IconButton(
				onClick = { onMenuItemClicked("Search") })
			{
				SsSearchIcon()
			}

			// Overflow icon
			IconButton(
				onClick = { state.toggle() })
			{

				SsOverflowIcon()

				SsDropdownMenu(
					options = menuItems,
					state = state,
					onMenuItemClickedListener = { menuIndex, menuItem ->
						Log.i("SsMainScreen", "TODO: Launch the Settings component")
						onMenuItemClicked(menuItem)
					})

			}

		})

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
