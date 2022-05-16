package me.gabeg.sicksends.rope

import me.gabeg.sicksends.problem.SsProblem
import androidx.room.ColumnInfo

/**
 * Aspects of a Climbing problem, specifically for ropes, that are saved.
 *
 * TODO: Number of pitches?
 */
abstract class SsRopeProblem : SsProblem()
{

	/**
	 * Number of takes done on the problem.
	 */
	@ColumnInfo(name = "num_take")
	var numTake = 0

	/**
	 * Whether the problem was onsighted or not.
	 */
	@ColumnInfo(name = "is_onsight")
	var isOnsight = false

	/**
	 * Whether the problem was a redpoint or not.
	 */
	@ColumnInfo(name = "is_redpoint")
	var isRedpoint = false

}
