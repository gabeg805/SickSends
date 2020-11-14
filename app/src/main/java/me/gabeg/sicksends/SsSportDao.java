package me.gabeg.sicksends;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for sport climbing.
 */
@Dao
public interface SsSportDao
{

	/**
	 * Delete the sport problem.
	 *
	 * @param  problem  Sport problem.
	 */
	@Delete
	void delete(SsSportProblem problem);

	/**
	 * @return All sport problems.
	 */
	@Query("SELECT * FROM sport")
	List<SsSportProblem> getAll();

	/**
	 * Insert a new sport problem.
	 *
	 * @param  problem  Sport problem.
	 */
	@Insert
	void insert(SsSportProblem problem);

}
