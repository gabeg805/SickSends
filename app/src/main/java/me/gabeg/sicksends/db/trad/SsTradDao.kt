package me.gabeg.sicksends.db.trad

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.db.generic.SsGenericProblemDao

/**
 * Data access object for trad problems.
 */
@Dao
interface SsTradDao : SsGenericProblemDao<SsTradProblem>
{

	/**
	 * Delete all trad problems.
	 */
	//@Query("DELETE FROM trad")
	//override suspend fun deleteAll()

	/**
	 * @see SsGenericProblemDao.findProblems
	 */
	@Query("SELECT * FROM trad ${SsGenericProblem.WHERE} ${SsGenericProblem.ORDER_BY}")
	override fun findProblems(
		isOutdoor : Boolean?,
		isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsTradProblem>>

	/**
	 * Get all trad problems.
	 *
	 * @return All trad problems.
	 */
	@Query("SELECT * FROM trad ${SsGenericProblem.ORDER_BY}")
	override fun getAllProblems() : LiveData<List<SsTradProblem>>

}
