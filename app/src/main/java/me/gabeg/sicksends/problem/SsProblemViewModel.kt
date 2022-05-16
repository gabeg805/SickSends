package me.gabeg.sicksends.problem

import me.gabeg.sicksends.problem.SsProblemRepository
import me.gabeg.sicksends.problem.SsProblem
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import me.gabeg.sicksends.problem.SsProblemViewModel

/**
 * Problem view model.
 */
//SsProblemRepository<SsProblemDao<SsProblem>, SsProblem>
//abstract class SsProblemViewModel<T : SsProblemRepository<*, *>?, S : SsProblem?>(app: Application?) : AndroidViewModel(app!!)
//abstract class SsProblemViewModel<T : SsProblemRepository<*, *>, S : SsProblem>(
//abstract class SsProblemViewModel<T : SsProblemRepository<SsProblemDao<S>, S>, S : SsProblem>(
//	private val repo : T) : ViewModel()

abstract class SsProblemViewModel<S : SsProblem>(
	private val repo : SsProblemRepository<*, S>) : ViewModel()
//private val repo : SsProblemRepository<SsProblemDao<S>, S>) : ViewModel()
{

	///**
	// * Repository of the climbing problems.
	// */
	//@JvmField
	//protected var mRepository: T? = null

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	//val allProblems: LiveData<List<S>> = repo.allProblems.asLiveData()
	val allProblems = repo.allProblems.asLiveData()
	//var allProblems: LiveData<List<S>>? = null
	//	protected set

	///**
	// * Build the live data list of all climbing problems.
	// */
	//fun buildAllProblems(repo: T)
	//{
	//	//this.mAllProblems = repo.getAllProblems();
	//}

	///**
	// * Build the repository of climbing problems.
	// */
	//abstract fun buildRepository(app: Application?)

	///**
	// * @return Repository of the climbing problems.
	// */
	//val repository: T
	//	get() = mRepository

	/**
	 * Insert a type of climbing problem into the database via the repository.
	 */
	fun insert(problem : S) = viewModelScope.launch {
		Log.i(TAG, "Inserting problem into the repository!")
		repo.insert(problem)
		//this.getRepository().insert(problem);
	}

	companion object
	{
		const val TAG = "SsProblemViewModel"
	}

	///**
	// */
	//init
	//{
	//	buildRepository(app)
	//	buildAllProblems(repository)
	//}

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
