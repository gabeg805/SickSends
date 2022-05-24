package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import androidx.room.*
import me.gabeg.sicksends.problem.SsGenericProblemDao

/**
 * Data access object for bouldering.
 */
@Dao
interface SsBoulderDao : SsGenericProblemDao<SsBoulderProblem>
{

	/**
	 * @see SsGenericProblemDao.deleteAll
	 */
	@Query("DELETE FROM boulder")
	override suspend fun deleteAll()

	/**
	 * Get all boulder problems.
	 *
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder ORDER BY timestamp DESC")
	override fun getAllProblems() : LiveData<List<SsBoulderProblem>>

}
