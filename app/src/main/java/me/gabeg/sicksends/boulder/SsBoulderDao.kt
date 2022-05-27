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

	/**
	 * Get all boulder problems.
	 *
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder " +
		"WHERE (:isOutdoor IS NULL OR is_outdoor = :isOutdoor) " +
		"AND   (:isProject IS NULL OR is_project = :isProject) " +
		"AND   (:isFlash   IS NULL OR is_flash   = :isFlash) " +
		"ORDER BY timestamp DESC")
	fun getProblemsWhere(isOutdoor : Boolean?, isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsBoulderProblem>>

}
