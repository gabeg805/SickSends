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
	private final SharedPreferences mSharedPreferences;

	/**
	 * Shared preference keys.
	 */
	private final SsSharedKeys mKeys;

	/**
	 * Default value to see if this is the app's first run after installing.
	 */
	private static final boolean DEFAULT_APP_FIRST_RUN = true;

	/**
	 * Default app rating counter.
	 */
	public static final int DEFAULT_RATE_MY_APP_COUNTER = 0;

	/**
	 * Default app rating counter limit.
	 */
	public static final int DEFAULT_RATE_MY_APP_LIMIT = 30;

	/**
	 * Counter amount indicating that the app has been rated.
	 */
	public static final int DEFAULT_RATE_MY_APP_RATED = -999;

	/**
	 * Default climbing type.
	 */
	public static final boolean DEFAULT_CLIMBING_TYPE = false;

	/**
	 */
	public SsSharedPreferences(Context context)
	{
		this.mSharedPreferences = PreferenceManager
			.getDefaultSharedPreferences(context);
		this.mKeys = new SsSharedKeys(context);
		this.mContext = context;
	}

	/**
	 * @see editAppFirstRun
	 */
	public void editAppFirstRun(boolean first)
	{
		this.editAppFirstRun(first, false);
	}

	/**
	 * Edit the app first run value.
	 */
	public void editAppFirstRun(boolean first, boolean commit)
	{
		String key = this.getKeys().getAppFirstRun();
		this.saveBoolean(key, first, commit);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingType(SsClimbing.Type type, boolean willClimb)
	{
		this.editClimbingType(type, willClimb, false);
	}

	/**
	 * Edit the climbing type to indicate if the user will climb it or not.
	 */
	public void editClimbingType(SsClimbing.Type type, boolean willClimb,
		boolean commit)
	{
		String key = this.getKeys().getClimbingType(type);
		this.saveBoolean(key, willClimb, commit);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeBouldering(boolean willClimb)
	{
		this.editClimbingTypeBouldering(willClimb, false);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeBouldering(boolean willClimb, boolean commit)
	{
		String key = this.getKeys().getClimbingTypeBouldering();
		this.saveBoolean(key, willClimb, commit);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeSport(boolean willClimb)
	{
		this.editClimbingTypeSport(willClimb, false);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeSport(boolean willClimb, boolean commit)
	{
		String key = this.getKeys().getClimbingTypeSport();
		this.saveBoolean(key, willClimb, commit);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeTopRope(boolean willClimb)
	{
		this.editClimbingTypeTopRope(willClimb, false);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeTopRope(boolean willClimb, boolean commit)
	{
		String key = this.getKeys().getClimbingTypeTopRope();
		this.saveBoolean(key, willClimb, commit);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeTrad(boolean willClimb)
	{
		this.editClimbingTypeTrad(willClimb, false);
	}

	/**
	 * @see editClimbingType
	 */
	public void editClimbingTypeTrad(boolean willClimb, boolean commit)
	{
		String key = this.getKeys().getClimbingTypeTrad();
		this.saveBoolean(key, willClimb, commit);
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
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_APP_FIRST_RUN);
	}

	/**
	 * @return Whether the user will climb the given climbing type.
	 */
	public boolean getClimbingType(SsClimbing.Type type)
	{
		String key = this.getKeys().getClimbingType(type);
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_CLIMBING_TYPE);
	}

	/**
	 * @return Whether the user will climb bouldering.
	 */
	public boolean getClimbingTypeBouldering()
	{
		String key = this.getKeys().getClimbingTypeBouldering();
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_CLIMBING_TYPE);
	}

	/**
	 * @return Whether the user will climb sport.
	 */
	public boolean getClimbingTypeSport()
	{
		String key = this.getKeys().getClimbingTypeSport();
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_CLIMBING_TYPE);
	}

	/**
	 * @return Whether the user will climb top rope.
	 */
	public boolean getClimbingTypeTopRope()
	{
		String key = this.getKeys().getClimbingTypeTopRope();
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_CLIMBING_TYPE);
	}

	/**
	 * @return Whether the user will climb trad.
	 */
	public boolean getClimbingTypeTrad()
	{
		String key = this.getKeys().getClimbingTypeTrad();
		return this.getSharedPreferences().getBoolean(key,
			DEFAULT_CLIMBING_TYPE);
	}

	/**
	 * @return The application context.
	 */
	public Context getContext()
	{
		return this.mContext;
	}

	/**
	 * @return An example of the given climbing grade.
	 */
	public String getGradeExample(Context context, String grade)
	{
		Resources res = context.getResources();

		if (res.getString(R.string.bouldering_font).equals(grade))
		{
			return res.getString(R.string.bouldering_font_example);
		}
		else if (res.getString(R.string.bouldering_uk).equals(grade))
		{
			return res.getString(R.string.bouldering_uk_example);
		}
		else if (res.getString(R.string.bouldering_vscale).equals(grade))
		{
			return res.getString(R.string.bouldering_vscale_example);
		}
		else if (res.getString(R.string.rope_aus).equals(grade))
		{
			return res.getString(R.string.rope_aus_example);
		}
		else if (res.getString(R.string.rope_bra).equals(grade))
		{
			return res.getString(R.string.rope_bra_example);
		}
		else if (res.getString(R.string.rope_fin).equals(grade))
		{
			return res.getString(R.string.rope_fin_example);
		}
		else if (res.getString(R.string.rope_fre).equals(grade))
		{
			return res.getString(R.string.rope_fre_example);
		}
		else if (res.getString(R.string.rope_nor).equals(grade))
		{
			return res.getString(R.string.rope_nor_example);
		}
		else if (res.getString(R.string.rope_pol).equals(grade))
		{
			return res.getString(R.string.rope_pol_example);
		}
		else if (res.getString(R.string.rope_sax).equals(grade))
		{
			return res.getString(R.string.rope_sax_example);
		}
		else if (res.getString(R.string.rope_uiaa).equals(grade))
		{
			return res.getString(R.string.rope_uiaa_example);
		}
		else if (res.getString(R.string.rope_usa).equals(grade))
		{
			return res.getString(R.string.rope_usa_example);
		}
		else if (res.getString(R.string.trad_bri).equals(grade))
		{
			return res.getString(R.string.trad_bri_example);
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
		return this.mSharedPreferences;
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

		return this.getSharedPreferences().getInt(key,
			DEFAULT_RATE_MY_APP_COUNTER);
	}

	/**
	 * @return The SharedPreferences object.
	 */
	public SharedPreferences getSharedPreferences()
	{
		return this.mSharedPreferences;
	}

	/**
	 * @see save
	 */
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
	 * @see saveBoolean
	 */
	public void saveBoolean(String key, boolean value)
	{
		this.saveBoolean(key, value, false);
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
	 * @see saveInt
	 */
	public void saveInt(String key, int value)
	{
		this.saveInt(key, value, false);
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
	 * @see saveString
	 */
	public void saveString(String key, String value)
	{
		this.saveString(key, value, false);
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
