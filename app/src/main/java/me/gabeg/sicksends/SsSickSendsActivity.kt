package me.gabeg.sicksends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

/**
 * The application's starting activity.
 */
@AndroidEntryPoint
class SsSickSendsActivity : ComponentActivity()
{

	/**
	 */
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)

		setContent()
		{
			// Show the app
			SsSickSendsApp()
		}
	}

}
