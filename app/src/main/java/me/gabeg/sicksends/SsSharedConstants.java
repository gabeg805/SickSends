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
	 * @return The example text for Fontainebleau bouldering.
	 */
	public String getExampleBoulderingFont()
	{
		return this.getString(R.string.bouldering_font_example);
	}

	/**
	 * @return The example text for UK bouldering.
	 */
	public String getExampleBoulderingUk()
	{
		return this.getString(R.string.bouldering_uk_example);
	}

	/**
	 * @return The example text for V-scale bouldering.
	 */
	public String getExampleBoulderingVscale()
	{
		return this.getString(R.string.bouldering_vscale_example);
	}

	/**
	 * @return The example text for Australian rope climbing.
	 */
	public String getExampleRopeAustralian()
	{
		return this.getString(R.string.rope_aus_example);
	}

	/**
	 * @return The example text for Brasilian rope climbing.
	 */
	public String getExampleRopeBrasilian()
	{
		return this.getString(R.string.rope_bra_example);
	}

	/**
	 * @return The example text for Finnish rope climbing.
	 */
	public String getExampleRopeFinnish()
	{
		return this.getString(R.string.rope_fin_example);
	}

	/**
	 * @return The example text for French rope climbing.
	 */
	public String getExampleRopeFrench()
	{
		return this.getString(R.string.rope_fre_example);
	}

	/**
	 * @return The example text for Norway rope climbing.
	 */
	public String getExampleRopeNorway()
	{
		return this.getString(R.string.rope_nor_example);
	}

	/**
	 * @return The example text for Poland rope climbing.
	 */
	public String getExampleRopePoland()
	{
		return this.getString(R.string.rope_pol_example);
	}

	/**
	 * @return The example text for Saxon rope climbing.
	 */
	public String getExampleRopeSaxon()
	{
		return this.getString(R.string.rope_sax_example);
	}

	/**
	 * @return The example text for UIAA rope climbing.
	 */
	public String getExampleRopeUiaa()
	{
		return this.getString(R.string.rope_uiaa_example);
	}

	/**
	 * @return The example text for rope climbing.
	 */
	public String getExampleRopeUsa()
	{
		return this.getString(R.string.rope_usa_example);
	}

	/**
	 * @return The example text for British trad climbing.
	 */
	public String getExampleTradBritish()
	{
		return this.getString(R.string.trad_bri_example);
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
