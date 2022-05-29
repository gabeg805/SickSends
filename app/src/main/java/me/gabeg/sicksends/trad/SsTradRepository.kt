package me.gabeg.sicksends.trad

import me.gabeg.sicksends.problem.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for trad problems.
 */
class SsTradRepository @Inject constructor(dao : SsTradDao)
	: SsGenericProblemRepository<SsTradProblem>(dao)
