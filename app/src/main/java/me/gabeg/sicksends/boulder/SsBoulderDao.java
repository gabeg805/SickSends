package me.gabeg.sicksends.boulder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

import me.gabeg.sicksends.problem.SsProblemDao;

/**
 * Data access object for bouldering.
 */
@Dao
public interface SsBoulderDao
	extends SsProblemDao<SsBoulder>
{

	/**
	 * @see SsProblemDao#deleteAll
	 */
	@Query("DELETE FROM boulder")
	void deleteAll();

	/**
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder")
	LiveData<List<SsBoulder>> getAll();

}
