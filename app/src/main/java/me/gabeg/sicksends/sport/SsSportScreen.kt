package me.gabeg.sicksends.sport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.db.sport.SsSportProblem
import me.gabeg.sicksends.db.sport.SsSportViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

const val SPORT_SCREEN_ROUTE = "Sport"

/**
 * Sport screen.
 */
@Composable
fun SsSportScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsSportViewModel = hiltViewModel())
{
	val problems :  List<SsSportProblem>

	if (queryState.hasNoFilter())
	{
		val allProblems: List<SsSportProblem> by viewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		val findProblems: List<SsSportProblem> by viewModel
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
