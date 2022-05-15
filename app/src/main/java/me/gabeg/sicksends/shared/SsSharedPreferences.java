package me.gabeg.sicksends.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.preference.PreferenceManager;

import me.gabeg.sicksends.problem.gradingsystem.SsGradingSystem;
import me.gabeg.sicksends.boulder.SsBoulderGradingSystem;

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

		//for (String grade : allBoulders)
		//{
		//	if (this.useBoulderGrade(grade))
		//	{
		//		Log.i(TAG, "Use boulder grade : " + grade);
		//		useBoulders.add(grade);
		//	}
		//}

		return useBoulders;
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
	 * Get the grading system from its name.
	 *
	 * @param  name  Name of the grading system.
	 *
	 * @return Get the grading system from its name.
	 */
	public SsGradingSystem getGradingSystem(String name)
	{
		SsSharedConstants cons = this.getConstants();

		if (cons.isGradeBoulderFont(name))
		{
			return new SsBoulderGradingSystem.Font();
		}
		else if (cons.isGradeBoulderUk(name))
		{
			return new SsBoulderGradingSystem.Uk();
		}
		else if (cons.isGradeBoulderVscale(name))
		{
			return new SsBoulderGradingSystem.Vscale();
		}
		else
		{
			return null;
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
	 * @return A string value from the SharedPreferences instance.
	 */
	public String getString(String key, String defValue)
	{
		return this.getInstance().getString(key, defValue);
	}

	/**
	 * @return Whether the user has selected anything climb or not.
	 */
	//public boolean getWillClimb()
	//{
	//	return this.getWillClimbBoulder() || this.getWillClimbSport()
	//		|| this.getWillClimbTopRope() || this.getWillClimbTrad();
	//}

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

}
