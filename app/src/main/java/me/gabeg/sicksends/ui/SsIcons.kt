package me.gabeg.sicksends.problem.ui

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Add icon.
 */
@Composable
fun SsAddIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Add,
		modifier = modifier,
		contentDescription = "Add")
}

/**
 * Back icon.
 */
@Composable
fun SsBackIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.ArrowBack,
		modifier = modifier,
		contentDescription = "Back")
}

/**
 * Flash icon.
 */
@Composable
fun SsFlashIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Bolt,
		modifier = modifier,
		contentDescription = "Flash")
}

/**
 * Grade icon.
 */
@Composable
fun SsGradeIcon(modifier : Modifier = Modifier)
{
	//Icon(Icons.Default.Grade,
	Icon(Icons.Default.HotelClass,
		modifier = modifier,
		contentDescription = "Grade")
}

/**
 * Location icon.
 */
@Composable
fun SsLocationIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Place,
		modifier = modifier,
		contentDescription = "Location")
}

/**
 * Name icon.
 */
@Composable
fun SsNameIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Edit,
		modifier = modifier,
		contentDescription = "Name")
}

/**
 * No icon.
 */
@Composable
fun SsNoIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Close,
		modifier = modifier,
		contentDescription = "No")
}

/**
 * Note icon.
 */
@Composable
fun SsNoteIcon(modifier : Modifier = Modifier)
{
	//Icon(Icons.Default.Chat,
	Icon(Icons.Default.LibraryBooks,
		modifier = modifier,
		contentDescription = "Note")
}

/**
 * Number of attempts icon.
 */
@Composable
fun SsNumberOfAttemptsIcon(modifier : Modifier = Modifier)
{
	//Maps section in icons
	//Icon(Icons.Default.DoNotStep,
	//Icon(Icons.Default.RunCircle,
	//Icon(Icons.Default.TransferWithinAStation,
	//Icon(Icons.Default.DirectionsWalk,
	Icon(Icons.Default.DirectionsRun,
		modifier = modifier,
		contentDescription = "Number of attempts")
}

/**
 * Outdoor icon.
 */
@Composable
fun SsOutdoorIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Park,
		modifier = modifier,
		contentDescription = "Outdoor")
}

/**
 * Overflow icon.
 */
@Composable
fun SsOverflowIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.MoreVert,
		modifier = modifier,
		contentDescription = "Overflow options")
}

/**
 * Project icon.
 */
@Composable
fun SsProjectIcon(modifier : Modifier = Modifier)
{
	//Icon(Icons.Default.Construction,
	Icon(Icons.Default.Hardware,
		modifier = modifier,
		contentDescription = "Project")
}

/**
 * Search icon.
 */
@Composable
fun SsSearchIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Search,
		modifier = modifier,
		contentDescription = "Search")
}

/**
 * Yes icon.
 */
@Composable
fun SsYesIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Check,
		modifier = modifier,
		contentDescription = "Yes")
}
