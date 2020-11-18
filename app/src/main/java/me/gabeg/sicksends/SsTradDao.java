package me.gabeg.sicksends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for trad problems.
 */
@Dao
public interface SsTradDao
	extends SsProblemDao<SsTrad>
{

	/**
	 * @return All trad problems.
	 */
	@Query("SELECT * FROM trad")
	LiveData<List<SsTrad>> getAll();

}
