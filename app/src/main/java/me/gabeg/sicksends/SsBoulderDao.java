package me.gabeg.sicksends;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for bouldering.
 */
@Dao
public interface SsBoulderDao
{

	/**
	 * Delete the boulder problem.
	 *
	 * @param  problem  Boulder problem.
	 */
	@Delete
	void delete(SsBoulderProblem problem);

	/**
	 * @return All boulder problems.
	 */
	@Query("SELECT * FROM boulder")
	List<SsBoulderProblem> getAll();

	/**
	 * Insert a new boulder problem.
	 *
	 * @param  problem  Boulder problem.
	 */
	@Insert
	void insert(SsBoulderProblem problem);

}
