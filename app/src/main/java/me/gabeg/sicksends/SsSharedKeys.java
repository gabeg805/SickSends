package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;

/**
 * Container of string keys of each preference.
 */
public class SsSharedKeys
{

	/**
	 * The first run after installing the app.
	 */
	private final String mAppFirstRun;

	/**
	 * The counter used to determine when to show the rate-my-app dialog.
	 */
	private final String mRateMyAppCounter;

	/**
	 * Bouldering climbing type.
	 */
	private final String mClimbingTypeBouldering;

	/**
	 * Sport climbing type.
	 */
	private final String mClimbingTypeSport;

	/**
	 * Top rope climbing type.
	 */
	private final String mClimbingTypeTopRope;

	/**
	 * Trad climbing type.
	 */
	private final String mClimbingTypeTrad;

	/**
	 */
	public SsSharedKeys(Context context)
	{
		Resources res = context.getResources();

		this.mAppFirstRun = res.getString(R.string.app_first_run);
		this.mRateMyAppCounter = res.getString(R.string.app_rating_counter);
		this.mClimbingTypeBouldering = res.getString(R.string.climbing_type_bouldering);
		this.mClimbingTypeSport = res.getString(R.string.climbing_type_sport);
		this.mClimbingTypeTopRope = res.getString(R.string.climbing_type_top_rope);
		this.mClimbingTypeTrad = res.getString(R.string.climbing_type_trad);
	}

	/**
	 * @return The post install, first run key.
	 */
	public String getAppFirstRun()
	{
		return this.mAppFirstRun;
	}

	/**
	 * @return The desired  climbing type.
	 */
	public String getClimbingType(SsClimbing.Type type)
	{
		switch (type)
		{
			case BOULDERING:
				return this.getClimbingTypeBouldering();
			case SPORT:
				return this.getClimbingTypeSport();
			case TOP_ROPE:
				return this.getClimbingTypeTopRope();
			case TRAD:
				return this.getClimbingTypeTrad();
			default:
				return "";
		}
	}

	/**
	 * @return The bouldering climbing type.
	 */
	public String getClimbingTypeBouldering()
	{
		return this.mClimbingTypeBouldering;
	}

	/**
	 * @return The sport climbing type.
	 */
	public String getClimbingTypeSport()
	{
		return this.mClimbingTypeSport;
	}

	/**
	 * @return The top rope climbing type.
	 */
	public String getClimbingTypeTopRope()
	{
		return this.mClimbingTypeTopRope;
	}

	/**
	 * @return The trad climbing type.
	 */
	public String getClimbingTypeTrad()
	{
		return this.mClimbingTypeTrad;
	}

	/**
	 * @return The counter used to check when the rate-my-app dialog should be
	 *         shown.
	 */
	public String getRateMyAppCounter()
	{
		return this.mRateMyAppCounter;
	}

}
