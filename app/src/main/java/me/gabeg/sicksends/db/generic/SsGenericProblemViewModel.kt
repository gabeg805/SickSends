package me.gabeg.sicksends.db.generic

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Problem view model.
 */
abstract class SsGenericProblemViewModel<T : SsGenericProblem>(
	private val savedStateHandle: SavedStateHandle,
	private val repo : SsGenericProblemRepository<T>) : ViewModel()
{

	/**
	 * All climbing problems in a table.
	 */
	val allProblems : LiveData<List<T>> = repo.allProblems

	/**
	 * Find problems in a table.
	 *
	 * @return The live data list of problems found.
	 */
	fun findProblems(isOutdoor : Boolean? = null, isProject : Boolean? = null,
		isFlash : Boolean? = null) : LiveData<List<T>>
	{
		return repo.findProblems(isOutdoor, isProject, isFlash)
	}

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : T) = viewModelScope.launch {
		repo.insert(problem)
	}

}
