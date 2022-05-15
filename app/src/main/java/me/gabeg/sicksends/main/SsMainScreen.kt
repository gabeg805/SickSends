package me.gabeg.sicksends.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.gabeg.sicksends.R
import me.gabeg.sicksends.shared.SsSharedDataStore

const val MAIN_SCREEN_ROUTE = "main_screen_route"

// TODO: Look into the back button at the top bar and also subtitles in the top
//  bar
// TODO: Hide the floating action button when in the Home screen
@Composable
fun SsMainScreen(navController: NavController)
{
	val dataStore = SsSharedDataStore(LocalContext.current)
	val scaffoldState = rememberScaffoldState()

	Scaffold(
		scaffoldState = scaffoldState,
		topBar = {
			TopAppBar(
				title = { Text(stringResource(id = R.string.app_name)) },
				actions = {
					buildDropdownMenu() { menuItem ->
						Log.i("SsMainScreen", "TODO: Launch the Settings component")
					}
				})
		},
		bottomBar = {
			buildBottomNavigationBar(dataStore = dataStore) { index, name ->
				Log.i("SsMainScreen", "TODO: Launch the climb type")
			}
		},
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = { buildFloatingActionButton() },
		content = { innerPadding ->
			val colors = listOf<Color>(Color.Cyan, Color.Red, Color.Green, Color.Magenta, Color.Yellow)
			LazyColumn(contentPadding = innerPadding) {
				items(count = 10) {
					Box(
						Modifier
							.fillMaxWidth()
							.height(100.dp)
							.background(colors[it % colors.size])
					)
				}
			}
		})
}

/**
 * Build the bottom navigation bar.
 */
@Composable
fun buildBottomNavigationBar(dataStore : SsSharedDataStore,
	onNavigationItemClicked : (index : Int, name : String) -> Unit)
{
	var navItemNames = remember { mutableListOf<String>("Home") }
	var navItemIcons = remember { mutableListOf<Int>(R.mipmap.home) }
	var selectedIndex by remember { mutableStateOf(0) }

	// Get all the names of the type of climbs the user will do
	runBlocking {
		navItemNames.addAll(dataStore.getAllClimbNamesWillClimb())
		navItemIcons.addAll(dataStore.getAllClimbIconsWillClimb())
	}

	// Create the bottom navigation bar
	BottomNavigation {

		// Iterate over Home + each type of climb the user will do
		navItemNames.zip(navItemIcons).forEachIndexed { index, (name, icon) ->

			// Painter of the icon
			val painter = painterResource(id = icon)

			// Create a navigation item
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
				unselectedContentColor = Color.White.copy(alpha = 0.3f)
			)
		}
	}
}

/**
 * Build the dropdown menu.
 */
@Composable
fun buildDropdownMenu(onMenuItemClicked : (menuItem : String) -> Unit)
{
	val menuItems = listOf<String>("Settings")
	var isMenuVisible by remember { mutableStateOf(false) }

	IconButton(onClick = { isMenuVisible = !isMenuVisible })
	{
		Icon(Icons.Default.MoreVert, contentDescription = "Localized description")

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
		onClick = {
			Log.i("FloatingActionButton", "TODO: Add climb type, depending on which")
		})
	{
		Icon(Icons.Default.Add, contentDescription = "Localized description")
	}
}
