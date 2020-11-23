package me.gabeg.sicksends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for sport climbing.
 */
@Dao
public interface SsSportDao
	extends SsProblemDao<SsSport>
{

	/**
	 * @return All sport problems.
	 */
	@Query("SELECT * FROM sport")
	LiveData<List<SsSport>> getAll();

}
