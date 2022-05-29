package me.gabeg.sicksends.boulder

import me.gabeg.sicksends.problem.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository @Inject constructor(dao : SsBoulderDao)
	: SsGenericProblemRepository<SsBoulderProblem>(dao)
