package me.gabeg.sicksends.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import me.gabeg.sicksends.SsLongClickOutlinedButton
import me.gabeg.sicksends.problem.ui.SsNoIcon
import me.gabeg.sicksends.problem.ui.SsYesIcon

/**
 * An icon button with text underneath it.
 */
@Composable
fun SsIconTextButton(
	text : String,
	modifier : Modifier = Modifier,
	innerModifier : Modifier = Modifier,
	border : BorderStroke = ButtonDefaults.outlinedBorder,
	colors : ButtonColors = ButtonDefaults.outlinedButtonColors(),
	fontSize : TextUnit = MaterialTheme.typography.subtitle2.fontSize,
	fontWeight: FontWeight = FontWeight.Normal,
	onClick : (String) -> Unit = {},
	onLongClick : (String) -> Unit = {},
	icon : @Composable () -> Unit = {})
{

	// Container for both button and text
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally)
	{

		// Icon button
		SsLongClickOutlinedButton(
			modifier = innerModifier,
			onClick = { onClick(text) },
			onLongClick = { onLongClick(text) },
			border = border,
			colors = colors)
		{
			icon()
		}

		// Text underneath the button
		Text(text,
			fontSize = fontSize,
			fontWeight = fontWeight,
			textAlign = TextAlign.Center)

	}

}

///**
// * An icon button with text underneath it.
// */
//@Composable
//fun SsIconTextButton(
//	text : String,
//	border : BorderStroke = ButtonDefaults.outlinedBorder,
//	colors : ButtonColors = ButtonDefaults.outlinedButtonColors(),
//	fontSize : TextUnit = MaterialTheme.typography.subtitle2.fontSize,
//	fontWeight: FontWeight = FontWeight.Normal,
//	onClick : (String) -> Unit = {},
//	icon : @Composable () -> Unit = {})
//{
//
//	// Container for both button and text
//	Column(
//		horizontalAlignment = Alignment.CenterHorizontally)
//	{
//
//		// Icon button
//		OutlinedButton(
//			onClick = { onClick(text) },
//			border = border,
//			colors = colors)
//		{
//			icon()
//		}
//
//		// Text underneath the button
//		Text(text,
//			fontSize = fontSize,
//			fontWeight = fontWeight)
//
//	}
//
//}

/**
 * An icon and text button for "No".
 */
@Composable
fun SsNoIconTextButton(
	border : BorderStroke = ButtonDefaults.outlinedBorder,
	colors : ButtonColors = ButtonDefaults.outlinedButtonColors(),
	fontSize : TextUnit = MaterialTheme.typography.subtitle2.fontSize,
	fontWeight: FontWeight = FontWeight.Normal,
	onClick : (String) -> Unit = {})
{

	SsIconTextButton(
		text = "No",
		border = border,
		colors = colors,
		fontSize = fontSize,
		fontWeight = fontWeight,
		onClick = onClick)
	{
		SsNoIcon()
	}

}

/**
 * An icon and text button for "Yes".
 */
@Composable
fun SsYesIconTextButton(
	border : BorderStroke = ButtonDefaults.outlinedBorder,
	colors : ButtonColors = ButtonDefaults.outlinedButtonColors(),
	fontSize : TextUnit = MaterialTheme.typography.subtitle2.fontSize,
	fontWeight: FontWeight = FontWeight.Normal,
	onClick : (String) -> Unit = {})
{

	SsIconTextButton(
		text = "Yes",
		border = border,
		colors = colors,
		fontSize = fontSize,
		fontWeight = fontWeight,
		onClick = onClick)
	{
		SsYesIcon()
	}

}
