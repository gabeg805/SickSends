package me.gabeg.sicksends.db.sport

import me.gabeg.sicksends.db.generic.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for sport problems.
 */
class SsSportRepository @Inject constructor(dao : SsSportDao)
	: SsGenericProblemRepository<SsSportProblem>(dao)
