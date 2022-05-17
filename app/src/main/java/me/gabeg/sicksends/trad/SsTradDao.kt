package me.gabeg.sicksends.trad

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.gabeg.sicksends.problem.SsProblemDao

/**
 * Data access object for trad problems.
 */
@Dao
interface SsTradDao : SsProblemDao<SsTradProblem>
{

	/**
	 * Delete all trad problems.
	 */
	@Query("DELETE FROM trad")
	override fun deleteAll()

	/**
	 * Get all trad problems.
	 *
	 * @return All trad problems.
	 */
	@get:Query("SELECT * FROM trad")
	override val all: Flow<List<SsTradProblem>>

}
