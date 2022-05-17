package me.gabeg.sicksends.sport

import androidx.room.Dao
import me.gabeg.sicksends.problem.SsProblemDao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for sport climbing.
 */
@Dao
interface SsSportDao : SsProblemDao<SsSportProblem>
{

	/**
	 * Delete all sport problems.
	 */
	@Query("DELETE FROM sport")
	override fun deleteAll()

	/**
	 * Get all sport problems.
	 *
	 * @return All sport problems.
	 */
	@get:Query("SELECT * FROM sport")
	override val all: Flow<List<SsSportProblem>>

}
