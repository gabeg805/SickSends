package me.gabeg.sicksends.shared

import androidx.compose.runtime.Composable

@Composable
fun howDidItFeelScaleToString(feelScale : Int) : String
{
	return when (feelScale)
	{
		0 -> ""
		1 -> "Easy"
		2 -> "Easy"
		3 -> "Normal"
		4 -> "Hard"
		5 -> "Hard"
		else -> ""
	}
}
