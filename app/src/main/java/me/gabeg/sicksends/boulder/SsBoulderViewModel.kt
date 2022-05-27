package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import me.gabeg.sicksends.problem.SsGenericProblemViewModel

/**
 * Boulder view model.
 */
class SsBoulderViewModel(repo : SsBoulderRepository)
	: SsGenericProblemViewModel<SsBoulderProblem>(repo)
{

	/**
	 * Live data list of all climbing problems of a particular type.
	 *
	 * @return The live data list of all climbing problems.
	 */
	fun getProblemsWhere(
		isIndoor : Boolean, isOutdoor : Boolean,
		isProject : Boolean, isSend : Boolean,
		isFlash : Boolean, isNormal : Boolean) : LiveData<List<SsBoulderProblem>>
	{
		return (repo as SsBoulderRepository).getProblemsWhere(
			isIndoor, isOutdoor, isProject, isSend, isFlash, isNormal)
	}

}
