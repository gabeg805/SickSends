package me.gabeg.sicksends.problem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Problem view model.
 */
abstract class SsGenericProblemViewModel<T : SsGenericProblem>(
	protected val repo : SsGenericProblemRepository<T>) : ViewModel()
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	val allProblems : LiveData<List<T>> = repo.allProblems

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : T) = viewModelScope.launch {
		Log.i(TAG, "Inserting problem into the repository!")
		repo.insert(problem)
	}

	companion object
	{
		const val TAG = "SsGenericProblemViewMod"
	}

}

//class SsProblemViewModelFactory(private val repo: SsGenericProblemRepository)
//	: ViewModelProvider.Factory
//{
//
//	override fun <T : ViewModel> create(modelClass: Class<T>): T
//	{
//
//		if (modelClass.isAssignableFrom(SsGenericProblemViewModel::class.java)) {
//			@Suppress("UNCHECKED_CAST")
//			return SsGenericProblemViewModel(repo) as T
//		}
//
//		throw IllegalArgumentException("Unknown ViewModel class")
//	}
//
//}
