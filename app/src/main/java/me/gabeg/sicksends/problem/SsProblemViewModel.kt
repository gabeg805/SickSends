package me.gabeg.sicksends.problem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Problem view model.
 */
abstract class SsProblemViewModel<S : SsProblem>(
	private val repo : SsProblemRepository<S>) : ViewModel()
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	//val allProblems: LiveData<List<S>> = repo.allProblems.asLiveData()
	val allProblems = repo.allProblems.asLiveData()

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : S) = viewModelScope.launch {
		Log.i(TAG, "Inserting problem into the repository!")
		repo.insert(problem)
	}

	companion object
	{
		const val TAG = "SsProblemViewModel"
	}

}

//class SsProblemViewModelFactory(private val repo: SsProblemRepository)
//	: ViewModelProvider.Factory
//{
//
//	override fun <T : ViewModel> create(modelClass: Class<T>): T
//	{
//
//		if (modelClass.isAssignableFrom(SsProblemViewModel::class.java)) {
//			@Suppress("UNCHECKED_CAST")
//			return SsProblemViewModel(repo) as T
//		}
//
//		throw IllegalArgumentException("Unknown ViewModel class")
//	}
//
//}
