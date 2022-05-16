package me.gabeg.sicksends.toprope

import me.gabeg.sicksends.problem.SsProblemRepository

/**
 * Repository for top rope problems.
 */
class SsTopRopeRepository(dao : SsTopRopeDao)
	: SsProblemRepository<SsTopRopeDao, SsTopRope>(dao)
