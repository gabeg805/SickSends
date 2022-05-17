package me.gabeg.sicksends.trad

import me.gabeg.sicksends.problem.SsProblemRepository

/**
 * Repository for trad problems.
 */
class SsTradRepository(dao : SsTradDao)
	: SsProblemRepository<SsTradProblem>(dao)
