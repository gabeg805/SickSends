package me.gabeg.sicksends.problem.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
	Icon(Icons.Default.CloudDone,
		modifier = modifier,
		contentDescription = "Grade")
}

/**
 * Name icon.
 */
@Composable
fun SsNameIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.Face,
		modifier = modifier,
		contentDescription = "Name")
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
