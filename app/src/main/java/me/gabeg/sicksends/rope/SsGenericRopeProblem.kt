package me.gabeg.sicksends.rope

import me.gabeg.sicksends.problem.SsGenericProblem

/**
 * Aspects of a Climbing problem, specifically for ropes, that are saved.
 *
 * TODO: Number of pitches?
 */
abstract class SsGenericRopeProblem : SsGenericProblem()
{

	/**
	 * Number of takes done on the problem.
	 */
	abstract var numTake : Int

	/**
	 * Whether the problem was onsighted or not.
	 */
	abstract var isOnsight : Boolean

	/**
	 * Whether the problem was a redpoint or not.
	 */
	abstract var isRedpoint : Boolean

}
