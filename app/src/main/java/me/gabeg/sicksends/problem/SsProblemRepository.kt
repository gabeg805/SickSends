package me.gabeg.sicksends.problem

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 */
abstract class SsProblemRepository<T : SsProblemDao<S>, S : SsProblem>(
	private val dao : T)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 *
	 * @return The live data list of all climbing problems.
	 */
	//val allProblems: Flow<List<S>> = dao.all
	val allProblems = dao.all

	/**
	 * Insert a type of climbing problem, asynchronously, into the database.
	 */
	@WorkerThread
	suspend fun insert(problem: S)
	{
		dao.insert(problem)
	}

}