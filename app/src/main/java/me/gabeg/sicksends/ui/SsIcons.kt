package me.gabeg.sicksends.problem.ui

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import me.gabeg.sicksends.R

/**
 * Next icon.
 */
@Composable
fun SsNextIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_right_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Next")
}

/**
 * Previous icon.
 */
@Composable
fun SsPreviousIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Previous")
}

/**
 * icon.
 */
@Composable
fun SsYoIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_double_arrow_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Next or skip")
}

/**
 * Thought bubble icon.
 */
@Composable
fun SsThoughtBubbleIcon(modifier : Modifier = Modifier)
{
	//val painter = painterResource(R.drawable.ic_thought_bubble)
	//val painter = painterResource(R.drawable.ic_filled_thought_bubble)
	//val painter = painterResource(R.drawable.ic_better_filled_thought_bubble)
	val painter = painterResource(R.drawable.ic_baseline_psychology_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Thought bubble")
}










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
 * Boulder icon.
 */
@Composable
fun SsBoulderIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.mipmap.boulder)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Boulder")
}

/**
 * Expand icon.
 */
@Composable
fun SsExpandIcon(modifier : Modifier = Modifier)
{
	//Icon(Icons.Default.KeyboardArrowDown,
	Icon(Icons.Default.ArrowDropDown,
		modifier = modifier,
		contentDescription = "Expand")
}

/**
 * Flash icon.
 */
@Composable
fun SsFlashIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_bolt_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Flash")
}

/**
 * Grade icon.
 */
@Composable
fun SsGradeIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_hotel_class_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Grade")
}

/**
 * Grading system icon.
 */
@Composable
fun SsGradingSystemIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_public_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Grading system")
}

/**
 * Home icon.
 */
@Composable
fun SsHomeIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.mipmap.home)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Home")
}

/**
 * How did it feel icon.
 */
@Composable
fun SsHowDidItFeelIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.ThumbUp,
		modifier = modifier,
		contentDescription = "How did it feel")
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
 * Location name icon.
 */
@Composable
fun SsLocationNameIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_local_offer_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Location name")
}

/**
 * Media icon.
 */
@Composable
fun SsMediaIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_photo_camera_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Image or video")
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
	val painter = painterResource(R.drawable.ic_baseline_library_books_24)

	Icon(painter,
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
	//Icon(Icons.Default.DirectionsRun,

	val painter = painterResource(R.drawable.ic_baseline_directions_run_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Number of attempts")
}

/**
 * Outdoor icon.
 */
@Composable
fun SsOutdoorIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.baseline_forest_24)

	Icon(painter,
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
 * Perceived grade icon.
 */
@Composable
fun SsPerceivedGradeIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_star_half_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Perceived grade")
}

/**
 * Project icon.
 */
@Composable
fun SsProjectIcon(modifier : Modifier = Modifier)
{
	//Icon(Icons.Default.Construction,
	//Icon(Icons.Default.Hardware,

	val painter = painterResource(R.drawable.ic_baseline_hardware_24)

	Icon(painter,
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
 * Skip icon.
 */
@Composable
fun SsSkipIcon(modifier : Modifier = Modifier)
{
	Icon(Icons.Default.KeyboardArrowRight,
		modifier = modifier,
		contentDescription = "Skip")
}

/**
 * Sport icon.
 */
@Composable
fun SsSportIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.mipmap.sport)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Sport")
}

/**
 * Subtract icon.
 */
@Composable
fun SsSubtractIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_remove_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Subtract")
}

/**
 * Technique icon.
 */
@Composable
fun SsTechniqueIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_lightbulb_24)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Search")
}

/**
 * Top rope icon.
 */
@Composable
fun SsTopRopeIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.mipmap.top_rope)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Top rope")
}

/**
 * Trad icon.
 */
@Composable
fun SsTradIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.mipmap.trad)

	Icon(painter,
		modifier = modifier,
		contentDescription = "Trad")
}

/**
 * Wall icon.
 */
@Composable
fun SsWallIcon(modifier : Modifier = Modifier)
{
	val painter = painterResource(R.drawable.ic_baseline_map_24)

	Icon(painter,
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
