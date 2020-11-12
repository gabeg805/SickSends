package me.gabeg.sicksends;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for trad problems.
 */
@Dao
public interface SsTradDao
{

	/**
	 * Delete the trad problem.
	 *
	 * @param  problem  Trad problem.
	 */
	@Delete
	void delete(SsTradProblem problem);

	/**
	 * @return All trad problems.
	 */
	@Query("SELECT * FROM trad")
	List<SsTradProblem> getAll();

	/**
	 * Insert a new trad problem.
	 *
	 * @param  problem  Trad problem.
	 */
	@Insert
	void insert(SsTradProblem problem);

}
