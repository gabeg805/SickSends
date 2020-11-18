package me.gabeg.sicksends;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Data access object for climbing problems.
 */
@Dao
public abstract interface SsProblemDao<T>
{

	/**
	 * Delete a problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Delete
	void delete(T problem);

	/**
	 * Delete all problems.
	 */
	//abstract void deleteAll();

	/**
	 * Insert a new problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Insert
	void insert(T problem);

	/**
	 * Update an existing problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Update
	void update(T problem);

	/**
	 * @return All climbing problems.
	 */
	abstract LiveData<List<T>> getAll();

}
