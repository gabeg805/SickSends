package me.gabeg.sicksends.sport

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.problem.SsProblemScreen

@Composable
fun SsSportScreen(db : SsProblemDatabase, innerPadding : PaddingValues)
{
	val sportDao = db.sportDao()
	val sportRepo = SsSportRepository(sportDao)
	val sportViewModel = SsSportViewModel(sportRepo)
	val allProblems :  List<SsSportProblem> = listOf()
	//val allProblems :  List<SsSportProblem> by sportViewModel.allProblems.observeAsState(listOf())

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
