package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import androidx.room.*
import me.gabeg.sicksends.problem.SsGenericProblem
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
	//@Query("DELETE FROM boulder")
	//override suspend fun deleteAll()

	/**
	 * Get all boulder problems.
	 *
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder ${SsGenericProblem.ORDER_BY}")
	override fun getAllProblems() : LiveData<List<SsBoulderProblem>>

	/**
	 * @see SsGenericProblemDao.findProblems
	 */
	@Query("SELECT * FROM boulder ${SsGenericProblem.WHERE} ${SsGenericProblem.ORDER_BY}")
	override fun findProblems(
		isOutdoor : Boolean?,
		isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsBoulderProblem>>

}
