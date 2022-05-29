package me.gabeg.sicksends.toprope

import me.gabeg.sicksends.problem.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for top rope problems.
 */
class SsTopRopeRepository @Inject constructor(dao : SsTopRopeDao)
	: SsGenericProblemRepository<SsTopRopeProblem>(dao)
