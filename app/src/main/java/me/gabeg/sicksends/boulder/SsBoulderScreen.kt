package me.gabeg.sicksends.boulder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.db.boulder.SsBoulderProblem
import me.gabeg.sicksends.db.boulder.SsBoulderViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

@Composable
fun SsBoulderScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsBoulderViewModel = hiltViewModel())
{
	val problems :  List<SsBoulderProblem>

	if (queryState.hasNoFilter())
	{
		val allProblems: List<SsBoulderProblem> by viewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		val findProblems: List<SsBoulderProblem> by viewModel
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
