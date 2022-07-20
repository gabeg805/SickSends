package me.gabeg.sicksends.toprope

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.db.toprope.SsTopRopeViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

const val TOP_ROPE_SCREEN_ROUTE = "Top Rope"

/**
 * Top rope screen.
 */
@Composable
fun SsTopRopeScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsTopRopeViewModel = hiltViewModel())
{
	val problems :  List<SsTopRopeProblem>

	if (queryState.hasNoFilter())
	{
		val allProblems: List<SsTopRopeProblem> by viewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		val findProblems: List<SsTopRopeProblem> by viewModel
			.findProblems(
				queryState.outdoor,
				queryState.project,
				queryState.flash)
			.observeAsState(listOf())
		problems = findProblems
	}

	Column()
	{
		SsProblemScreen(problems, innerPadding = innerPadding, lazyListState)
	}
}
