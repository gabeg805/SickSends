package me.gabeg.sicksends.addproblem.sport

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemScreen
import me.gabeg.sicksends.db.sport.SsSportProblem
import me.gabeg.sicksends.db.sport.SsSportViewModel

/**
 * A screen to add a sport problem.
 */
@Composable
fun SsAddSportProblemScreen(
	viewModel : SsAddSportProblemViewModel = hiltViewModel(),
	onDone : () -> Unit = {})
{
	val sport : SsSportViewModel = hiltViewModel()

	SsAddGenericProblemScreen(
		viewModel = viewModel,
		onDone = { problem ->
			sport.insert(problem as SsSportProblem)
			onDone()
		})
}
