package me.gabeg.sicksends.db.toprope

import me.gabeg.sicksends.db.generic.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for top rope problems.
 */
class SsTopRopeRepository @Inject constructor(dao : SsTopRopeDao)
	: SsGenericProblemRepository<SsTopRopeProblem>(dao)
