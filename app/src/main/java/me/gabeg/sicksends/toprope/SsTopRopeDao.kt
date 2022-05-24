package me.gabeg.sicksends.toprope

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import me.gabeg.sicksends.problem.SsGenericProblemDao

/**
 * Data access object for top rope problems.
 */
@Dao
interface SsTopRopeDao : SsGenericProblemDao<SsTopRopeProblem>
{

	/**
	 * Delete all top rope problems.
	 */
	@Query("DELETE FROM top_rope")
	override suspend fun deleteAll()

	/**
	 * Get all top rope problems.
	 *
	 * @return All top rope problems.
	 */
	@Query("SELECT * FROM top_rope ORDER BY timestamp DESC")
	override fun getAllProblems() : LiveData<List<SsTopRopeProblem>>

}
