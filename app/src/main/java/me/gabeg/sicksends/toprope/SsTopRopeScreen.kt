package me.gabeg.sicksends.toprope

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.problem.SsProblemScreen

@Composable
fun SsTopRopeScreen(db : SsProblemDatabase, innerPadding : PaddingValues)
{
	val topRopeDao = db.topRopeDao()
	val topRopeRepo = SsTopRopeRepository(topRopeDao)
	val topRopeViewModel = SsTopRopeViewModel(topRopeRepo)
	val allProblems :  List<SsTopRopeProblem> = listOf()
	//val allProblems :  List<SsTopRopeProblem> by topRopeViewModel.allProblems.observeAsState(listOf())

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
