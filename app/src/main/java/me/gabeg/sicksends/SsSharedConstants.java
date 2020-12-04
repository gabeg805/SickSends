package me.gabeg.sicksends;

import android.content.Context;
import android.content.res.Resources;
import java.util.Arrays;
import java.util.List;

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
	 * @return All boulder grades.
	 */
	public List<String> getAllBoulderGrades()
	{
		String font = this.getGradeBoulderFont();
		String uk = this.getGradeBoulderUk();
		String vscale = this.getGradeBoulderVscale();

		return Arrays.asList(font, uk, vscale);
	}

	/**
	 * @return All rope grades.
	 */
	public List<String> getAllRopeGrades()
	{
		String aus = this.getGradeRopeAustralian();
		String bra = this.getGradeRopeBrasilian();
		String fin = this.getGradeRopeFinnish();
		String fre = this.getGradeRopeFrench();
		String nor = this.getGradeRopeNorway();
		String pol = this.getGradeRopePoland();
		String sax = this.getGradeRopeSaxon();
		String uiaa = this.getGradeRopeUiaa();
		String usa = this.getGradeRopeUsa();

		return Arrays.asList(aus, bra, fin, fre, nor, pol, sax, uiaa, usa);
	}

	/**
	 * @return The app name.
	 */
	public String getAppName()
	{
		return this.getString(R.string.app_name);
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
	public String getExampleGradeBoulderFont()
	{
		return this.getString(R.string.example_grade_boulder_font);
	}

	/**
	 * @return An example of the grade scale for UK bouldering.
	 */
	public String getExampleGradeBoulderUk()
	{
		return this.getString(R.string.example_grade_boulder_uk);
	}

	/**
	 * @return An example of the grade scale for V-scale bouldering.
	 */
	public String getExampleGradeBoulderVscale()
	{
		return this.getString(R.string.example_grade_boulder_vscale);
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
	public String getGradeBoulderFont()
	{
		return this.getString(R.string.grade_boulder_font);
	}

	/**
	 * @return Name of grade for UK bouldering.
	 */
	public String getGradeBoulderUk()
	{
		return this.getString(R.string.grade_boulder_uk);
	}

	/**
	 * @return Name of grade for V-scale bouldering.
	 */
	public String getGradeBoulderVscale()
	{
		return this.getString(R.string.grade_boulder_vscale);
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

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Font grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeBoulderFont(String name)
	{
		return this.getGradeBoulderFont().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the UK grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeBoulderUk(String name)
	{
		return this.getGradeBoulderUk().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the V scale grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeBoulderVscale(String name)
	{
		return this.getGradeBoulderVscale().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Australian grading system, and
	 *     False otherwise.
	 */
	public boolean isGradeRopeAustralian(String name)
	{
		return this.getGradeRopeAustralian().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Brasilian grading system, and
	 *     False otherwise.
	 */
	public boolean isGradeRopeBrasilian(String name)
	{
		return this.getGradeRopeBrasilian().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Finnish grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeFinnish(String name)
	{
		return this.getGradeRopeFinnish().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the French grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeFrench(String name)
	{
		return this.getGradeRopeFrench().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Norway grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeNorway(String name)
	{
		return this.getGradeRopeNorway().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Poland grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopePoland(String name)
	{
		return this.getGradeRopePoland().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the Saxon grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeSaxon(String name)
	{
		return this.getGradeRopeSaxon().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the UIAA grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeUiaa(String name)
	{
		return this.getGradeRopeUiaa().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the USA grading system, and False
	 *     otherwise.
	 */
	public boolean isGradeRopeUsa(String name)
	{
		return this.getGradeRopeUsa().equals(name);
	}

	/**
	 * @param  name  Name of a grading system.
	 *
	 * @return True if a name is the same as the British trad grading system, and
	 *     False otherwise.
	 */
	public boolean isGradeTradBritish(String name)
	{
		return this.getGradeTradBritish().equals(name);
	}

}
