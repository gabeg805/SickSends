package me.gabeg.sicksends.boulder

import androidx.room.Dao
import me.gabeg.sicksends.problem.SsProblemDao
import me.gabeg.sicksends.boulder.SsBoulder
import androidx.lifecycle.LiveData
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for bouldering.
 */
@Dao
interface SsBoulderDao : SsProblemDao<SsBoulder>
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
	override val all: Flow<List<SsBoulder>>

}
