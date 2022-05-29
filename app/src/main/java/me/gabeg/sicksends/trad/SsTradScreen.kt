package me.gabeg.sicksends.trad

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.problem.SsProblemScreen

@Composable
fun SsTradScreen(db : SsProblemDatabase, innerPadding : PaddingValues)
{
	val tradDao = db.tradDao()
	val tradRepo = SsTradRepository(tradDao)
	val tradViewModel = SsTradViewModel(tradRepo)
	val allProblems :  List<SsTradProblem> = listOf()
	//val allProblems :  List<SsTradProblem> by tradViewModel.allProblems.observeAsState(listOf())

	SsProblemScreen(allProblems, innerPadding = innerPadding)
}
