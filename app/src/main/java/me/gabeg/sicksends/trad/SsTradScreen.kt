package me.gabeg.sicksends.trad

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.db.trad.SsTradProblem
import me.gabeg.sicksends.db.trad.SsTradViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

const val TRAD_SCREEN_ROUTE = "Trad"

/**
 * Trad screen.
 */
@Composable
fun SsTradScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsTradViewModel = hiltViewModel())
{
	val problems :  List<SsTradProblem>

	if (queryState.hasNoFilter())
	{
		val allProblems: List<SsTradProblem> by viewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		val findProblems: List<SsTradProblem> by viewModel
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
