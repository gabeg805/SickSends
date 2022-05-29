package me.gabeg.sicksends.toprope

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import me.gabeg.sicksends.problem.SsGenericProblem
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
	//@Query("DELETE FROM top_rope")
	//override suspend fun deleteAll()

	/**
	 * @see SsGenericProblemDao.findProblems
	 */
	@Query("SELECT * FROM top_rope ${SsGenericProblem.WHERE} ${SsGenericProblem.ORDER_BY}")
	override fun findProblems(
		isOutdoor : Boolean?,
		isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsTopRopeProblem>>

	/**
	 * Get all top rope problems.
	 *
	 * @return All top rope problems.
	 */
	@Query("SELECT * FROM top_rope ${SsGenericProblem.ORDER_BY}")
	override fun getAllProblems() : LiveData<List<SsTopRopeProblem>>

}
