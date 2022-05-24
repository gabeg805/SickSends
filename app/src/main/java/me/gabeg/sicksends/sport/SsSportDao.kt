package me.gabeg.sicksends.sport

import androidx.lifecycle.LiveData
import androidx.room.Dao
import me.gabeg.sicksends.problem.SsGenericProblemDao
import androidx.room.Query

/**
 * Data access object for sport climbing.
 */
@Dao
interface SsSportDao : SsGenericProblemDao<SsSportProblem>
{

	/**
	 * Delete all sport problems.
	 */
	@Query("DELETE FROM sport")
	override suspend fun deleteAll()

	/**
	 * Get all sport problems.
	 *
	 * @return All sport problems.
	 */
	@Query("SELECT * FROM sport ORDER BY timestamp DESC")
	override fun getAllProblems() : LiveData<List<SsSportProblem>>

}
