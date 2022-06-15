package me.gabeg.sicksends.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import me.gabeg.sicksends.R
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import java.util.*

/**
 * Get a list of all the bouldering grades for the Fontainebleau grading system.
 *
 * @return A list of all the bouldering grades for the Fontainebleau grading system.
 */
@Composable
fun getAllBoulderGradesFont() : List<String>
{
	return stringArrayResource(R.array.grades_boulder_font).toList()
}

/**
 * Get a list of all the boulder grades for a given grading system.
 *
 * @return A list of all the boulder grades for a given grading system.
 */
@Composable
fun getAllBoulderGradesForGradingSystem(gradingSystem : String) : List<String>
{
	return when (gradingSystem)
	{

		// Font
		getBoulderGradingSystemFont() -> getAllBoulderGradesFont()

		// UK
		getBoulderGradingSystemUk() -> getAllBoulderGradesUk()

		// V scale
		getBoulderGradingSystemVscale() -> getAllBoulderGradesVscale()

		// Unknown
		else -> listOf()
	}
}

/**
 * Get a list of all the bouldering grades for the UK grading system.
 *
 * @return A list of all the bouldering grades for the UK grading system.
 */
@Composable
fun getAllBoulderGradesUk() : List<String>
{
	return stringArrayResource(R.array.grades_boulder_uk).toList()
}

/**
 * Get a list of all the bouldering grades for the V-scale grading system.
 *
 * @return A list of all the bouldering grades for the V-scale grading system.
 */
@Composable
fun getAllBoulderGradesVscale() : List<String>
{
	return stringArrayResource(R.array.grades_boulder_vscale).toList()
}

/**
 * Get the name of all bouldering grades.
 *
 * @return The name of all bouldering grades.
 */
@Composable
fun getAllBoulderGradingSystems() : List<String>
{
	return listOf(
		getBoulderGradingSystemFont(),
		getBoulderGradingSystemUk(),
		getBoulderGradingSystemVscale())
}

/**
 * Get all the icons of climbs that a user can do.
 *
 * @return List of all the icons of climbs that a user can do.
 */
fun getAllClimbIcons() : List<Int>
{
	return listOf<Int>(
		R.mipmap.boulder,
		R.mipmap.sport,
		R.mipmap.top_rope,
		R.mipmap.trad)
}

/**
 * Get all the names of climbs that a user can do.
 *
 * @return List of all the names of climbs that a user can do.
 */
@Composable
fun getAllClimbNames() : List<String>
{
	return listOf<String>(
		stringResource(R.string.boulder),
		stringResource(R.string.sport),
		stringResource(R.string.top_rope),
		stringResource(R.string.trad))
}

/**
 * Get the name of all grading systems for ropes.
 *
 * @return The name of all grading systems for ropes.
 */
@Composable
fun getAllRopeGradingSystems() : List<String>
{
	return listOf(
		getGradeRopeAustralian(),
		getGradeRopeBrasilian(),
		getGradeRopeFinnish(),
		getGradeRopeFrench(),
		getGradeRopeNorway(),
		getGradeRopePoland(),
		getGradeRopeSaxon(),
		getGradeRopeUiaa(),
		getGradeRopeUsa())
}

/**
 * Get the name of all sport grading systems.
 *
 * @return The name of all sport grading systems.
 */
@Composable
fun getAllSportGradingSystems() : List<String>
{
	return getAllRopeGradingSystems()
}

/**
 * Get the name of all top rope grading systems.
 *
 * @return The name of all top rope grading systems.
 */
@Composable
fun getAllTopRopeGradingSystems() : List<String>
{
	return getAllRopeGradingSystems()
}

/**
 * Get the name of all trad grading systems.
 *
 * @return The name of all trad grading systems.
 */
@Composable
fun getAllTradGradingSystems() : List<String>
{
	var allGradingSystems = getAllRopeGradingSystems().toMutableList()

	allGradingSystems.add(2, getGradeTradBritish())

	return allGradingSystems
}

/**
 * Get the name of the app.
 *
 * @return The name of the app.
 */
@Composable
fun getAppName() : String
{
	return stringResource(R.string.app_name)
}

/**
 * Get the name of the Fontainebleau bouldering grade.
 *
 * @return The name of the Fontainebleau bouldering grade.
 */
@Composable
fun getBoulderGradingSystemFont() : String
{
	return stringResource(R.string.grade_boulder_font)
}

/**
 * Get the name of the UK UK bouldering grade.
 *
 * @return The name of the UK UK bouldering grade.
 */
@Composable
fun getBoulderGradingSystemUk() : String
{
	return stringResource(R.string.grade_boulder_uk)
}

/**
 * Get the name of the V-scale bouldering grade.
 *
 * @return The name of the V-scale bouldering grade.
 */
@Composable
fun getBoulderGradingSystemVscale() : String
{
	return stringResource(R.string.grade_boulder_vscale)
}

/**
 * Get an example of the grade scale for Fontainebleau bouldering.
 *
 * @return An example of the grade scale for Fontainebleau bouldering.
 */
@Composable
fun getExampleGradeBoulderFont() : String
{
	return stringResource(R.string.example_grade_boulder_font)
}

/**
 * Get an example of the grade scale for UK bouldering.
 *
 * @return An example of the grade scale for UK bouldering.
 */
@Composable
fun getExampleGradeBoulderUk() : String
{
	return stringResource(R.string.example_grade_boulder_uk)
}

/**
 * Get an example of the grade scale for V-scale bouldering.
 *
 * @return An example of the grade scale for V-scale bouldering.
 */
@Composable
fun getExampleGradeBoulderVscale() : String
{
	return stringResource(R.string.example_grade_boulder_vscale)
}

/**
 * Get an example of the grade scale for Australian rope climbing.
 *
 * @return An example of the grade scale for Australian rope climbing.
 */
@Composable
fun getExampleGradeRopeAustralian() : String
{
	return stringResource(R.string.example_grade_rope_aus)
}

/**
 * Get an example of the grade scale for Brasilian rope climbing.
 *
 * @return An example of the grade scale for Brasilian rope climbing.
 */
@Composable
fun getExampleGradeRopeBrasilian() : String
{
	return stringResource(R.string.example_grade_rope_bra)
}

/**
 * Get an example of the grade scale for Finnish rope climbing.
 *
 * @return An example of the grade scale for Finnish rope climbing.
 */
@Composable
fun getExampleGradeRopeFinnish() : String
{
	return stringResource(R.string.example_grade_rope_fin)
}

/**
 * Get an example of the grade scale for French rope climbing.
 *
 * @return An example of the grade scale for French rope climbing.
 */
@Composable
fun getExampleGradeRopeFrench() : String
{
	return stringResource(R.string.example_grade_rope_fre)
}

/**
 * Get an example of the grade scale for Norway rope climbing.
 *
 * @return An example of the grade scale for Norway rope climbing.
 */
@Composable
fun getExampleGradeRopeNorway() : String
{
	return stringResource(R.string.example_grade_rope_nor)
}

/**
 * Get an example of the grade scale for Poland rope climbing.
 *
 * @return An example of the grade scale for Poland rope climbing.
 */
@Composable
fun getExampleGradeRopePoland() : String
{
	return stringResource(R.string.example_grade_rope_pol)
}

/**
 * Get an example of the grade scale for Saxon rope climbing.
 *
 * @return An example of the grade scale for Saxon rope climbing.
 */
@Composable
fun getExampleGradeRopeSaxon() : String
{
	return stringResource(R.string.example_grade_rope_sax)
}

/**
 * Get an example of the grade scale for UIAA rope climbing.
 *
 * @return An example of the grade scale for UIAA rope climbing.
 */
@Composable
fun getExampleGradeRopeUiaa() : String
{
	return stringResource(R.string.example_grade_rope_uiaa)
}

/**
 * Get an example of the grade scale for USA rope climbing.
 *
 * @return An example of the grade scale for USA rope climbing.
 */
@Composable
fun getExampleGradeRopeUsa() : String
{
	return stringResource(R.string.example_grade_rope_usa)
}

/**
 * Get an example of the grade scale for British trad climbing.
 *
 * @return An example of the grade scale for British trad climbing.
 */
@Composable
fun getExampleGradeTradBritish() : String
{
	return stringResource(R.string.example_grade_trad_bri)
}

/**
 * Get an example of the given climbing grade.
 *
 * @return An example of the given climbing grade.
 */
@Composable
fun getExampleGrade(grade : String) : String
{
	return when (grade)
	{
		getBoulderGradingSystemFont()   -> getExampleGradeBoulderFont()
		getBoulderGradingSystemUk()     -> getExampleGradeBoulderUk()
		getBoulderGradingSystemVscale() -> getExampleGradeBoulderVscale()
		getGradeRopeAustralian()        -> getExampleGradeRopeAustralian()
		getGradeRopeBrasilian()         -> getExampleGradeRopeBrasilian()
		getGradeRopeFinnish()           -> getExampleGradeRopeFinnish()
		getGradeRopeFrench()            -> getExampleGradeRopeFrench()
		getGradeRopeNorway()            -> getExampleGradeRopeNorway()
		getGradeRopePoland()            -> getExampleGradeRopePoland()
		getGradeRopeSaxon()             -> getExampleGradeRopeSaxon()
		getGradeRopeUiaa()              -> getExampleGradeRopeUiaa()
		getGradeRopeUsa()               -> getExampleGradeRopeUsa()
		getGradeTradBritish()           -> getExampleGradeTradBritish()
		else                            -> ""
	}
}

/**
 * Get the name of grade for Australian rope climbing.
 *
 * @return Name of grade for Australian rope climbing.
 */
@Composable
fun getGradeRopeAustralian() : String
{
	return stringResource(R.string.grade_rope_aus)
}

/**
 * Get the name of grade for Brasilian rope climbing.
 *
 * @return Name of grade for Brasilian rope climbing.
 */
@Composable
fun getGradeRopeBrasilian() : String
{
	return stringResource(R.string.grade_rope_bra)
}

/**
 * Get the name of grade for Finnish rope climbing.
 *
 * @return Name of grade for Finnish rope climbing.
 */
@Composable
fun getGradeRopeFinnish() : String
{
	return stringResource(R.string.grade_rope_fin)
}

/**
 * Get the name of grade for French rope climbing.
 *
 * @return Name of grade for French rope climbing.
 */
@Composable
fun getGradeRopeFrench() : String
{
	return stringResource(R.string.grade_rope_fre)
}

/**
 * Get the name of grade for Norway rope climbing.
 *
 * @return Name of grade for Norway rope climbing.
 */
@Composable
fun getGradeRopeNorway() : String
{
	return stringResource(R.string.grade_rope_nor)
}

/**
 * Get the name of grade for Poland rope climbing.
 *
 * @return Name of grade for Poland rope climbing.
 */
@Composable
fun getGradeRopePoland() : String
{
	return stringResource(R.string.grade_rope_pol)
}

/**
 * Get the name of grade for Saxon rope climbing.
 *
 * @return Name of grade for Saxon rope climbing.
 */
@Composable
fun getGradeRopeSaxon() : String
{
	return stringResource(R.string.grade_rope_sax)
}

/**
 * Get the name of grade for UIAA rope climbing.
 *
 * @return Name of grade for UIAA rope climbing.
 */
@Composable
fun getGradeRopeUiaa() : String
{
	return stringResource(R.string.grade_rope_uiaa)
}

/**
 * Get the name of grade for USA rope climbing.
 *
 * @return Name of grade for USA rope climbing.
 */
@Composable
fun getGradeRopeUsa() : String
{
	return stringResource(R.string.grade_rope_usa)
}

/**
 * Get the name of grade for British trad climbing.
 *
 * @return Name of grade for British trad climbing.
 */
@Composable
fun getGradeTradBritish() : String
{
	return stringResource(R.string.grade_trad_bri)
}

/**
 * Get the name for how a climb felt.
 *
 * @return The name for how a climb felt.
 */
fun getHowDidItFeelScaleName(value : Int?) : String
{
	return when(value)
	{
		SsHowDidItFeelType.VERY_EASY.value -> "Very Easy"
		SsHowDidItFeelType.EASY.value      -> "Easy"
		SsHowDidItFeelType.NORMAL.value    -> "Normal"
		SsHowDidItFeelType.HARD.value      -> "Hard"
		SsHowDidItFeelType.VERY_HARD.value -> "Very Hard"
		else                               -> ""
	}
}