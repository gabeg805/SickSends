package me.gabeg.sicksends.ui

import android.util.Log
import android.webkit.WebSettings
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit

@Composable
fun ToggleButton(text : String,
	modifier : Modifier = Modifier,
	shape : Shape = RoundedCornerShape(50),
	state : Boolean = false,
	fontSize : TextUnit = MaterialTheme.typography.button.fontSize,
	onClicked : (String, Boolean) -> Unit = { _,_ -> })
{

	val unselectedColor = ButtonDefaults.buttonColors(
		backgroundColor = Color(0xFFE5E5E5),
		contentColor = Color.Gray)

	val selectedColor = ButtonDefaults.buttonColors(
		backgroundColor = MaterialTheme.colors.primary,
		contentColor = MaterialTheme.colors.onPrimary)

	Button(
		modifier = modifier,
		shape = shape,
		colors = if (state) selectedColor else unselectedColor,
		elevation = null,
		onClick = { onClicked(text, !state) })
	{
		Text(text,
			fontSize = fontSize)
	}
}
