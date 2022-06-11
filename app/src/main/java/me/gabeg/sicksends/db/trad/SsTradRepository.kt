package me.gabeg.sicksends.db.trad

import me.gabeg.sicksends.db.generic.SsGenericProblemRepository
import javax.inject.Inject

/**
 * Repository for trad problems.
 */
class SsTradRepository @Inject constructor(dao : SsTradDao)
	: SsGenericProblemRepository<SsTradProblem>(dao)
