package me.gabeg.sicksends.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight

/**
 *
 */
@Composable
fun SsAlertDialog(
	title : String,
	confirmText : String = "OK",
	dismissText : String = "CANCEL",
	onConfirmClicked : () -> Unit = {},
	onDismissClicked : () -> Unit = {},
	show : Boolean = false,
	state : SsAlertDialogState = rememberSsAlertDialogState(show),
	content : @Composable () -> Unit = {})
{

	// Show the dialog
	AlertDialog(

		// Title
		title = {
			Text(title,
				fontWeight = FontWeight.Bold)
		},

		// Body
		text = {

			Column()
			{

				// Space between the title and the dropdown menu
				Text("")

				// Content
				content()

			}
		},

		onDismissRequest = {
			state.hide()
		},

		confirmButton = {

			// Show a confirm button if there is text to show
			if (!confirmText.isNullOrEmpty())
			{

				TextButton(
					onClick = {
						state.hide()
						onConfirmClicked()
					})
				{
					Text(confirmText)
				}

			}
		},

		dismissButton = {

			// Show a dismiss button if there is text to show
			if (!dismissText.isNullOrEmpty())
			{

				TextButton(
					onClick = {
						state.hide()
						onDismissClicked()
					})
				{
					Text(dismissText)
				}

			}

		})

}

/**
 * State for an alert dialog.
 */
data class SsAlertDialogState(
	private val initial : Boolean = false)
{

	/**
	 * Whether or not the dialog is visible.
	 */
	var isVisible : Boolean by mutableStateOf(false)

	/**
	 */
	init
	{
		isVisible = initial
	}

	/**
	 * Hide the dialog.
	 */
	fun hide()
	{
		isVisible = false
	}

	/**
	 * Show the dialog.
	 */
	fun show()
	{
		isVisible = true
	}

}

/**
 * Remember the alert dialog state.
 */
@Composable
fun rememberSsAlertDialogState(initial : Boolean = false) : SsAlertDialogState
{
	return remember { SsAlertDialogState(initial) }
}
