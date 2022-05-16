package me.gabeg.sicksends.sport

import me.gabeg.sicksends.problem.SsProblemRepository

/**
 * Repository for sport problems.
 */
class SsSportRepository(dao : SsSportDao)
	: SsProblemRepository<SsSportDao, SsSport>(dao)
