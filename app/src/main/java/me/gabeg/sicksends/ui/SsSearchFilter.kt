package me.gabeg.sicksends.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

/**
 * State for a dropdown search filter.
 *
 * @param index Index to show first in the dropdown menu. A value of -1 means
 *              that nothing is shown.
 */
data class SsDropdownSearchFilterState(
	val index : Int = -1) : SsDropdownMenuBaseState(index)
{

	/**
	 * Convert the selected option in the list of search filter options to a
	 * value that can be used in a query.
	 *
	 * @return The selected option as a value that can be used in a query.
	 */
	fun toQuery() : Boolean?
	{
		val isEither = (selected == 0)
		val isSelected = (selected == 1)

		return if (isEither) null else isSelected
	}

}

/**
 * State to use search filters in a query.
 *
 * @param initialOutdoor The initial outdoor search filter state.
 * @param initialProject The initial project search filter state.
 * @param initialFlash The initial flash search filter state.
 */
data class SsSearchFilterQueryState(
	val initialOutdoor : Boolean? =  null,
	val initialProject : Boolean? =  null,
	val initialFlash : Boolean? =  null)
{

	/**
	 * The current state of the outdoor search filter.
	 */
	var outdoor : Boolean? by mutableStateOf(initialOutdoor)

	/**
	 * The current state of the project search filter.
	 */
	var project : Boolean? by mutableStateOf(initialProject)

	/**
	 * The current state of the flash search filter.
	 */
	var flash : Boolean? by mutableStateOf(initialFlash)

	/**
	 * Check if any search filters are set.
	 */
	fun hasNoFilter() : Boolean
	{
		return (outdoor == null) && (project == null) && (flash == null)
	}

}

/**
 * Dropdown search filter.
 */
@Composable
fun SsDropdownSearchFilter(state : SsDropdownSearchFilterState,
	allOptions : List<String>, description : String,
	dropdownWidth : Dp, iconContent : @Composable () -> Unit = {})
{

	// Each search filter will span a row.
	Row(
		modifier = Modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically)
	{

		// Optional icon to display at the start of the search filter.
		iconContent()

		// Description of the search filter.
		Text(description,
			modifier = Modifier
				.weight(1f),
			fontWeight = FontWeight.SemiBold)

		// Dropdown menu options for the search filter
		SsDropdownMenu(
			options = allOptions,
			state = state,
			modifier = Modifier.width(dropdownWidth))

	}
}