package me.gabeg.sicksends;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for lead climbing.
 */
@Dao
public interface SsLeadDao
{

	/**
	 * Delete the lead problem.
	 *
	 * @param  problem  Lead problem.
	 */
	@Delete
	void delete(SsLeadProblem problem);

	/**
	 * @return All lead problems.
	 */
	@Query("SELECT * FROM lead")
	List<SsLeadProblem> getAll();

	/**
	 * Insert a new lead problem.
	 *
	 * @param  problem  Lead problem.
	 */
	@Insert
	void insert(SsLeadProblem problem);

}
