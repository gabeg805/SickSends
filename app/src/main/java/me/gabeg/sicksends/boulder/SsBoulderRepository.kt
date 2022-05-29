package me.gabeg.sicksends.boulder

import androidx.lifecycle.LiveData
import me.gabeg.sicksends.problem.SsGenericProblemRepository

/**
 * Repository for boulder problems.
 */
class SsBoulderRepository(dao : SsBoulderDao)
	: SsGenericProblemRepository<SsBoulderProblem>(dao)
