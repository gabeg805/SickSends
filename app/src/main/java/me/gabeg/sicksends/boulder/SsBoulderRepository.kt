package me.gabeg.sicksends.boulder

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.Flow

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository(private val dao : SsBoulderDao)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 */
	val allProblems : LiveData<List<SsBoulderProblem>> = dao.getAllProblems()

	/**
	 * Insert a type of climbing problem, asynchronously, into the database.
	 */
	@WorkerThread
	suspend fun insert(problem: SsBoulderProblem)
	{
		dao.insert(problem)
	}

}
