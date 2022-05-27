package me.gabeg.sicksends.main

import android.util.Log
import android.widget.SearchView
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.relocationRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.gabeg.sicksends.R
import me.gabeg.sicksends.boulder.SsBoulderScreen
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.shared.SsSharedDataStore
import me.gabeg.sicksends.sport.SsSportScreen
import me.gabeg.sicksends.toprope.SsTopRopeScreen
import me.gabeg.sicksends.trad.SsTradScreen
import me.gabeg.sicksends.ui.SsDrawerState
import me.gabeg.sicksends.ui.SsEndDrawer
import me.gabeg.sicksends.ui.SsTopDrawer
import me.gabeg.sicksends.ui.ToggleButton
import kotlin.math.abs
import kotlin.math.roundToInt

const val MAIN_SCREEN_ROUTE = "main_screen_route"

// TODO: Look into the back button at the top bar and also subtitles in the top
//  bar
// TODO: Hide the floating action button when in the Home screen
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SsMainScreen(navController: NavHostController)
{
	val dataStore = SsSharedDataStore(LocalContext.current)
	val scaffoldState = rememberScaffoldState()
	val scope = rememberCoroutineScope()

	// TODO: rememberSaveable() pass this to function to decouple how the data
	//  is saved with how it is used.
	// TODO: Move this to Application and remove scope when done testing
	val db = SsProblemDatabase.getInstance(LocalContext.current, scope)
	val navItemNames = dataStore.getAllNavigationNames()

	// TODO: Testing out hiding FAB
	val lazyListState = rememberLazyListState()

	val topBarHeight = 56.dp
	val topBarHeightPx = with(LocalDensity.current) { topBarHeight.roundToPx().toFloat() }

	val topBarOffsetHeightPx = remember { mutableStateOf(0f) }
	var isFabVisible by remember { mutableStateOf(false) }

	//LaunchedEffect(lazyListState) {
	//	snapshotFlow { lazyListState.isScrollInProgress }
	//		.collect {
	//			Log.i("QWEQRUYTIUT", "Is scrolling? ${lazyListState.isScrollInProgress} || Value : $it")
	//		}
	//}

	val nestedScrollConnection = remember {
		object : NestedScrollConnection
		{

			override fun onPreScroll(available: Offset,
				source: NestedScrollSource) : Offset
			{

				val delta = available.y
				val newTopBarOffset = topBarOffsetHeightPx.value + delta

				topBarOffsetHeightPx.value = newTopBarOffset.coerceIn(-topBarHeightPx, 0f)

				FabVisibilityConsultant.scrollBy(delta)

				isFabVisible = FabVisibilityConsultant.shouldBeVisible()

				return Offset.Zero
			}

		}
	}

	//var whereProblem = remember { WhereProblem() }
	var whereProblem = remember { mutableStateOf(WhereProblem()) }
	var drawerState = remember { SsDrawerState() }

	Scaffold(
		modifier = Modifier
			.nestedScroll(nestedScrollConnection),
		scaffoldState = scaffoldState,
		topBar = {
			buildTopBar(topBarOffsetHeightPx) { menuItem ->

				if (menuItem == "Search")
				{
					drawerState.toggle()
				}

			}
		},
		bottomBar = {
			buildBottomNavigationBar(dataStore = dataStore) { index, name ->
				//navController.popBackStack()
				navController.navigate(name)
			}
		},
		floatingActionButton = {

			AnimatedVisibility(
				visible = isFabVisible,
				enter = scaleIn(),
				exit = scaleOut())
			{
				buildFloatingActionButton()
			}

		},
		floatingActionButtonPosition = FabPosition.End,
		content = { innerPadding ->

			SsTopDrawer(
				scaffoldPadding = innerPadding,
				modifier = Modifier
					.padding(16.dp),
					//.width(IntrinsicSize.Max),
			state = drawerState)
			{

				var filterNames = remember { listOf(
					"Indoor", "Outdoor",
					"Project", "Send",
					"Flash", "Normal",
					"All" )}

				var isFilterSelected = remember { mutableStateListOf(
					false, false,
					false, false,
					false, false,
					true )}

				val size = filterNames.size

				//Text("Filter by...",
				//		modifier = Modifier
				//			.padding(bottom = 16.dp),
				//		fontWeight = FontWeight.SemiBold,
				//		//fontStyle = FontStyle.Italic,
				//		textAlign = TextAlign.Center)

				// TODO: Maybe? This is entirely redrawn when a button is clicked
				//for (i in 0 until size-2 step 2)
			//	{

			//		Row()
			//		{
			//			for (j in i until i + 2)
			//			{
			//				ToggleButton(filterNames[j], state = isFilterSelected[j])
			//				{ text, isSelected ->

			//					isFilterSelected[j] = isSelected

			//					if (isSelected)
			//					{
			//						isFilterSelected[size-1] = false
			//					}
			//				}

			//				if ((j % 2) == 0)
			//				{
			//					Spacer(modifier = Modifier.padding(2.dp))
			//				}
			//			}
			//		}

			//	}

				//Row()
			//	{
			//		ToggleButton("Indoor", state = !isAllClicked)
			//		{ text, isSelected ->
			//			isAllClicked = !isSelected.value
			//		}

			//		Spacer(modifier = Modifier.padding(2.dp))

			//		ToggleButton("Outdoor", state = !isAllClicked)
			//		{ text, isSelected ->
			//			isAllClicked = !isSelected.value
			//		}
			//	}

			//	Row()
			//	{
			//		ToggleButton("Project")
			//		Spacer(modifier = Modifier.padding(2.dp))
			//		ToggleButton("Send")
			//	}

			//	Row()
			//	{
			//		ToggleButton("Flash")
			//		Spacer(modifier = Modifier.padding(2.dp))
			//		ToggleButton("Normal")
			//	}

				//Row()
			//	{
			//		ToggleButton("All", state = isFilterSelected.last())
			//		{ text, isSelected ->

			//			if (isSelected)
			//			{
			//				for (i in isFilterSelected.indices)
			//				{
			//					isFilterSelected[i] = false
			//				}
			//			}

			//			isFilterSelected[size-1] = isSelected

			//			drawerState.close()
			//		}
			//	}


				val outdoorOptions = listOf("Either", "Indoor", "Outdoor")
				val projectOptions = listOf("Either", "Project", "Send")
				val flashOptions = listOf("Either", "Flash", "Not flashed")

				var isOutdoorExpanded by remember { mutableStateOf(false) }
				var isProjectExpanded by remember { mutableStateOf(false) }
				var isFlashExpanded by remember { mutableStateOf(false) }

				var selectedOutdoorOption by remember { mutableStateOf(outdoorOptions[0]) }
				var selectedProjectOption by remember { mutableStateOf(projectOptions[0]) }
				var selectedFlashOption by remember { mutableStateOf(flashOptions[0]) }

				val iconPadding = 16.dp
				var textWidth = with(LocalDensity.current) {
					//MaterialTheme.typography.body1.fontSize.toDp() * (outdoorOptions[2].length+1) //* 3 / 2
					MaterialTheme.typography.body1.fontSize.toDp() * flashOptions[2].length //* 3 / 2
				}

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically)
				{

					Icon(Icons.Default.Park,
						modifier = Modifier
							.padding(horizontal = iconPadding)
							then(Modifier.size(16.dp)),
						contentDescription = "Outdoor")

					Text("Outdoor or Indoor?",
						modifier = Modifier
							.weight(1f),
						fontWeight = FontWeight.SemiBold)
					//modifier = Modifier.padding(end = 8.dp))

					//Spacer(modifier = Modifier.weight(1f))

					ExposedDropdownMenuBox(
						//modifier = Modifier.weight(2f),
						expanded = isOutdoorExpanded,
						onExpandedChange = {
							isOutdoorExpanded = !isOutdoorExpanded
						})
					{
						TextField(
							modifier = Modifier.width(textWidth),
							value = selectedOutdoorOption,
							onValueChange = { },
							trailingIcon = {
								ExposedDropdownMenuDefaults.TrailingIcon(
									expanded = isOutdoorExpanded
								)
							},
							readOnly = true,
							colors = ExposedDropdownMenuDefaults.textFieldColors())

						ExposedDropdownMenu(
							modifier = Modifier
								.exposedDropdownSize(true),
							expanded = isOutdoorExpanded,
							onDismissRequest = {
								isOutdoorExpanded = false
							})
						{
							outdoorOptions.forEach { option ->
								DropdownMenuItem(
									onClick = {
										selectedOutdoorOption = option
										isOutdoorExpanded = false
									})
								{
									Text(option)
								}
							}
						}
					}

				}

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically)
				{

					Icon(Icons.Default.Construction,
						modifier = Modifier
							.padding(horizontal = iconPadding)
							then(Modifier.size(18.dp)),
					contentDescription = "Project")

					Text("Project or Send?",
						modifier = Modifier
							.weight(1f),
						fontWeight = FontWeight.SemiBold)
					//modifier = Modifier.padding(end = 8.dp))

					//Spacer(modifier = Modifier.weight(1f))

					ExposedDropdownMenuBox(
						//modifier = Modifier.weight(2f),
						expanded = isProjectExpanded,
						onExpandedChange = {
							isProjectExpanded = !isProjectExpanded
						})
					{
						TextField(
							modifier = Modifier.width(textWidth),
							value = selectedProjectOption,
							onValueChange = { },
							trailingIcon = {
								ExposedDropdownMenuDefaults.TrailingIcon(
									expanded = isProjectExpanded
								)
							},
							readOnly = true,
							colors = ExposedDropdownMenuDefaults.textFieldColors())

						ExposedDropdownMenu(
							modifier = Modifier
								.exposedDropdownSize(true),
							expanded = isProjectExpanded,
							onDismissRequest = {
								isProjectExpanded = false
							})
						{
							projectOptions.forEach { option ->
								DropdownMenuItem(
									onClick = {
										selectedProjectOption = option
										isProjectExpanded = false
									})
								{
									Text(option)
								}
							}
						}
					}

				}

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically)
				{

					Icon(Icons.Default.Bolt,
						modifier = Modifier
							.padding(horizontal = iconPadding)
							then(Modifier.size(20.dp)),
					contentDescription = "Flash")

					Text("Flash or not?",
						modifier = Modifier
							.weight(1f),
						fontWeight = FontWeight.SemiBold)
					//modifier = Modifier.padding(end = 8.dp))

					//Spacer(modifier = Modifier.weight(1f))

					ExposedDropdownMenuBox(
						//modifier = Modifier.weight(2f),
						expanded = isFlashExpanded,
						onExpandedChange = {
							isFlashExpanded = !isFlashExpanded
						})
					{
						TextField(
							modifier = Modifier.width(textWidth),
							value = selectedFlashOption,
							onValueChange = { },
							trailingIcon = {
								ExposedDropdownMenuDefaults.TrailingIcon(
									expanded = isFlashExpanded
								)
							},
							readOnly = true,
							colors = ExposedDropdownMenuDefaults.textFieldColors())

						ExposedDropdownMenu(
							modifier = Modifier
								.exposedDropdownSize(true),
							expanded = isFlashExpanded,
							onDismissRequest = {
								isFlashExpanded = false
							})
						{
							flashOptions.forEach { option ->
								DropdownMenuItem(
									onClick = {
										selectedFlashOption = option
										isFlashExpanded = false
									})
								{
									Text(option)
								}
							}
						}
					}

				}

				Button(
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 16.dp),
					onClick = {
						//whereProblem.isIndoor = isFilterSelected[0]
					//	whereProblem.isOutdoor  = isFilterSelected[1]
					//	whereProblem.isProject = isFilterSelected[2]
					//	whereProblem.isSend = isFilterSelected[3]
					//	whereProblem.isFlash = isFilterSelected[4]
					//	whereProblem.isNormal = isFilterSelected[5]

						var wp = WhereProblem()

						wp.isOutdoor  = if (selectedOutdoorOption == outdoorOptions[0]) null else (selectedOutdoorOption == outdoorOptions[2])
						wp.isProject = if (selectedProjectOption == projectOptions[0]) null else (selectedProjectOption == projectOptions[1])
						wp.isFlash = if (selectedFlashOption == flashOptions[0]) null else (selectedFlashOption == flashOptions[1])

						//wp.isIndoor = isFilterSelected[0]
						//wp.isOutdoor  = isFilterSelected[1]
						//wp.isProject = isFilterSelected[2]
						//wp.isSend = isFilterSelected[3]
						//wp.isFlash = isFilterSelected[4]
						//wp.isNormal = isFilterSelected[5]

						whereProblem.value = wp

						//Log.i("isIndoor?", isFilterSelected[0].toString())
						//Log.i("isOutdoor?", isFilterSelected[1].toString())
						//Log.i("isProject?", isFilterSelected[2].toString())
						//Log.i("isSend?", isFilterSelected[3].toString())
						//Log.i("isFlash?", isFilterSelected[4].toString())
						//Log.i("isNormal?", isFilterSelected[5].toString())
					})
				{
					Text("Apply")
				}
			}


			// Navigation host which allows navigation to occur
			NavHost(navController, startDestination = navItemNames[0])
			{

				// Home
				composable(route = navItemNames[0])
				{
					isFabVisible = false
					SsHomeScreen(db, innerPadding)
				}

				// Boulder
				composable(route = navItemNames[1])
				{
					isFabVisible = true
					SsBoulderScreen(db, innerPadding, lazyListState, whereProblem.value)
				}

				// Sport
				composable(route = navItemNames[2])
				{
					isFabVisible = true
					SsSportScreen(db, innerPadding)
				}

				// Top rope
				composable(route = navItemNames[3])
				{
					isFabVisible = true
					SsTopRopeScreen(db, innerPadding)
				}

				// Trad
				composable(route = navItemNames[4])
				{
					isFabVisible = true
					SsTradScreen(db, innerPadding)
				}

			}
		})
}

data class WhereProblem(
	var isOutdoor : Boolean? = null,
	var isProject : Boolean? = null,
	var isFlash : Boolean? = null)
{

	fun isAll() : Boolean
	{
		return (isOutdoor == null) && (isProject == null) && (isFlash == null)
	}

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
	var navItemNames = dataStore.getAllNavigationNames()
	var navItemIcons = dataStore.getAllNavigationIcons()
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
 * Build the top bar.
 */
@Composable
fun buildTopBar(offsetHeight : MutableState<Float>,
	onMenuItemClicked: (menuItem: String) -> Unit)
{
	TopAppBar(
		//modifier = Modifier
		//	.offset { IntOffset(x = 0, y = offsetHeight.value.roundToInt()) },
		title = { Text(stringResource(R.string.app_name)) },
		actions = {
			IconButton(
				onClick = { onMenuItemClicked("Search") })
			{
				Icon(Icons.Default.Search, contentDescription = "Search")
			}

			buildTopBarDropdownMenu() { menuItem ->
				Log.i("SsMainScreen", "TODO: Launch the Settings component")
				onMenuItemClicked(menuItem)
			}
		})

}

/**
 * Build the dropdown menu for the top bar.
 */
@Composable
fun buildTopBarDropdownMenu(onMenuItemClicked : (menuItem : String) -> Unit)
{
	val menuItems = listOf<String>("Settings")
	var isMenuVisible by remember { mutableStateOf(false) }

	// TODO: Place = Location icon
	// TODO: Visibility = Eye icon
	IconButton(onClick = { isMenuVisible = !isMenuVisible })
	{
		Icon(Icons.Default.MoreVert, contentDescription = "Top bar menu")

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
		modifier = Modifier
			.onGloballyPositioned { coordinates ->

				// Measure the height of the FAB
				if (FabVisibilityConsultant.heightPx == 0)
				{
					FabVisibilityConsultant.heightPx = coordinates.size.height
				}
			},
		onClick = {
			Log.i("FloatingActionButton", "TODO: Add climb type, depending on which")
		})
	{
		Icon(Icons.Default.Add, contentDescription = "Add a problem")
	}
}

/**
 * Floating action button visibility consultant.
 *
 * When the screen is scrolled, this object is consulted to check if the FAB
 * should be visible or not.
 */
object FabVisibilityConsultant
{

	/**
	 * Height of the FAB, in pixels.
	 */
	var heightPx : Int = 0

	/**
	 * The amount the screen has scrolled by. The max value of this is the
	 * height of the FAB.
	 */
	var scrollTotal : Float = 0f

	/**
	 * Indicate to the FAB how much the screen has scrolled by in order to
	 * accurately determine if the FAB should be visible or not.
	 *
	 * This does not actually scroll the screen.
	 */
	fun scrollBy(delta : Float)
	{
		val newTotal = scrollTotal + delta

		scrollTotal = newTotal.coerceIn((-heightPx).toFloat(), 0f)
	}

	/**
	 * Check whether the FAB should be visible or not.
	 *
	 * @return True if the FAB should be visible, and False otherwise.
	 */
	fun shouldBeVisible() : Boolean
	{
		return abs(scrollTotal).toInt() != heightPx
	}
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
