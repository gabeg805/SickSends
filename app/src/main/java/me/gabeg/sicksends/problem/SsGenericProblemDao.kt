package me.gabeg.sicksends.problem

import androidx.room.Dao
import androidx.room.Delete
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.gabeg.sicksends.boulder.SsBoulderProblem

/**
 * Data access object for climbing problems.
 */
@Dao
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
	abstract suspend fun deleteAll()

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
	abstract fun getAllProblems() : LiveData<List<T>>

}
