package me.gabeg.sicksends.boulder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Boulder view model.
 */
class SsBoulderViewModel(private val repo : SsBoulderRepository) : ViewModel()
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	val allProblems : LiveData<List<SsBoulderProblem>> = repo.allProblems

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : SsBoulderProblem) = viewModelScope.launch {
		Log.i("SsBoulderViewModel", "Inserting problem into the repository!")
		repo.insert(problem)
	}

}
