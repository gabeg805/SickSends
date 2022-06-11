package me.gabeg.sicksends.db.generic

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 */
abstract class SsGenericProblemRepository<T : SsGenericProblem>(
	private val dao : SsGenericProblemDao<T>)
{

	/**
	 * All climbing problems in a table.
	 */
	val allProblems : LiveData<List<T>> = dao.getAllProblems()

	/**
	 * Delete a climbing problem from the database.
	 */
	@WorkerThread
	suspend fun delete(problem: T)
	{
		dao.delete(problem)
	}

	/**
	 * Find problems in a table.
	 *
	 * @return The live data list of problems found.
	 */
	fun findProblems(isOutdoor : Boolean? = null, isProject : Boolean? = null,
		isFlash : Boolean? = null) : LiveData<List<T>>
	{
		return dao.findProblems(isOutdoor, isProject, isFlash)
	}

	/**
	 * Insert a climbing problem into the database.
	 */
	@WorkerThread
	suspend fun insert(problem: T)
	{
		dao.insert(problem)
	}

	/**
	 * Update an existing climbing problem in the database.
	 */
	@WorkerThread
	suspend fun update(problem: T)
	{
		dao.update(problem)
	}

}
