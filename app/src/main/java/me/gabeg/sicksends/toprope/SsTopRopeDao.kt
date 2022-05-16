package me.gabeg.sicksends.toprope

import androidx.room.Dao
import me.gabeg.sicksends.problem.SsProblemDao
import me.gabeg.sicksends.toprope.SsTopRope
import androidx.lifecycle.LiveData
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for top rope problems.
 */
@Dao
interface SsTopRopeDao : SsProblemDao<SsTopRope>
{

	/**
	 * Delete all top rope problems.
	 */
	@Query("DELETE FROM top_rope")
	override fun deleteAll()

	/**
	 * Get all top rope problems.
	 *
	 * @return All top rope problems.
	 */
	@get:Query("SELECT * FROM top_rope")
	override val all: Flow<List<SsTopRope>>

}