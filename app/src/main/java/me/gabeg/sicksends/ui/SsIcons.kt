package me.gabeg.sicksends.problem.ui

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
	Icon(Icons.Default.Note,
		modifier = modifier,
		contentDescription = "Note")
}

/**
 * Number of attempts icon.
 */
@Composable
fun SsNumberOfAttemptsIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Lock,
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
 * Project icon.
 */
@Composable
fun SsProjectIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Construction,
		modifier = modifier,
		contentDescription = "Project")
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
