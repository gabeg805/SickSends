package me.gabeg.sicksends;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

/**
 * Data access object for top rope problems.
 */
@Dao
public interface SsTopRopeDao
{

	/**
	 * Delete the top rope problem.
	 *
	 * @param  problem  Top rope problem.
	 */
	@Delete
	void delete(SsTopRopeProblem problem);

	/**
	 * @return All top rope problems.
	 */
	@Query("SELECT * FROM top_rope")
	List<SsTopRopeProblem> getAll();

	/**
	 * Insert a new top rope problem.
	 *
	 * @param  problem  Top rope problem.
	 */
	@Insert
	void insert(SsTopRopeProblem problem);

}
