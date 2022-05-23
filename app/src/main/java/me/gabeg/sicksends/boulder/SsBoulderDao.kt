package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import androidx.room.*
import me.gabeg.sicksends.problem.SsProblemDao
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for bouldering.
 */
@Dao
interface SsBoulderDao
{

	/**
	 * Delete a problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Delete
	fun delete(problem: SsBoulderProblem)

	/**
	 * @see SsProblemDao.deleteAll
	 */
	@Query("DELETE FROM boulder")
	fun deleteAll()

	/**
	 * Insert a new problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Insert
	suspend fun insert(problem: SsBoulderProblem)

	/**
	 * Update an existing problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Update
	fun update(problem: SsBoulderProblem)

	/**
	 * Get all boulder problems.
	 *
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder ORDER BY timestamp DESC")
	fun getAllProblems() : LiveData<List<SsBoulderProblem>>

}
