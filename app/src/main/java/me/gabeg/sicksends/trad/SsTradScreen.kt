package me.gabeg.sicksends.trad

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.gabeg.sicksends.problem.SsProblemScreen
import me.gabeg.sicksends.ui.SsSearchFilterQueryState

@Composable
fun SsTradScreen(
	queryState: SsSearchFilterQueryState,
	lazyListState : LazyListState,
	innerPadding : PaddingValues,
	viewModel: SsTradViewModel = hiltViewModel())
{
	val allProblems :  List<SsTradProblem> = listOf()

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
