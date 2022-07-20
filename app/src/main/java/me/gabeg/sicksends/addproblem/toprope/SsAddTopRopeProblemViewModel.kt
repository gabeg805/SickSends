package me.gabeg.sicksends.addproblem.toprope

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.shared.SsSharedTopRopeDataStore
import me.gabeg.sicksends.shared.getAllTopRopeGradesForGradingSystem
import javax.inject.Inject

/**
 * Add top rope problem view model.
 */
@HiltViewModel
class SsAddTopRopeProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTopRopeProblem,
	dataStore : SsSharedTopRopeDataStore)
	: SsAddGenericProblemViewModel<SsTopRopeProblem>(savedStateHandle, problem, dataStore)
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
		return getAllTopRopeGradesForGradingSystem(gradingSystem)
	}

}
