package me.gabeg.sicksends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for lead climbing.
 */
@Dao
public interface SsLeadDao
	extends SsProblemDao<SsLead>
{

	/**
	 * @return All lead problems.
	 */
	@Query("SELECT * FROM lead")
	LiveData<List<SsLead>> getAll();

}
