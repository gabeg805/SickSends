package me.gabeg.sicksends.problem

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 */
abstract class SsGenericProblemRepository<T : SsGenericProblem>(
	protected val dao : SsGenericProblemDao<T>)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 *
	 * @return The live data list of all climbing problems.
	 */
	val allProblems : LiveData<List<T>> = dao.getAllProblems()

	/**
	 * Insert a type of climbing problem, asynchronously, into the database.
	 */
	@WorkerThread
	suspend fun insert(problem: T)
	{
		dao.insert(problem)
	}

}
