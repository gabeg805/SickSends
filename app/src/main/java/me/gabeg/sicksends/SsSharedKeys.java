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
	 * @return Key for using a particular boulder grade.
	 */
	public String getUseGradeBoulder(String grade)
	{
		String key = this.getString(R.string.use_grade_boulder);
		return key.concat("_").concat(grade);
	}

	/**
	 * @return Key for using a particular lead grade.
	 */
	public String getUseGradeLead(String grade)
	{
		String key = this.getString(R.string.use_grade_lead);
		return key.concat("_").concat(grade);
	}

	///**
	// * @return Key for using a particular sport grade.
	// */
	//public String getUseGradeSport(String grade)
	//{
	//	String key = this.getString(R.string.use_grade_sport);
	//	return key.concat("_").concat(grade);
	//}

	/**
	 * @return Key for using a particular top rope grade.
	 */
	public String getUseGradeTopRope(String grade)
	{
		String key = this.getString(R.string.use_grade_top_rope);
		return key.concat("_").concat(grade);
	}

	/**
	 * @return Key for using a particular trad grade.
	 */
	public String getUseGradeTrad(String grade)
	{
		String key = this.getString(R.string.use_grade_trad);
		return key.concat("_").concat(grade);
	}

	/**
	 * @return The boulder climbing type.
	 */
	public String getWillClimbBoulder()
	{
		return this.getString(R.string.will_climb_boulder);
	}

	/**
	 * @return The lead climbing type.
	 */
	public String getWillClimbLead()
	{
		return this.getString(R.string.will_climb_lead);
	}

	///**
	// * @return The sport climbing type.
	// */
	//public String getWillClimbSport()
	//{
	//	return this.getString(R.string.will_climb_sport);
	//}

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
