package me.gabeg.sicksends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * The application's starting activity.
 */
class SsSickSendsActivity : ComponentActivity()
{

	/**
	 */
	@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)

		// Show the app
		setContent()
		{
			SsSickSendsApp()
		}
	}

}
