package me.gabeg.sicksends.sport

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
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
	val allProblems :  List<SsSportProblem> = listOf()

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
