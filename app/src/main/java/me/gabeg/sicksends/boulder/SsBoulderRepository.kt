package me.gabeg.sicksends.boulder

import me.gabeg.sicksends.problem.SsProblemRepository

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository(dao : SsBoulderDao)
	: SsProblemRepository<SsBoulderDao, SsBoulder>(dao)
