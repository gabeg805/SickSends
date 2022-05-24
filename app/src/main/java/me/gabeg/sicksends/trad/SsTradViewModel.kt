package me.gabeg.sicksends.trad

import me.gabeg.sicksends.problem.SsGenericProblemViewModel

/**
 * Trad view model.
 */
class SsTradViewModel(repo : SsTradRepository)
	: SsGenericProblemViewModel<SsTradProblem>(repo)
