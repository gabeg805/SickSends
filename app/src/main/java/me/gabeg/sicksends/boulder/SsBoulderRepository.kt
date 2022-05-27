package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import me.gabeg.sicksends.problem.SsGenericProblemRepository

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository(dao : SsBoulderDao)
	: SsGenericProblemRepository<SsBoulderProblem>(dao)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 *
	 * @return The live data list of all climbing problems.
	 */
	fun getProblemsWhere(isOutdoor : Boolean?, isProject : Boolean?,
		isFlash : Boolean?) : LiveData<List<SsBoulderProblem>>
	{
		return (dao as SsBoulderDao).getProblemsWhere(
			isOutdoor, isProject, isFlash)
		//return (dao as SsBoulderDao).getProblemsWhere(
		//	isIndoor, isOutdoor, isProject, isSend, isFlash, isNormal)
	}

}
