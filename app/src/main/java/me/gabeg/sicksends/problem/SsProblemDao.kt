package me.gabeg.sicksends.problem

import androidx.room.Dao
import androidx.room.Delete
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for climbing problems.
 */
@Dao
interface SsProblemDao<T : SsProblem>
{

	/**
	 * Delete a problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Delete
	fun delete(problem: T)

	/**
	 * Delete all problems.
	 */
	abstract fun deleteAll()

	/**
	 * Insert a new problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Insert
	suspend fun insert(problem: T)

	/**
	 * Update an existing problem.
	 *
	 * @param  problem  Climbing problem.
	 */
	@Update
	fun update(problem: T)

	/**
	 * Get all climbing problems.
	 *
	 * @return All climbing problems.
	 */
	val all: Flow<List<T>>
	//val all: Flow<List<T>?>?

}
