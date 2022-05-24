package me.gabeg.sicksends.sport

import me.gabeg.sicksends.problem.SsGenericProblemRepository

/**
 * Repository for sport problems.
 */
class SsSportRepository(dao : SsSportDao)
	: SsGenericProblemRepository<SsSportProblem>(dao)
