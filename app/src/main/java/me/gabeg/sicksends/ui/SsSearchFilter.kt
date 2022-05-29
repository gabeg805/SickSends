package me.gabeg.sicksends.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Park
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * State for a dropdown search filter.
 *
 * @param options List of options that appear in the dropdown menu.
 */
data class SsDropdownSearchFilterState(
	val options : List<String>)
{

	/**
	 * The selected dropdown menu item.
	 */
	var selected : String by mutableStateOf("")

	/**
	 * Whether the dropdown menu is expanded or not.
	 */
	var isExpanded : Boolean by mutableStateOf(false)

	/**
	 */
	init
	{
		selected = options[0]
	}

	/**
	 * Collapse the dropdown menu.
	 */
	fun collapse()
	{
		isExpanded = false
	}

	/**
	 * Expand the dropdown menu.
	 */
	fun expand()
	{
		isExpanded = true
	}

	/**
	 * Select an item in the dropdown menu.
	 */
	fun select(name : String)
	{
		selected = name
	}

	/**
	 * Toggle the dropdown menu and expand or collapse it, depending on its
	 * current state.
	 */
	fun toggle()
	{
		isExpanded = !isExpanded
	}

	/**
	 * Convert the selected option in the list of search filter options to a
	 * value that can be used in a query.
	 *
	 * @return The selected option as a value that can be used in a query.
	 */
	fun toQuery() : Boolean?
	{
		val isEither = (selected == options[0])
		val isSelected = (selected == options[1])

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
@OptIn(ExperimentalMaterialApi::class)
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

		// Dropdown menu for the search filter.
		ExposedDropdownMenuBox(
			//modifier = Modifier.weight(2f),
			expanded = state.isExpanded,
			onExpandedChange = {
				state.toggle()
			})
		{

			// Text containing the item in the dropdown menu.
			TextField(
				modifier = Modifier.width(dropdownWidth),
				value = state.selected,
				onValueChange = { },
				trailingIcon = {
					ExposedDropdownMenuDefaults.TrailingIcon(
						expanded = state.isExpanded
					)
				},
				readOnly = true,
				colors = ExposedDropdownMenuDefaults.textFieldColors())


			// The dropdown menu itself that is displayed when a user clicks on
			// the menu box.
			ExposedDropdownMenu(
				modifier = Modifier
					.exposedDropdownSize(true),
				expanded = state.isExpanded,
				onDismissRequest = {
					state.collapse()
				})
			{

				// Iterate over each of the options
				allOptions.forEach { option ->
					DropdownMenuItem(
						onClick = {
							state.select(option)
							state.collapse()
						})
					{
						Text(option)
					}
				}
			}

		}

	}
}