package me.gabeg.sicksends.boulder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

@Composable
fun SsBoulderScreen(db : SsProblemDatabase, innerPadding : PaddingValues,
	lazyListState : LazyListState, queryState: SsSearchFilterQueryState)
{
	val boulderDao = db.boulderDao()
	val boulderRepo = SsBoulderRepository(boulderDao)
	val boulderViewModel = SsBoulderViewModel(boulderRepo)
	val problems :  List<SsBoulderProblem>

	if (queryState.hasNoFilter())
	{
		val allProblems: List<SsBoulderProblem> by boulderViewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		val findProblems: List<SsBoulderProblem> by boulderViewModel
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
