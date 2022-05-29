package me.gabeg.sicksends.sport

import me.gabeg.sicksends.problem.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for sport problems.
 */
class SsSportRepository @Inject constructor(dao : SsSportDao)
	: SsGenericProblemRepository<SsSportProblem>(dao)
