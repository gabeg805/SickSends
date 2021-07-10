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
	@ColumnInfo(name="num_take")
	public int mNumTake;

	/**
	 * Whether the problem was onsighted or not.
	 */
	@ColumnInfo(name="is_onsight")
	public boolean mIsOnsight;

	/**
	 * Whether the problem was a redpoint or not.
	 */
	@ColumnInfo(name="is_redpoint")
	public boolean mIsRedpoint;

	// Number of pitches?

	/**
	 * Get whether the problem was onsighted or not.
	 *
	 * @return Whether the problem was onsighted or not.
	 */
	public boolean isOnsight()
	{
		return this.mIsOnsight;
	}

	/**
	 * Get whether the problem was a redpoint or not.
	 *
	 * @return Whether the problem was a redpoint or not.
	 */
	public boolean isRedpoint()
	{
		return this.mIsRedpoint;
	}

	/**
	 * @see #isOnsight()
	 */
	public boolean getIsOnsight()
	{
		return this.isOnsight();
	}

	/**
	 * @see #isRedpoint()
	 */
	public boolean getIsRedpoint()
	{
		return this.isRedpoint();
	}

	/**
	 * Get the number of takes done on the problem.
	 *
	 * @return The number of takes done on the problem.
	 */
	public int getNumTake()
	{
		return this.mNumTake;
	}

	/**
	 * Set whether the problem was onsighted or not.
	 *
	 * @param  onsight  Whether the problem was onsighted or not.
	 */
	public void setIsOnsight(boolean onsight)
	{
		this.mIsOnsight = onsight;
	}

	/**
	 * Set whether the problem was a redpoint or not.
	 *
	 * @param  redpoint  Whether the problem was a redpoint or not.
	 */
	public void setIsRedpoint(boolean redpoint)
	{
		this.mIsRedpoint = redpoint;
	}

	/**
	 * Set the number of takes done on the problem.
	 *
	 * @param  take  The number of takes done on the problem.
	 */
	public void setNumTake(int take)
	{
		this.mNumTake = take;
	}

}
