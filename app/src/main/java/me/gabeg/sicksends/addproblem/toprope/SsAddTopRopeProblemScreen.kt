package me.gabeg.sicksends.addproblem.toprope

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemScreen
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.db.toprope.SsTopRopeViewModel

/**
 * A screen to add a boulder problem.
 *
 * TODO: location name, location lat/lon, image
 */
@Composable
fun SsAddTopRopeProblemScreen(
	viewModel : SsAddTopRopeProblemViewModel = hiltViewModel(),
	onDone : () -> Unit = {})
{
	val topRope : SsTopRopeViewModel = hiltViewModel()

	SsAddGenericProblemScreen(
		viewModel = viewModel,
		onDone = { problem ->
			topRope.insert(problem as SsTopRopeProblem)
			onDone()
		})
}
