package me.gabeg.sicksends.toprope

import me.gabeg.sicksends.problem.SsGenericProblemRepository

/**
 * Repository for top rope problems.
 */
class SsTopRopeRepository(dao : SsTopRopeDao)
	: SsGenericProblemRepository<SsTopRopeProblem>(dao)
