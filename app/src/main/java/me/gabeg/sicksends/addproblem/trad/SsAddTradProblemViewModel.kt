package me.gabeg.sicksends.addproblem.trad

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.trad.SsTradProblem
import me.gabeg.sicksends.shared.SsSharedTradDataStore
import me.gabeg.sicksends.shared.getAllTradGradesForGradingSystem
import javax.inject.Inject

/**
 * Add trad problem view model.
 */
@HiltViewModel
class SsAddTradProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTradProblem,
	dataStore : SsSharedTradDataStore)
	: SsAddGenericProblemViewModel<SsTradProblem>(savedStateHandle, problem, dataStore)
{

	/**
	 * Get the list of all grades for a type of grading system.
	 *
	 * @param gradingSystem A grading system.
	 *
	 * @return A list of all grades for a type of grading system.
	 */
	@Composable
	override fun getAllGrades(gradingSystem : String) : List<String>
	{
		return getAllTradGradesForGradingSystem(gradingSystem)
	}

}
