package me.gabeg.sicksends.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.gabeg.sicksends.SsLongClickOutlinedButton

/**
 * Button toggle group.
 */
@Composable
fun SsButtonToggleGroup(
	items : List<String>,
	modifier : Modifier = Modifier,
	rowModifier : Modifier = Modifier,
	singleSelection : Boolean = false,
	toggleable : Boolean = true,
	initialSelect : String = "",
	select : List<String> = listOf(),
	numPerRow : Int = items.size,
	checkCriteria : @Composable ((String) -> Boolean)? = null,
	checkedColor : ButtonColors = ButtonDefaults.buttonColors(
		backgroundColor = Color.Magenta,
		contentColor = Color.White),
	uncheckedColor : ButtonColors = ButtonDefaults.buttonColors(
		backgroundColor = Color.White,
		contentColor = Color.Black),
	alignment : Alignment.Horizontal = Alignment.CenterHorizontally,
	spacing : Dp = 0.dp,
	startButtonContent : @Composable RowScope.(String) -> Unit = {},
	textButtonContent : @Composable RowScope.(String) -> Unit = {
		Text(it,
			overflow = TextOverflow.Ellipsis,
			softWrap = false)
	},
	endButtonContent : @Composable RowScope.(String) -> Unit = {},
	onClick : (Int, String, Boolean) -> Unit = { _,_,_ -> },
	onLongClick : (String) -> Unit = {},
)
{

	// The selected button. This is only used for single selection
	var selectedButton by remember { mutableStateOf(initialSelect) }

	// Container for all the buttons
	Column(
		modifier = modifier,
		horizontalAlignment = alignment)
	{

		// Iterate over each item in chunks, based on how many buttons should
		// appear per row
		items.chunked(numPerRow).forEachIndexed { i, chunkedItems ->

			// Row for the buttons
			Row(modifier = rowModifier)
			{

				// Iterate over each button that will be in the row
				for ((j,name) in chunkedItems.withIndex())
				{

					// Index of the button
					val index = (numPerRow * i) + j

					// Whether or not the grading system button is selected
					var isChecked : Boolean

					// Single selection. The name must match the selected button
					// and if the check criteria is set, it must satisfy that as
					// well.
					if (singleSelection)
					{
						isChecked = (name == selectedButton)
						isChecked = checkCriteria?.let { isChecked && it(name) }
							?: isChecked
					}
					// Multi selection. If the check criteria is set, it must
					// satisfy that, otherwise, the name will have to match an
					// item in the select list.
					else
					{
						isChecked = checkCriteria?.let { it(name) }
							?: (name in select)
					}

					// Create a button
					SsLongClickOutlinedButton(
						modifier = Modifier
							.fillMaxWidth(1f / (numPerRow - j)),
						colors = if (isChecked) checkedColor else uncheckedColor,
						onClick = {

							// Single selection button
							if (singleSelection)
							{
								// Button is toggleable. If it is already
								// checked, then the subsequent state would be
								// unchecked, thus an empty selected button
								// name. Otherwise, set the name of the selected
								// button
								if (toggleable)
								{
									selectedButton = if (isChecked) "" else name
								}
								else
								{
									// Set the name of the selected button twice
									// so that the check criteria above is
									// reevaluated
									selectedButton = ""
									selectedButton = name

									// Button is already checked, but is not
									// toggleable. Call the onClick() method
									// as if it was just clicked to true
									if (isChecked)
									{
										onClick(index, name, isChecked)
										return@SsLongClickOutlinedButton
									}
								}
							}

							// Call the onClick() method
							onClick(index, name, !isChecked)

						},
						onLongClick = {
							onLongClick(name)
						})
					{

						// Start content of the button
						startButtonContent(name)

						// Text of the button
						textButtonContent(name)

						// End content of the button
						endButtonContent(name)

					}

				}
			}

		}

	}

}

