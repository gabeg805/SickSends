package me.gabeg.sicksends.dummy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.problem.SsProblem
import me.gabeg.sicksends.problem.SsProblemRepository
import me.gabeg.sicksends.problem.SsProblemViewModel

/**
 * Boulder view model.
 */
class SsDummyViewModel(private val repo : SsDummyRepository) : ViewModel()
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	//val allProblems: LiveData<List<S>> = repo.allProblems.asLiveData()
	val allProblems = repo.allProblems.asLiveData()

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : SsDummyProblem) = viewModelScope.launch {
		Log.i(TAG, "Inserting problem into the repository!")
		repo.insert(problem)
	}

	companion object
	{
		const val TAG = "SsProblemViewModel"
	}

}
