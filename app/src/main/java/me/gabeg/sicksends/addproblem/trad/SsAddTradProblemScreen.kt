package me.gabeg.sicksends.addproblem.trad

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemScreen
import me.gabeg.sicksends.db.trad.SsTradProblem
import me.gabeg.sicksends.db.trad.SsTradViewModel

/**
 * A screen to add a boulder problem.
 *
 * TODO: location name, location lat/lon, image
 */
@Composable
fun SsAddTradProblemScreen(
	viewModel : SsAddTradProblemViewModel = hiltViewModel(),
	onDone : () -> Unit = {})
{
	val trad : SsTradViewModel = hiltViewModel()

	SsAddGenericProblemScreen(
		viewModel = viewModel,
		onDone = { problem ->
			trad.insert(problem as SsTradProblem)
			onDone()
		})
}
