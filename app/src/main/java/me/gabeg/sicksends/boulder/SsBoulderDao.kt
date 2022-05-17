package me.gabeg.sicksends.boulder

import androidx.room.Dao
import me.gabeg.sicksends.problem.SsProblemDao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for bouldering.
 */
@Dao
interface SsBoulderDao : SsProblemDao<SsBoulderProblem>
{
	/**
	 * @see SsProblemDao.deleteAll
	 */
	@Query("DELETE FROM boulder")
	override fun deleteAll()

	/**
	 * Get all boulder problems.
	 *
	 * @return All boulder problems.
	 */
	@get:Query("SELECT * FROM boulder")
	override val all: Flow<List<SsBoulderProblem>>

}
