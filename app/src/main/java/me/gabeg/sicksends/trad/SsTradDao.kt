package me.gabeg.sicksends.trad

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import me.gabeg.sicksends.problem.SsGenericProblemDao

/**
 * Data access object for trad problems.
 */
@Dao
interface SsTradDao : SsGenericProblemDao<SsTradProblem>
{

	/**
	 * Delete all trad problems.
	 */
	@Query("DELETE FROM trad")
	override suspend fun deleteAll()

	/**
	 * Get all trad problems.
	 *
	 * @return All trad problems.
	 */
	@Query("SELECT * FROM trad ORDER BY timestamp DESC")
	override fun getAllProblems() : LiveData<List<SsTradProblem>>

}
