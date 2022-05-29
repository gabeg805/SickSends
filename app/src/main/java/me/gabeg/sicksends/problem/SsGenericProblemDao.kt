package me.gabeg.sicksends.problem

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data access object for climbing problems.
 */
//@Dao
interface SsGenericProblemDao<T : SsGenericProblem>
{

	/**
	 * Delete a problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Delete
	suspend fun delete(problem: T)

	/**
	 * Delete all problems.
	 */
	//abstract suspend fun deleteAll()

	/**
	 * Insert a new problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Insert
	suspend fun insert(problem: T)

	/**
	 * Update an existing problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Update
	suspend fun update(problem: T)

	/**
	 * Get all climbing problems.
	 *
	 * @return All climbing problems.
	 */
	abstract fun getAllProblems() : LiveData<List<@JvmSuppressWildcards T>>

	/**
	 * Find climbing problems that match a set of criteria.
	 *
	 * @return The climbing problems that were found.
	 */
	abstract fun findProblems(
		isOutdoor : Boolean? = null,
		isProject : Boolean? = null,
		isFlash : Boolean? = null) : LiveData<List<T>>

}
