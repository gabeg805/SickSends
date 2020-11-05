package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.view.View;
import androidx.preference.PreferenceManager;

/**
 * Container for the values of each preference.
 */
public class SsSharedPreferences
{

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
	 * Edit the flag indicating whether the user will boulder or not.
	 *
	 * @param  willClimb  Flag indicating whether the user will boulder or not.
	 */
	public void editWillClimbBouldering(boolean willClimb)
	{
		String key = this.getKeys().getWillClimbBouldering();
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
		Context context = this.getContext();
		Resources res = context.getResources();
		SsSharedConstants cons = this.getConstants();

		if (res.getString(R.string.bouldering_font).equals(grade))
		{
			return cons.getExampleBoulderingFont();
		}
		else if (res.getString(R.string.bouldering_uk).equals(grade))
		{
			return cons.getExampleBoulderingUk();
		}
		else if (res.getString(R.string.bouldering_vscale).equals(grade))
		{
			return cons.getExampleBoulderingVscale();
		}
		else if (res.getString(R.string.rope_aus).equals(grade))
		{
			return cons.getExampleRopeAustralian();
		}
		else if (res.getString(R.string.rope_bra).equals(grade))
		{
			return cons.getExampleRopeBrasilian();
		}
		else if (res.getString(R.string.rope_fin).equals(grade))
		{
			return cons.getExampleRopeFinnish();
		}
		else if (res.getString(R.string.rope_fre).equals(grade))
		{
			return cons.getExampleRopeFrench();
		}
		else if (res.getString(R.string.rope_nor).equals(grade))
		{
			return cons.getExampleRopeNorway();
		}
		else if (res.getString(R.string.rope_pol).equals(grade))
		{
			return cons.getExampleRopePoland();
		}
		else if (res.getString(R.string.rope_sax).equals(grade))
		{
			return cons.getExampleRopeSaxon();
		}
		else if (res.getString(R.string.rope_uiaa).equals(grade))
		{
			return cons.getExampleRopeUiaa();
		}
		else if (res.getString(R.string.rope_usa).equals(grade))
		{
			return cons.getExampleRopeUsa();
		}
		else if (res.getString(R.string.trad_bri).equals(grade))
		{
			return cons.getExampleTradBritish();
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
		return this.getWillClimbBouldering() || this.getWillClimbSport()
			|| this.getWillClimbTopRope() || this.getWillClimbTrad();
	}

	/**
	 * @return Whether the user will climb bouldering.
	 */
	public boolean getWillClimbBouldering()
	{
		String key = this.getKeys().getWillClimbBouldering();
		boolean value = this.getDefaults().getWillClimbType();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user will climb sport.
	 */
	public boolean getWillClimbSport()
	{
		String key = this.getKeys().getWillClimbSport();
		boolean value = this.getDefaults().getWillClimbType();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user will climb top rope.
	 */
	public boolean getWillClimbTopRope()
	{
		String key = this.getKeys().getWillClimbTopRope();
		boolean value = this.getDefaults().getWillClimbType();

		return this.getBoolean(key, value);
	}

	/**
	 * @return Whether the user will climb trad.
	 */
	public boolean getWillClimbTrad()
	{
		String key = this.getKeys().getWillClimbTrad();
		boolean value = this.getDefaults().getWillClimbType();

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

}
