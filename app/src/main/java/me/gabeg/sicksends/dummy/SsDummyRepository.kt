package me.gabeg.sicksends.dummy

import androidx.annotation.WorkerThread
import me.gabeg.sicksends.problem.SsProblem
import me.gabeg.sicksends.problem.SsProblemDao
import me.gabeg.sicksends.problem.SsProblemRepository

/**
 * Repository for boulder problems.
 */
class SsDummyRepository(private val dao : SsDummyDao)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 *
	 * @return The live data list of all climbing problems.
	 */
	val allProblems = dao.all

	/**
	 * Insert a type of climbing problem, asynchronously, into the database.
	 */
	@WorkerThread
	suspend fun insert(problem: SsDummyProblem)
	{
		dao.insert(problem)
	}

}
