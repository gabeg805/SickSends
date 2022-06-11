package me.gabeg.sicksends.db.boulder

import me.gabeg.sicksends.db.generic.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository @Inject constructor(dao : SsBoulderDao)
	: SsGenericProblemRepository<SsBoulderProblem>(dao)
