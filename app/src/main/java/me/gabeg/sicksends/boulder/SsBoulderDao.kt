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
	//@Query("SELECT * FROM boulder ORDER BY timestamp DESC " +
	//	"WHERE is_outdoor = :isOutdoor AND is_outdoor != :isIndoor " +
	//	"is_project = :isProject AND is_project != :isSend " +
	//	"is_flash = :isFlash AND is_flash != :isFlash")
	//"is_flash = :isFlash AND is_flash != :isNormal")
	//"is_outdoor = :isOutdoor AND " +
	//"is_project = :isProject AND " +
	//"is_flash = :isFlash")
	@Query("SELECT * FROM boulder WHERE is_outdoor = :isOutdoor AND is_project = :isProject AND is_flash = :isFlash")
	fun getProblemsWhere(
		isOutdoor : Boolean,
		isProject : Boolean,
		isFlash : Boolean) : LiveData<List<SsBoulderProblem>>
	//isIndoor : Boolean, isOutdoor : Boolean,
	//isProject : Boolean, isSend : Boolean,
	//isFlash : Boolean, isNormal : Boolean) : LiveData<List<SsBoulderProblem>>

}
