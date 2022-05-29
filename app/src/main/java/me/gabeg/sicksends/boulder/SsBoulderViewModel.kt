package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import me.gabeg.sicksends.problem.SsGenericProblemViewModel

/**
 * Boulder view model.
 */
class SsBoulderViewModel(repo : SsBoulderRepository)
	: SsGenericProblemViewModel<SsBoulderProblem>(repo)
