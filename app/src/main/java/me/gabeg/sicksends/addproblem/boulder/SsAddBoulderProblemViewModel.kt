package me.gabeg.sicksends.addproblem.boulder

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.boulder.SsBoulderProblem
import me.gabeg.sicksends.shared.SsSharedBoulderDataStore
import me.gabeg.sicksends.shared.getAllBoulderGradesForGradingSystem
import javax.inject.Inject

/**
 * Add boulder problem view model.
 */
@HiltViewModel
class SsAddBoulderProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsBoulderProblem,
	dataStore : SsSharedBoulderDataStore)
	: SsAddGenericProblemViewModel<SsBoulderProblem>(savedStateHandle, problem, dataStore)
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
		return getAllBoulderGradesForGradingSystem(gradingSystem)
	}

}
