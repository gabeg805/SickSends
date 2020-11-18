package me.gabeg.sicksends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for top rope problems.
 */
@Dao
public interface SsTopRopeDao
	extends SsProblemDao<SsTopRope>
{

	/**
	 * Delete all top rope problems.
	 */
	@Query("DELETE FROM top_rope")
	void deleteAll();

	/**
	 * @return All top rope problems.
	 */
	@Query("SELECT * FROM top_rope")
	LiveData<List<SsTopRope>> getAll();

}
