package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;

/**
 * Container of string keys of each preference.
 */
public class SsSharedKeys
	extends SsSharedResource
{

	/**
	 */
	public SsSharedKeys(Context context)
	{
		super(context);
	}

	/**
	 */
	public SsSharedKeys(Resources res)
	{
		super(res);
	}

	/**
	 * @return The post install, first run key.
	 */
	public String getAppFirstRun()
	{
		return this.getString(R.string.app_first_run);
	}

	/**
	 * @return The counter used to check when the rate-my-app dialog should be
	 *         shown.
	 */
	public String getRateMyAppCounter()
	{
		return this.getString(R.string.app_rating_counter);
	}

	/**
	 * @return The bouldering climbing type.
	 */
	public String getWillClimbBouldering()
	{
		return this.getString(R.string.will_climb_bouldering);
	}

	/**
	 * @return The sport climbing type.
	 */
	public String getWillClimbSport()
	{
		return this.getString(R.string.will_climb_sport);
	}

	/**
	 * @return The top rope climbing type.
	 */
	public String getWillClimbTopRope()
	{
		return this.getString(R.string.will_climb_top_rope);
	}

	/**
	 * @return The trad climbing type.
	 */
	public String getWillClimbTrad()
	{
		return this.getString(R.string.will_climb_trad);
	}

}
