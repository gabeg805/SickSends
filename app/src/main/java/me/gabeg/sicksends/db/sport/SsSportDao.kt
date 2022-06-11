package me.gabeg.sicksends.db.sport

import androidx.lifecycle.LiveData
import androidx.room.Dao
import me.gabeg.sicksends.db.generic.SsGenericProblemDao
import androidx.room.Query
import me.gabeg.sicksends.db.generic.SsGenericProblem

/**
 * Data access object for sport climbing.
 */
@Dao
interface SsSportDao : SsGenericProblemDao<SsSportProblem>
{

	/**
	 * Delete all sport problems.
	 */
	//@Query("DELETE FROM sport")
	//override suspend fun deleteAll()

	/**
	 * @see SsGenericProblemDao.findProblems
	 */
	@Query("SELECT * FROM sport ${SsGenericProblem.WHERE} ${SsGenericProblem.ORDER_BY}")
	override fun findProblems(
		isOutdoor : Boolean?,
		isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsSportProblem>>

	/**
	 * Get all sport problems.
	 *
	 * @return All sport problems.
	 */
	@Query("SELECT * FROM sport ${SsGenericProblem.ORDER_BY}")
	override fun getAllProblems() : LiveData<List<SsSportProblem>>

}
