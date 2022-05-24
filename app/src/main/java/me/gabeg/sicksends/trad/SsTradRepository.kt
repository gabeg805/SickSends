package me.gabeg.sicksends.trad

import me.gabeg.sicksends.problem.SsGenericProblemRepository

/**
 * Repository for trad problems.
 */
class SsTradRepository(dao : SsTradDao)
	: SsGenericProblemRepository<SsTradProblem>(dao)
