package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import androidx.preference.PreferenceManager;

/**
 * Container for the values of each preference.
 */
public class SsSharedPreferences
{

	/**
	 * Log tag.
	 */
	private static final String TAG = "SsSharedPreferences";

	/**
	 * The context application.
	 */
	private final Context mContext;

	/**
	 * Shared preferences instance.
	 */
	private final SharedPreferences mInstance;

	/**
	 * Keys.
	 */
	private final SsSharedKeys mKeys;

	/**
	 * Defaults.
	 */
	private final SsSharedDefaults mDefaults;

	/**
	 * Constants.
	 */
	private final SsSharedConstants mConstants;

	/**
	 */
	public SsSharedPreferences(Context context)
	{
		this.mContext = context;
		this.mInstance = PreferenceManager.getDefaultSharedPreferences(context);
		this.mKeys = new SsSharedKeys(context);
		this.mDefaults = new SsSharedDefaults(context);
		this.mConstants = new SsSharedConstants(context);
	}

	/**
	 * Edit the flag indicating whether this is the app's first run or not.
	 *
	 * @param  isFirst  Flag indicating whether this is the app's first run or
	 *     not.
	 */
	public void editAppFirstRun(boolean isFirst)
	{
		String key = this.getKeys().getAppFirstRun();
		this.saveBoolean(key, isFirst, false);
	}

	/**
	 * @see editRateMyAppCounter
	 */
	public void editRateMyAppCounter(int counter)
	{
		this.editRateMyAppCounter(counter, false);
	}

	/**
	 * Edit the app rating counter.
	 */
	public void editRateMyAppCounter(int counter, boolean commit)
	{
		String key = this.getKeys().getRateMyAppCounter();
		this.saveInt(key, counter, commit);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * bouldering grade or not.
	 *
	 * @param  grade  Name of a bouldering grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseGradeBoulder(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseGradeBoulder(grade);
		this.saveBoolean(key, willUse, false);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * lead grade or not.
	 *
	 * @param  grade  Name of a lead grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseGradeLead (String grade, boolean willUse)
	{
		String key = this.getKeys().getUseGradeLead(grade);
		this.saveBoolean(key, willUse, false);
	}

	///**
	// * Edit the flag indicating whether the user will use a particular type of
	// * sport grade or not.
	// *
	// * @param  grade  Name of a sport grade.
	// * @param  willUse  Flag indicating whether the user will use the grade or
	// *     not.
	// */
	//public void editUseGradeSport(String grade, boolean willUse)
	//{
	//	String key = this.getKeys().getUseGradeSport(grade);
	//	this.saveBoolean(key, willUse, false);
	//}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * top rope grade or not.
	 *
	 * @param  grade  Name of a top rope grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseGradeTopRope(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseGradeTopRope(grade);
		this.saveBoolean(key, willUse, false);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * trad grade or not.
	 *
	 * @param  grade  Name of a trad grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseGradeTrad(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseGradeTrad(grade);
		this.saveBoolean(key, willUse, false);
	}

	/**
	 * Edit the flag indicating whether the user will boulder or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will boulder or not.
	 */
	public void editWillClimbBoulder(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbBoulder();
		this.saveBoolean(key, willClimb, false);
	}

	/**
	 * Edit the flag indicating whether the user will climb lead or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will climb lead or
	 *     not.
	 */
	public void editWillClimbLead(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbLead();
		this.saveBoolean(key, willClimb, false);
	}

	///**
	// * Edit the flag indicating whether the user will climb sport or not.
	// *
	// * @param  willClimb  Flag indicating whether the user will climb sport or
	// *     not.
	// */
	//public void editWillClimbSport(boolean willClimb)
	//{
	//	String key = this.getKeys().getWillClimbSport();
	//	this.saveBoolean(key, willClimb, false);
	//}

	/**
	 * Edit the flag indicating whether the user will top rope or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will top rope or not.
	 */
	public void editWillClimbTopRope(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbTopRope();
		this.saveBoolean(key, willClimb, false);
	}

	/**
	 * Edit the flag indicating whether the user will climb trad or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will climb trad or not.
	 */
	public void editWillClimbTrad(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbTrad();
		this.saveBoolean(key, willClimb, false);
	}

	/**
	 * @return The app's first run value.
	 */
	public boolean getAppFirstRun()
	{
		String key = this.getKeys().getAppFirstRun();
		boolean value = this.getDefaults().getAppFirstRun();

		return this.getBoolean(key, value);
	}

	/**
	 * @return A boolean value from the SharedPreferences instance.
	 */
	public boolean getBoolean(String key, boolean defValue)
	{
		return this.getInstance().getBoolean(key, defValue);
	}

	/**
	 * @return The shared constants.
	 */
	public SsSharedConstants getConstants()
	{
		return this.mConstants;
	}

	/**
	 * @return The application context.
	 */
	public Context getContext()
	{
		return this.mContext;
	}

	/**
	 * @return The shared defaults.
	 */
	public SsSharedDefaults getDefaults()
	{
		return this.mDefaults;
	}

	/**
	 * @return An example of the given climbing grade.
	 */
	public String getGradeExample(String grade)
	{
		SsSharedConstants cons = this.getConstants();

		if (cons.getGradeBoulderFont().equals(grade))
		{
			return cons.getExampleGradeBoulderFont();
		}
		else if (cons.getGradeBoulderUk().equals(grade))
		{
			return cons.getExampleGradeBoulderUk();
		}
		else if (cons.getGradeBoulderVscale().equals(grade))
		{
			return cons.getExampleGradeBoulderVscale();
		}
		else if (cons.getGradeRopeAustralian().equals(grade))
		{
			return cons.getExampleGradeRopeAustralian();
		}
		else if (cons.getGradeRopeBrasilian().equals(grade))
		{
			return cons.getExampleGradeRopeBrasilian();
		}
		else if (cons.getGradeRopeFinnish().equals(grade))
		{
			return cons.getExampleGradeRopeFinnish();
		}
		else if (cons.getGradeRopeFrench().equals(grade))
		{
			return cons.getExampleGradeRopeFrench();
		}
		else if (cons.getGradeRopeNorway().equals(grade))
		{
			return cons.getExampleGradeRopeNorway();
		}
		else if (cons.getGradeRopePoland().equals(grade))
		{
			return cons.getExampleGradeRopePoland();
		}
		else if (cons.getGradeRopeSaxon().equals(grade))
		{
			return cons.getExampleGradeRopeSaxon();
		}
		else if (cons.getGradeRopeUiaa().equals(grade))
		{
			return cons.getExampleGradeRopeUiaa();
		}
		else if (cons.getGradeRopeUsa().equals(grade))
		{
			return cons.getExampleGradeRopeUsa();
		}
		else if (cons.getGradeTradBritish().equals(grade))
		{
			return cons.getExampleGradeTradBritish();
		}
		else
		{
			return "";
		}
	}

	/**
	 * @return The SharedPreferences instance.
	 */
	public SharedPreferences getInstance()
	{
		return this.mInstance;
	}

	/**
	 * @return An integer value from the SharedPreferences instance.
	 */
	public int getInt(String key, int defValue)
	{
		return this.getInstance().getInt(key, defValue);
	}

	/**
	 * @return The preference keys.
	 */
	public SsSharedKeys getKeys()
	{
		return this.mKeys;
	}

	/**
	 * @return The app's rating counter.
	 */
	public int getRateMyAppCounter()
	{
		String key = this.getKeys().getRateMyAppCounter();
		int value = this.getDefaults().getRateMyAppCounter();

		return this.getInt(key, value);
	}

	/**
	 * @return A string value from the SharedPreferences instance.
	 */
	public String getString(String key, String defValue)
	{
		return this.getInstance().getString(key, defValue);
	}

	/**
	 * @return Whether a bouldering grad will be used or not.
	 *
	 * @param  grade  Name of a bouldering grade.
	 */
	public boolean getUseGradeBoulder(String grade)
	{
		String key = this.getKeys().getUseGradeBoulder(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether a lead grad will be used or not.
	 *
	 * @param  grade  Name of a lead grade.
	 */
	public boolean getUseGradeLead(String grade)
	{
		String key = this.getKeys().getUseGradeLead(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	///**
	// * @return Whether a sport grad will be used or not.
	// *
	// * @param  grade  Name of a sport grade.
	// */
	//public boolean getUseGradeSport(String grade)
	//{
	//	String key = this.getKeys().getUseGradeSport(grade);
	//	boolean value = this.getDefaults().getUseGrade();

	//	return this.getBoolean(key, value);
	//}

	/**
	 * @return Whether a top rope grad will be used or not.
	 *
	 * @param  grade  Name of a top rope grade.
	 */
	public boolean getUseGradeTopRope(String grade)
	{
		String key = this.getKeys().getUseGradeTopRope(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether a trad grad will be used or not.
	 *
	 * @param  grade  Name of a trad grade.
	 */
	public boolean getUseGradeTrad(String grade)
	{
		String key = this.getKeys().getUseGradeTrad(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user has selected anything climb or not.
	 */
	public boolean getWillClimb()
	{
		//return this.getWillClimbBoulder() || this.getWillClimbSport()
		return this.getWillClimbBoulder() || this.getWillClimbLead()
			|| this.getWillClimbTopRope() || this.getWillClimbTrad();
	}

	/**
	 * @return Whether the user will climb bouldering.
	 */
	public boolean getWillClimbBoulder()
	{
		String key = this.getKeys().getWillClimbBoulder();
		boolean value = this.getDefaults().getWillClimb();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user will climb lead.
	 */
	public boolean getWillClimbLead()
	{
		String key = this.getKeys().getWillClimbLead();
		boolean value = this.getDefaults().getWillClimb();

		return this.getBoolean(key, value);
	}

	///**
	// * @return Whether the user will climb sport.
	// */
	//public boolean getWillClimbSport()
	//{
	//	String key = this.getKeys().getWillClimbSport();
	//	boolean value = this.getDefaults().getWillClimb();

	//	return this.getBoolean(key, value);
	//}

	/**
	 * @return Whether the user will climb top rope.
	 */
	public boolean getWillClimbTopRope()
	{
		String key = this.getKeys().getWillClimbTopRope();
		boolean value = this.getDefaults().getWillClimb();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user will climb trad.
	 */
	public boolean getWillClimbTrad()
	{
		String key = this.getKeys().getWillClimbTrad();
		boolean value = this.getDefaults().getWillClimb();

		return this.getBoolean(key, value);
	}

	/**
	 * @see #save(SharedPreferences.Editor, boolean)
	 */
	@SuppressWarnings("unused")
	public void save(SharedPreferences.Editor editor)
	{
		this.save(editor, false);
	}

	/**
	 * Save the changes that were made to the shared preference.
	 */
	public void save(SharedPreferences.Editor editor, boolean commit)
	{
		if (commit)
		{
			editor.commit();
		}
		else
		{
			editor.apply();
		}
	}

	/**
	 * Save a boolean to the shared preference.
	 */
	public void saveBoolean(String key, boolean value, boolean commit)
	{
		SharedPreferences.Editor editor = this.getInstance().edit()
			.putBoolean(key, value);

		this.save(editor, commit);
	}

	/**
	 * Save an int to the shared preference.
	 */
	public void saveInt(String key, int value, boolean commit)
	{
		SharedPreferences.Editor editor = this.getInstance().edit()
			.putInt(key, value);

		this.save(editor, commit);
	}

	/**
	 * Save a string to the shared preference.
	 */
	public void saveString(String key, String value, boolean commit)
	{
		SharedPreferences.Editor editor = this.getInstance().edit()
			.putString(key, value);

		this.save(editor, commit);
	}

	/**
	 * Debug the shared preferences.
	 */
	public void debug()
	{
		SsSharedConstants cons = this.getConstants();
		SsSharedKeys keys = this.getKeys();

		String[] bgrades = {
			cons.getGradeBoulderFont(),
			cons.getGradeBoulderUk(),
			cons.getGradeBoulderVscale()};

		String[] rgrades = {
			cons.getGradeRopeAustralian(),
			cons.getGradeRopeBrasilian(),
			cons.getGradeRopeFinnish(),
			cons.getGradeRopeFrench(),
			cons.getGradeRopeNorway(),
			cons.getGradeRopePoland(),
			cons.getGradeRopeSaxon(),
			cons.getGradeRopeUiaa(),
			cons.getGradeRopeUsa()};
			
		String[] tgrades = {
			cons.getGradeRopeAustralian(),
			cons.getGradeRopeBrasilian(),
			cons.getGradeTradBritish(),
			cons.getGradeRopeFinnish(),
			cons.getGradeRopeFrench(),
			cons.getGradeRopeNorway(),
			cons.getGradeRopePoland(),
			cons.getGradeRopeSaxon(),
			cons.getGradeRopeUiaa(),
			cons.getGradeRopeUsa()};

		Log.i(TAG, "Will climb boulder? " + this.getWillClimbBoulder());
		for (String g : bgrades)
		{
			boolean use = this.getUseGradeBoulder(g);
			String k = keys.getUseGradeBoulder(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		Log.i(TAG, "Will climb lead?      " + this.getWillClimbLead());
		for (String g : rgrades)
		{
			boolean use = this.getUseGradeLead(g);
			String k = keys.getUseGradeLead(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		//Log.i(TAG, "Will climb sport?      " + this.getWillClimbSport());
		//for (String g : rgrades)
		//{
		//	boolean use = this.getUseGradeSport(g);
		//	String k = keys.getUseGradeSport(g);
		//	Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		//}

		Log.i(TAG, "Will climb top rope?   " + this.getWillClimbTopRope());
		for (String g : rgrades)
		{
			boolean use = this.getUseGradeTopRope(g);
			String k = keys.getUseGradeTopRope(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		Log.i(TAG, "Will climb trad?       " + this.getWillClimbTrad());
		for (String g : tgrades)
		{
			boolean use = this.getUseGradeTrad(g);
			String k = keys.getUseGradeTrad(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}
	}

}
