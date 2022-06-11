package me.gabeg.sicksends.shared;

import android.content.Context;
import android.content.res.Resources;
import java.util.Arrays;
import java.util.List;

import me.gabeg.sicksends.R;

/**
 * Default values that are defined in an XML file in the values/ directory.
 */
public class SsSharedConstants
	extends SsSharedResource
{

	/**
	 */
	public SsSharedConstants(Context context)
	{
		super(context);
	}

	/**
	 */
	public SsSharedConstants(Resources res)
	{
		super(res);
	}

	/**
	 * @return The error message to show when no climbing type has been selected.
	 */
	public String getErrorMessageNoClimbingTypeSelected()
	{
		return this.getString(R.string.error_message_no_climbing_type_selected);
	}

	/**
	 * @return List of possible grades for Fontainebleau bouldering.
	 */
	public List<String> getGradeListBoulderFont()
	{
		return this.getStringList(R.array.grades_boulder_font);
	}

	/**
	 * @return List of possible grades for UK bouldering.
	 */
	public List<String> getGradeListBoulderUk()
	{
		return this.getStringList(R.array.grades_boulder_uk);
	}

	/**
	 * @return List of possible grades for V-scale bouldering.
	 */
	public List<String> getGradeListBoulderVscale()
	{
		return this.getStringList(R.array.grades_boulder_vscale);
	}

	/**
	 * @return List of possible grades for Australian rope climbing.
	 */
	public List<String> getGradeListRopeAustralian()
	{
		return this.getStringList(R.array.grades_rope_aus);
	}

	/**
	 * @return List of possible grades for Brasilian rope climbing.
	 */
	public List<String> getGradeListRopeBrasilian()
	{
		return this.getStringList(R.array.grades_rope_bra);
	}

	/**
	 * @return List of possible grades for Finnish rope climbing.
	 */
	public List<String> getGradeListRopeFinnish()
	{
		return this.getStringList(R.array.grades_rope_fin);
	}

	/**
	 * @return List of possible grades for French rope climbing.
	 */
	public List<String> getGradeListRopeFrench()
	{
		return this.getStringList(R.array.grades_rope_fre);
	}

	/**
	 * @return List of possible grades for Norway rope climbing.
	 */
	public List<String> getGradeListRopeNorway()
	{
		return this.getStringList(R.array.grades_rope_nor);
	}

	/**
	 * @return List of possible grades for Poland rope climbing.
	 */
	public List<String> getGradeListRopePoland()
	{
		return this.getStringList(R.array.grades_rope_pol);
	}

	/**
	 * @return List of possible grades for Saxon rope climbing.
	 */
	public List<String> getGradeListRopeSaxon()
	{
		return this.getStringList(R.array.grades_rope_sax);
	}

	/**
	 * @return List of possible grades for UIAA rope climbing.
	 */
	public List<String> getGradeListRopeUiaa()
	{
		return this.getStringList(R.array.grades_rope_uiaa);
	}

	/**
	 * @return List of possible grades for USA rope climbing.
	 */
	public List<String> getGradeListRopeUsa()
	{
		return this.getStringList(R.array.grades_rope_usa);
	}

	/**
	 * @return List of possible grades for British trad climbing.
	 */
	public List<String> getGradeListTradBritish()
	{
		return this.getStringList(R.array.grades_trad_bri);
	}

	/**
	 * @return The string indicating a climb is Indoor.
	 */
	public String getIndoor()
	{
		return this.getString(R.string.indoor);
	}

	/**
	 * @return The text to indicate finished navigating.
	 */
	public String getNavigateFinish()
	{
		return this.getString(R.string.navigate_finish);
	}

	/**
	 * @return The text to indicate to navigate to the next item.
	 */
	public String getNavigateNext()
	{
		return this.getString(R.string.navigate_next);
	}

	/**
	 * @return The text to indicate to navigate to the previous item.
	 */
	public String getNavigatePrevious()
	{
		return this.getString(R.string.navigate_previous);
	}

	/**
	 * @return The string indicating a climb is Outdoor.
	 */
	public String getOutdoor()
	{
		return this.getString(R.string.outdoor);
	}

}
