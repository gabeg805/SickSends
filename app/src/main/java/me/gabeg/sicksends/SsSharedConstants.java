package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;

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
	 * @return An example of the grade scale for Fontainebleau bouldering.
	 */
	public String getExampleGradeBoulderingFont()
	{
		return this.getString(R.string.example_grade_bouldering_font);
	}

	/**
	 * @return An example of the grade scale for UK bouldering.
	 */
	public String getExampleGradeBoulderingUk()
	{
		return this.getString(R.string.example_grade_bouldering_uk);
	}

	/**
	 * @return An example of the grade scale for V-scale bouldering.
	 */
	public String getExampleGradeBoulderingVscale()
	{
		return this.getString(R.string.example_grade_bouldering_vscale);
	}

	/**
	 * @return An example of the grade scale for Australian rope climbing.
	 */
	public String getExampleGradeRopeAustralian()
	{
		return this.getString(R.string.example_grade_rope_aus);
	}

	/**
	 * @return An example of the grade scale for Brasilian rope climbing.
	 */
	public String getExampleGradeRopeBrasilian()
	{
		return this.getString(R.string.example_grade_rope_bra);
	}

	/**
	 * @return An example of the grade scale for Finnish rope climbing.
	 */
	public String getExampleGradeRopeFinnish()
	{
		return this.getString(R.string.example_grade_rope_fin);
	}

	/**
	 * @return An example of the grade scale for French rope climbing.
	 */
	public String getExampleGradeRopeFrench()
	{
		return this.getString(R.string.example_grade_rope_fre);
	}

	/**
	 * @return An example of the grade scale for Norway rope climbing.
	 */
	public String getExampleGradeRopeNorway()
	{
		return this.getString(R.string.example_grade_rope_nor);
	}

	/**
	 * @return An example of the grade scale for Poland rope climbing.
	 */
	public String getExampleGradeRopePoland()
	{
		return this.getString(R.string.example_grade_rope_pol);
	}

	/**
	 * @return An example of the grade scale for Saxon rope climbing.
	 */
	public String getExampleGradeRopeSaxon()
	{
		return this.getString(R.string.example_grade_rope_sax);
	}

	/**
	 * @return An example of the grade scale for UIAA rope climbing.
	 */
	public String getExampleGradeRopeUiaa()
	{
		return this.getString(R.string.example_grade_rope_uiaa);
	}

	/**
	 * @return An example of the grade scale for USA rope climbing.
	 */
	public String getExampleGradeRopeUsa()
	{
		return this.getString(R.string.example_grade_rope_usa);
	}

	/**
	 * @return An example of the grade scale for British trad climbing.
	 */
	public String getExampleGradeTradBritish()
	{
		return this.getString(R.string.example_grade_trad_bri);
	}

	/**
	 * @return Name of grade for Fontainebleau bouldering.
	 */
	public String getGradeBoulderingFont()
	{
		return this.getString(R.string.grade_bouldering_font);
	}

	/**
	 * @return Name of grade for UK bouldering.
	 */
	public String getGradeBoulderingUk()
	{
		return this.getString(R.string.grade_bouldering_uk);
	}

	/**
	 * @return Name of grade for V-scale bouldering.
	 */
	public String getGradeBoulderingVscale()
	{
		return this.getString(R.string.grade_bouldering_vscale);
	}

	/**
	 * @return Name of grade for Australian rope climbing.
	 */
	public String getGradeRopeAustralian()
	{
		return this.getString(R.string.grade_rope_aus);
	}

	/**
	 * @return Name of grade for Brasilian rope climbing.
	 */
	public String getGradeRopeBrasilian()
	{
		return this.getString(R.string.grade_rope_bra);
	}

	/**
	 * @return Name of grade for Finnish rope climbing.
	 */
	public String getGradeRopeFinnish()
	{
		return this.getString(R.string.grade_rope_fin);
	}

	/**
	 * @return Name of grade for French rope climbing.
	 */
	public String getGradeRopeFrench()
	{
		return this.getString(R.string.grade_rope_fre);
	}

	/**
	 * @return Name of grade for Norway rope climbing.
	 */
	public String getGradeRopeNorway()
	{
		return this.getString(R.string.grade_rope_nor);
	}

	/**
	 * @return Name of grade for Poland rope climbing.
	 */
	public String getGradeRopePoland()
	{
		return this.getString(R.string.grade_rope_pol);
	}

	/**
	 * @return Name of grade for Saxon rope climbing.
	 */
	public String getGradeRopeSaxon()
	{
		return this.getString(R.string.grade_rope_sax);
	}

	/**
	 * @return Name of grade for UIAA rope climbing.
	 */
	public String getGradeRopeUiaa()
	{
		return this.getString(R.string.grade_rope_uiaa);
	}

	/**
	 * @return Name of grade for USA rope climbing.
	 */
	public String getGradeRopeUsa()
	{
		return this.getString(R.string.grade_rope_usa);
	}

	/**
	 * @return Name of grade for British trad climbing.
	 */
	public String getGradeTradBritish()
	{
		return this.getString(R.string.grade_trad_bri);
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

}
