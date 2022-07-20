package me.gabeg.sicksends.addproblem.boulder

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemScreen
import me.gabeg.sicksends.db.boulder.SsBoulderProblem
import me.gabeg.sicksends.db.boulder.SsBoulderViewModel

/**
 * A screen to add a boulder problem.
 */
@Composable
fun SsAddBoulderProblemScreen(
	viewModel : SsAddBoulderProblemViewModel = hiltViewModel(),
	onDone : () -> Unit = {})
{
	val boulder : SsBoulderViewModel = hiltViewModel()

	SsAddGenericProblemScreen(
		viewModel = viewModel,
		onDone = { problem ->
			boulder.insert(problem as SsBoulderProblem)
			onDone()
		})
}
