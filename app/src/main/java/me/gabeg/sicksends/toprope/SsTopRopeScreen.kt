package me.gabeg.sicksends.toprope

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.db.toprope.SsTopRopeViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

@Composable
fun SsTopRopeScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsTopRopeViewModel = hiltViewModel())
{
	val allProblems :  List<SsTopRopeProblem> = listOf()

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
