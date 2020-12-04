package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;

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
	 * Edit the app rating counter.
	 */
	public void editRateMyAppCounter(int counter)
	{
		String key = this.getKeys().getRateMyAppCounter();
		this.saveInt(key, counter, false);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * bouldering grade or not.
	 *
	 * @param  grade  Name of a bouldering grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseBoulderGrade(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseBoulderGrade(grade);
		this.saveBoolean(key, willUse, false);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * sport grade or not.
	 *
	 * @param  grade  Name of a sport grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseSportGrade(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseSportGrade(grade);
		this.saveBoolean(key, willUse, false);
	}

	/**
	 * Edit the flag indicating whether the user will use a particular type of
	 * top rope grade or not.
	 *
	 * @param  grade  Name of a top rope grade.
	 * @param  willUse  Flag indicating whether the user will use the grade or
	 *     not.
	 */
	public void editUseTopRopeGrade(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseTopRopeGrade(grade);
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
	public void editUseTradGrade(String grade, boolean willUse)
	{
		String key = this.getKeys().getUseTradGrade(grade);
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
	 * Edit the flag indicating whether the user will climb sport or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will climb sport or
	 *     not.
	 */
	public void editWillClimbSport(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbSport();
		this.saveBoolean(key, willClimb, false);
	}

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
	 * @param  name  Name of a grading system.
	 *
	 * @return All possible boulder grades for a grading system.
	 */
	public List<String> getAllBoulderGrades(String name)
	{
		SsSharedConstants cons = this.getConstants();

		if (cons.isGradeBoulderFont(name))
		{
			return cons.getGradeListBoulderFont();
		}
		else if (cons.isGradeBoulderUk(name))
		{
			return cons.getGradeListBoulderUk();
		}
		else if (cons.isGradeBoulderVscale(name))
		{
			return cons.getGradeListBoulderVscale();
		}
		else
		{
			return null;
		}
	}

	/**
	 * @return All boulder grades that will be used by the user.
	 */
	public List<String> getAllUseBoulderGrades()
	{
		SsSharedConstants cons = this.getConstants();
		List<String> allBoulders = cons.getAllBoulderGrades();
		List<String> useBoulders = new ArrayList<>();

		for (String grade : allBoulders)
		{
			if (this.useBoulderGrade(grade))
			{
				Log.i(TAG, "Use boulder grade : " + grade);
				useBoulders.add(grade);
			}
		}

		return useBoulders;
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

		if (cons.isGradeBoulderFont(grade))
		{
			return cons.getExampleGradeBoulderFont();
		}
		else if (cons.isGradeBoulderUk(grade))
		{
			return cons.getExampleGradeBoulderUk();
		}
		else if (cons.isGradeBoulderVscale(grade))
		{
			return cons.getExampleGradeBoulderVscale();
		}
		else if (cons.isGradeRopeAustralian(grade))
		{
			return cons.getExampleGradeRopeAustralian();
		}
		else if (cons.isGradeRopeBrasilian(grade))
		{
			return cons.getExampleGradeRopeBrasilian();
		}
		else if (cons.isGradeRopeFinnish(grade))
		{
			return cons.getExampleGradeRopeFinnish();
		}
		else if (cons.isGradeRopeFrench(grade))
		{
			return cons.getExampleGradeRopeFrench();
		}
		else if (cons.isGradeRopeNorway(grade))
		{
			return cons.getExampleGradeRopeNorway();
		}
		else if (cons.isGradeRopePoland(grade))
		{
			return cons.getExampleGradeRopePoland();
		}
		else if (cons.isGradeRopeSaxon(grade))
		{
			return cons.getExampleGradeRopeSaxon();
		}
		else if (cons.isGradeRopeUiaa(grade))
		{
			return cons.getExampleGradeRopeUiaa();
		}
		else if (cons.isGradeRopeUsa(grade))
		{
			return cons.getExampleGradeRopeUsa();
		}
		else if (cons.isGradeTradBritish(grade))
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
	 * @return Whether the user has selected anything climb or not.
	 */
	public boolean getWillClimb()
	{
		return this.getWillClimbBoulder() || this.getWillClimbSport()
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
	 * @return Whether the user will climb sport.
	 */
	public boolean getWillClimbSport()
	{
		String key = this.getKeys().getWillClimbSport();
		boolean value = this.getDefaults().getWillClimb();

		return this.getBoolean(key, value);
	}

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
	 * Increment the rate my app counter.
	 */
	public void incrementRateMyApp()
	{
		int counter = this.getRateMyAppCounter();
		this.editRateMyAppCounter(counter+1);
	}

	/**
	 * @return True if app has reached the counter limit, and False otherwise.
	 */
	public boolean isRateMyAppLimit()
	{
		int counter = this.getRateMyAppCounter();
		int limit = this.getDefaults().getRateMyAppLimit();
		return counter == limit;
	}

	/**
	 * @return True if app has been rated, and False otherwise.
	 */
	public boolean isRateMyAppRated()
	{
		int counter = this.getRateMyAppCounter();
		int rated = this.getDefaults().getRateMyAppRated();
		return counter == rated;
	}

	/**
	 * Set the rate my app counter to the postpone value.
	 */
	public void postponeRateMyApp()
	{
		int postpone = -2 * this.getDefaults().getRateMyAppLimit();
		this.editRateMyAppCounter(postpone);
	}

	/**
	 * Set the rate my app counter to the rated value.
	 */
	public void ratedRateMyApp()
	{
		int rated = this.getDefaults().getRateMyAppRated();
		this.editRateMyAppCounter(rated);
	}

	/**
	 * Reset the rate my app counter.
	 */
	public void resetRateMyApp()
	{
		int reset = this.getDefaults().getRateMyAppCounter();
		this.editRateMyAppCounter(reset);
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
	 * @return True if will use a boulder grade, and False otherwise.
	 *
	 * @param  grade  Name of a bouldering grade.
	 */
	public boolean useBoulderGrade(String grade)
	{
		String key = this.getKeys().getUseBoulderGrade(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return True if will use a sport grade, and False otherwise.
	 *
	 * @param  grade  Name of a sport grade.
	 */
	public boolean useSportGrade(String grade)
	{
		String key = this.getKeys().getUseSportGrade(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return True if will use a top rope grade, and False otherwise.
	 *
	 * @param  grade  Name of a top rope grade.
	 */
	public boolean useTopRopeGrade(String grade)
	{
		String key = this.getKeys().getUseTopRopeGrade(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
	}

	/**
	 * @return True if will use a trad grade, and False otherwise.
	 *
	 * @param  grade  Name of a trad grade.
	 */
	public boolean useTradGrade(String grade)
	{
		String key = this.getKeys().getUseTradGrade(grade);
		boolean value = this.getDefaults().getUseGrade();

		return this.getBoolean(key, value);
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
			boolean use = this.useBoulderGrade(g);
			String k = keys.getUseBoulderGrade(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		Log.i(TAG, "Will climb sport?      " + this.getWillClimbSport());
		for (String g : rgrades)
		{
			boolean use = this.useSportGrade(g);
			String k = keys.getUseSportGrade(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		Log.i(TAG, "Will climb top rope?   " + this.getWillClimbTopRope());
		for (String g : rgrades)
		{
			boolean use = this.useTopRopeGrade(g);
			String k = keys.getUseTopRopeGrade(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}

		Log.i(TAG, "Will climb trad?       " + this.getWillClimbTrad());
		for (String g : tgrades)
		{
			boolean use = this.useTradGrade(g);
			String k = keys.getUseTradGrade(g);
			Log.i(TAG, "Use? " + use + "  | Grade : " + g + "  | Key : " + k);
		}
	}

}
