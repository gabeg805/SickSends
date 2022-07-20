package me.gabeg.sicksends.addproblem.sport

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.sport.SsSportProblem
import me.gabeg.sicksends.shared.SsSharedSportDataStore
import me.gabeg.sicksends.shared.getAllSportGradesForGradingSystem
import javax.inject.Inject

/**
 * Add sport problem view model.
 */
@HiltViewModel
class SsAddSportProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsSportProblem,
	dataStore : SsSharedSportDataStore)
	: SsAddGenericProblemViewModel<SsSportProblem>(savedStateHandle, problem, dataStore)
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
		return getAllSportGradesForGradingSystem(gradingSystem)
	}

}
