package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;

/**
 * Default values that are defined in an XML file in the values/ directory.
 */
public class SsSharedDefaults
	extends SsSharedResource
{

	/**
	 */
	public SsSharedDefaults(Context context)
	{
		super(context);
	}

	/**
	 */
	public SsSharedDefaults(Resources res)
	{
		super(res);
	}

	/**
	 * @return App first run.
	 */
	public boolean getAppFirstRun()
	{
		return this.getBoolean(R.bool.default_app_first_run);
	}

	/**
	 * @return Rate my app counter.
	 */
	public int getRateMyAppCounter()
	{
		return this.getInteger(R.integer.default_rate_my_app_counter);
	}

	/**
	 * @return Rate my app limit.
	 */
	public int getRateMyAppLimit()
	{
		return this.getInteger(R.integer.default_rate_my_app_limit);
	}

	/**
	 * @return Rate my app rated.
	 */
	public int getRateMyAppRated()
	{
		return this.getInteger(R.integer.default_rate_my_app_rated);
	}

	/**
	 * @return Default value for whether a climbing grade will be used or not.
	 */
	public boolean getUseGrade()
	{
		return this.getBoolean(R.bool.default_use_grade);
	}

	/**
	 * @return Default value for whether a climbing type will be climbed or not.
	 */
	public boolean getWillClimb()
	{
		return this.getBoolean(R.bool.default_will_climb);
	}

}
