package me.gabeg.sicksends.dummy

import androidx.room.*
import me.gabeg.sicksends.problem.SsProblemDao
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for bouldering.
 */
@Dao
interface SsDummyDao
{

	/**
	 * Delete a problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Delete
	fun delete(problem: SsDummyProblem)

	/**
	 * @see SsProblemDao.deleteAll
	 */
	@Query("DELETE FROM dummy")
	fun deleteAll()

	/**
	 * Insert a new problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Insert
	suspend fun insert(problem: SsDummyProblem)

	/**
	 * Update an existing problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Update
	fun update(problem: SsDummyProblem)

	/**
	 * Get all dummy problems.
	 *
	 * @return All dummy problems.
	 */
	@get:Query("SELECT * FROM dummy")
	val all: Flow<List<SsDummyProblem>>

}
