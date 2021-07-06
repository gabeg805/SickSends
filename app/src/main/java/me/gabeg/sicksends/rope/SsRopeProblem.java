package me.gabeg.sicksends.rope;

import androidx.room.ColumnInfo;

import me.gabeg.sicksends.problem.SsProblem;

/**
 * Aspects of a Climbing problem, specifically for ropes, that are saved.
 */
public abstract class SsRopeProblem
	extends SsProblem
{

	/**
	 * Number of takes done on the problem.
	 */
	@ColumnInfo(name="take")
	public int take;

	/**
	 * Was the problem onsighted?
	 */
	@ColumnInfo(name="is_onsight")
	public boolean isOnsight;

	/**
	 * Was the problem a redpoint?
	 */
	@ColumnInfo(name="is_redpoint")
	public boolean isRedpoint;

	// Number of pitches?

}
