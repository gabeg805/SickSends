package me.gabeg.sicksends.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SsDropdownMenu(
	options : List<String>,
	state : SsDropdownMenuBaseState,
	modifier : Modifier = Modifier,
	onMenuItemClickedListener : (Int, String) -> Unit = {_,_ -> })
{

	// Dropdown menu for the search filter.
	ExposedDropdownMenuBox(
		expanded = state.isExpanded,
		onExpandedChange = {
			state.toggle()
		})
	{

		// Text containing the item in the dropdown menu.
		TextField(
			modifier = modifier,
			value = if (state.selected >= 0) options[state.selected] else "",
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
			options.forEachIndexed { index, name ->
				DropdownMenuItem(
					onClick = {
						//state.select(name)
						state.select(index)
						state.collapse()
						onMenuItemClickedListener(index, name)
					})
				{
					Text(name)
				}
			}
		}

	}
}

/**
 * Base state for a dropdown menu.
 *
 * @param index Index to show first in the dropdown menu. A value of -1 means
 *              that nothing is shown.
 */
abstract class SsDropdownMenuBaseState(
	private val index : Int = -1)
{

	/**
	 * The selected dropdown menu item.
	 */
	var selected : Int by mutableStateOf<Int>(-1)

	/**
	 * Whether the dropdown menu is expanded or not.
	 */
	var isExpanded : Boolean by mutableStateOf(false)

	/**
	 */
	init
	{
		selected = index
	}

	/**
	 * Clear the currently selected item in the dropdown menu.
	 */
	fun clearSelected()
	{
		selected = -1
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
	fun select(index : Int)
	{
		selected = index
	}

	/**
	 * Toggle the dropdown menu and expand or collapse it, depending on its
	 * current state.
	 */
	fun toggle()
	{
		isExpanded = !isExpanded
	}

}

/**
 * State for a dropdown menu.
 *
 * @param index Index to show first in the dropdown menu. A value of -1 means
 *              that nothing is shown.
 */
data class SsDropdownMenuState(
	val index : Int = -1) : SsDropdownMenuBaseState(index)
