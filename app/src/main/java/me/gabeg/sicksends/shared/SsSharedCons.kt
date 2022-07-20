package me.gabeg.sicksends.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import me.gabeg.sicksends.R
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.problem.type.SsWallFeatureType

/**
 * Convert the enumerated value of a climbing technique to its name.
 *
 * @return The name for a climbing technique.
 */
@Composable
fun climbingTechniqueToName(value : Long?) : String
{
	return when(value)
	{
		SsClimbingTechniqueType.ARM_BAR.value     -> stringResource(R.string.arm_bar)
		SsClimbingTechniqueType.BAT_HANG.value    -> stringResource(R.string.bat_hang)
		SsClimbingTechniqueType.BICYCLE.value     -> stringResource(R.string.bicycle)
		SsClimbingTechniqueType.CAMPUS.value      -> stringResource(R.string.campus)
		SsClimbingTechniqueType.DROP_KNEE.value   -> stringResource(R.string.drop_knee)
		SsClimbingTechniqueType.DYNO.value        -> stringResource(R.string.dyno)
		SsClimbingTechniqueType.FIGURE_FOUR.value -> stringResource(R.string.figure_four)
		SsClimbingTechniqueType.FINGER_JAM.value  -> stringResource(R.string.finger_jam)
		SsClimbingTechniqueType.FIST_JAM.value    -> stringResource(R.string.fist_jam)
		SsClimbingTechniqueType.GASTON.value      -> stringResource(R.string.gaston)
		SsClimbingTechniqueType.HAND_JAM.value    -> stringResource(R.string.hand_jam)
		SsClimbingTechniqueType.HEEL_HOOK.value   -> stringResource(R.string.heel_hook)
		SsClimbingTechniqueType.KNEE_BAR.value    -> stringResource(R.string.knee_bar)
		SsClimbingTechniqueType.LAYBACK.value     -> stringResource(R.string.layback)
		SsClimbingTechniqueType.MANTLE.value      -> stringResource(R.string.mantle)
		SsClimbingTechniqueType.ROSE_MOVE.value   -> stringResource(R.string.rose_move)
		SsClimbingTechniqueType.SMEAR.value       -> stringResource(R.string.smear)
		SsClimbingTechniqueType.STEM.value        -> stringResource(R.string.stem)
		SsClimbingTechniqueType.TOE_HOOK.value    -> stringResource(R.string.toe_hook)
		else                                      -> ""
	}
}

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
 * Get all names for each type of climbing technique.
 *
 * @return All names for each type of climbing technique.
 */
@Composable
fun getAllClimbingTechniqueNames() : List<String>
{
	val values = SsClimbingTechniqueType.values()
	val names = mutableListOf<String>()

	values.forEach {
		names.add(climbingTechniqueToName(it.value))
	}

	return names
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
 * Get all names for each type of hold.
 *
 * @return All names for each type of hold.
 */
@Composable
fun getAllHoldNames() : List<String>
{
	val values = SsHoldType.values()
	val names = mutableListOf<String>()

	values.forEach {
		names.add(holdToName(it.value))
	}

	return names
}

/**
 * Get all names for how a climb felt.
 *
 * @return All names for how a climb felt.
 */
@Composable
fun getAllHowDidItFeelNames() : List<String>
{
	val values = SsHowDidItFeelType.values()
	val names = mutableListOf<String>()

	values.forEach {
		names.add(howDidItFeelToName(it.value))
	}

	return names
}

/**
 * @return List of possible grades for Australian rope climbing.
 */
@Composable
fun getAllRopeGradesAustralian() : List<String>
{
	return stringArrayResource(R.array.grades_rope_aus).toList()
}

/**
 * @return List of possible grades for Brasilian rope climbing.
 */
@Composable
fun getAllRopeGradesBrasilian() : List<String>
{
	return stringArrayResource(R.array.grades_rope_bra).toList()
}

/**
 * @return List of possible grades for Finnish rope climbing.
 */
@Composable
fun getAllRopeGradesFinnish() : List<String>
{
	return stringArrayResource(R.array.grades_rope_fin).toList()
}

/**
 * @return List of possible grades for French rope climbing.
 */
@Composable
fun getAllRopeGradesFrench() : List<String>
{
	return stringArrayResource(R.array.grades_rope_fre).toList()
}

/**
 * @return List of possible grades for Norway rope climbing.
 */
@Composable
fun getAllRopeGradesNorway() : List<String>
{
	return stringArrayResource(R.array.grades_rope_nor).toList()
}

/**
 * @return List of possible grades for Poland rope climbing.
 */
@Composable
fun getAllRopeGradesPoland() : List<String>
{
	return stringArrayResource(R.array.grades_rope_pol).toList()
}

/**
 * @return List of possible grades for Saxon rope climbing.
 */
@Composable
fun getAllRopeGradesSaxon() : List<String>
{
	return stringArrayResource(R.array.grades_rope_sax).toList()
}

/**
 * @return List of possible grades for UIAA rope climbing.
 */
@Composable
fun getAllRopeGradesUiaa() : List<String>
{
	return stringArrayResource(R.array.grades_rope_uiaa).toList()
}

/**
 * @return List of possible grades for USA rope climbing.
 */
@Composable
fun getAllRopeGradesUsa() : List<String>
{
	return stringArrayResource(R.array.grades_rope_usa).toList()
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
		getRopeGradingSystemAustralian(),
		getRopeGradingSystemBrasilian(),
		getRopeGradingSystemFinnish(),
		getRopeGradingSystemFrench(),
		getRopeGradingSystemNorway(),
		getRopeGradingSystemPoland(),
		getRopeGradingSystemSaxon(),
		getRopeGradingSystemUiaa(),
		getRopeGradingSystemUsa())
}

/**
 * Get a list of all the rope grades for a given grading system.
 *
 * @return A list of all the rope grades for a given grading system.
 */
@Composable
fun getAllRopeGradesForGradingSystem(gradingSystem : String) : List<String>
{
	return when (gradingSystem)
	{

		// Australian
		getRopeGradingSystemAustralian() -> getAllRopeGradesAustralian()

		// Brasilian
		getRopeGradingSystemBrasilian() -> getAllRopeGradesBrasilian()

		// Finnish
		getRopeGradingSystemFinnish() -> getAllRopeGradesFinnish()

		// French
		getRopeGradingSystemFrench() -> getAllRopeGradesFrench()

		// Norway
		getRopeGradingSystemNorway() -> getAllRopeGradesNorway()

		// Poland
		getRopeGradingSystemPoland() -> getAllRopeGradesPoland()

		// Saxon
		getRopeGradingSystemSaxon() -> getAllRopeGradesSaxon()

		// UIAA
		getRopeGradingSystemUiaa() -> getAllRopeGradesUiaa()

		// USA
		getRopeGradingSystemUsa() -> getAllRopeGradesUsa()

		// Unknown
		else -> listOf()
	}
}

/**
 * Get a list of all the sport grades for a given grading system.
 *
 * @return A list of all the sport grades for a given grading system.
 */
@Composable
fun getAllSportGradesForGradingSystem(gradingSystem : String) : List<String>
{
	return getAllRopeGradesForGradingSystem(gradingSystem)
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
 * Get a list of all the top rope grades for a given grading system.
 *
 * @return A list of all the top rope grades for a given grading system.
 */
@Composable
fun getAllTopRopeGradesForGradingSystem(gradingSystem : String) : List<String>
{
	return getAllRopeGradesForGradingSystem(gradingSystem)
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
 * @return List of possible grades for British trad climbing.
 */
@Composable
fun getAllTradGradesBritish() : List<String>
{
	return stringArrayResource(R.array.grades_trad_bri).toList()
}

/**
 * Get a list of all the trad grades for a given grading system.
 *
 * @return A list of all the trad grades for a given grading system.
 */
@Composable
fun getAllTradGradesForGradingSystem(gradingSystem : String) : List<String>
{
	return when (gradingSystem)
	{
		// British
		getTradGradingSystemBritish() -> getAllTradGradesBritish()

		// Other rope grading system
		else -> getAllRopeGradesForGradingSystem(gradingSystem)
	}
}

/**
 * Get the name of all trad grading systems.
 *
 * @return The name of all trad grading systems.
 */
@Composable
fun getAllTradGradingSystems() : List<String>
{
	val allGradingSystems = getAllRopeGradingSystems().toMutableList()

	allGradingSystems.add(2, getTradGradingSystemBritish())

	return allGradingSystems
}

/**
 * Get all names for each type of wall feature.
 *
 * @return All names for each type of wall feature.
 */
@Composable
fun getAllWallFeatureNames() : List<String>
{
	val values = SsWallFeatureType.values()
	val names = mutableListOf<String>()

	values.forEach {
		names.add(wallFeatureToName(it.value))
	}

	return names
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
		getBoulderGradingSystemFont()    -> getExampleGradeBoulderFont()
		getBoulderGradingSystemUk()      -> getExampleGradeBoulderUk()
		getBoulderGradingSystemVscale()  -> getExampleGradeBoulderVscale()
		getRopeGradingSystemAustralian() -> getExampleGradeRopeAustralian()
		getRopeGradingSystemBrasilian()  -> getExampleGradeRopeBrasilian()
		getRopeGradingSystemFinnish()    -> getExampleGradeRopeFinnish()
		getRopeGradingSystemFrench()     -> getExampleGradeRopeFrench()
		getRopeGradingSystemNorway()     -> getExampleGradeRopeNorway()
		getRopeGradingSystemPoland()     -> getExampleGradeRopePoland()
		getRopeGradingSystemSaxon()      -> getExampleGradeRopeSaxon()
		getRopeGradingSystemUiaa()       -> getExampleGradeRopeUiaa()
		getRopeGradingSystemUsa()        -> getExampleGradeRopeUsa()
		getTradGradingSystemBritish()    -> getExampleGradeTradBritish()
		else                             -> ""
	}
}

/**
 * No.
 */
@Composable
fun getNo() : String
{
	return stringResource(R.string.no)
}

/**
 * Get the name of the grading system for Australian rope climbing.
 *
 * @return Name of the grading system for Australian rope climbing.
 */
@Composable
fun getRopeGradingSystemAustralian() : String
{
	return stringResource(R.string.grade_rope_aus)
}

/**
 * Get the name of the grading system for Brasilian rope climbing.
 *
 * @return Name of the grading system for Brasilian rope climbing.
 */
@Composable
fun getRopeGradingSystemBrasilian() : String
{
	return stringResource(R.string.grade_rope_bra)
}

/**
 * Get the name of the grading system for Finnish rope climbing.
 *
 * @return Name of the grading system for Finnish rope climbing.
 */
@Composable
fun getRopeGradingSystemFinnish() : String
{
	return stringResource(R.string.grade_rope_fin)
}

/**
 * Get the name of the grading system for French rope climbing.
 *
 * @return Name of the grading system for French rope climbing.
 */
@Composable
fun getRopeGradingSystemFrench() : String
{
	return stringResource(R.string.grade_rope_fre)
}

/**
 * Get the name of the grading system for Norway rope climbing.
 *
 * @return Name of the grading system for Norway rope climbing.
 */
@Composable
fun getRopeGradingSystemNorway() : String
{
	return stringResource(R.string.grade_rope_nor)
}

/**
 * Get the name of the grading system for Poland rope climbing.
 *
 * @return Name of the grading system for Poland rope climbing.
 */
@Composable
fun getRopeGradingSystemPoland() : String
{
	return stringResource(R.string.grade_rope_pol)
}

/**
 * Get the name of the grading system for Saxon rope climbing.
 *
 * @return Name of the grading system for Saxon rope climbing.
 */
@Composable
fun getRopeGradingSystemSaxon() : String
{
	return stringResource(R.string.grade_rope_sax)
}

/**
 * Get the name of the grading system for UIAA rope climbing.
 *
 * @return Name of the grading system for UIAA rope climbing.
 */
@Composable
fun getRopeGradingSystemUiaa() : String
{
	return stringResource(R.string.grade_rope_uiaa)
}

/**
 * Get the name of the grading system for USA rope climbing.
 *
 * @return Name of the grading system for USA rope climbing.
 */
@Composable
fun getRopeGradingSystemUsa() : String
{
	return stringResource(R.string.grade_rope_usa)
}

/**
 * Get the name of grade for British trad climbing.
 *
 * @return Name of grade for British trad climbing.
 */
@Composable
fun getTradGradingSystemBritish() : String
{
	return stringResource(R.string.grade_trad_bri)
}

/**
 * Yes.
 */
@Composable
fun getYes() : String
{
	return stringResource(R.string.yes)
}

/**
 * Yes or no, depending on the boolean.
 *
 * @param value Boolean value.
 */
@Composable
fun getYesOrNo(value : Boolean) : String
{
	return if (value) getYes() else getNo()
}

/**
 * Convert the enumerated value of a hold to its name.
 *
 * @return The name for a hold.
 */
@Composable
fun holdToName(value : Long?) : String
{
	return when(value)
	{
		SsHoldType.CRIMP.value      -> stringResource(R.string.crimp)
		SsHoldType.HORN.value       -> stringResource(R.string.horn)
		SsHoldType.JUG.value        -> stringResource(R.string.jug)
		SsHoldType.PINCH.value      -> stringResource(R.string.pinch)
		SsHoldType.POCKET.value     -> stringResource(R.string.pocket)
		SsHoldType.SLOPER.value     -> stringResource(R.string.sloper)
		SsHoldType.SIDE_PULL.value  -> stringResource(R.string.side_pull)
		SsHoldType.UNDERCLING.value -> stringResource(R.string.undercling)
		SsHoldType.VOLUME.value     -> stringResource(R.string.volume)
		else                        -> ""
	}
}

/**
 * Convert the enumerated value of how a climb felt to its name.
 *
 * @return The name for how a climb felt.
 */
@Composable
fun howDidItFeelToName(value : Int?) : String
{
	return when(value)
	{
		SsHowDidItFeelType.VERY_EASY.value -> stringResource(R.string.very_easy)
		SsHowDidItFeelType.EASY.value      -> stringResource(R.string.easy)
		SsHowDidItFeelType.NORMAL.value    -> stringResource(R.string.normal)
		SsHowDidItFeelType.HARD.value      -> stringResource(R.string.hard)
		SsHowDidItFeelType.VERY_HARD.value -> stringResource(R.string.very_hard)
		else                               -> ""
	}
}

/**
 * Convert the enumerated value of a wall feature to its name.
 *
 * @return The name for a wall feature.
 */
@Composable
fun wallFeatureToName(value : Long?) : String
{
	return when(value)
	{
		SsWallFeatureType.ARETE.value     -> stringResource(R.string.arete)
		SsWallFeatureType.CHIMNEY.value   -> stringResource(R.string.chimney)
		SsWallFeatureType.CRACK.value     -> stringResource(R.string.crack)
		SsWallFeatureType.DIHEDRAL.value  -> stringResource(R.string.dihedral)
		SsWallFeatureType.FACE.value      -> stringResource(R.string.face)
		SsWallFeatureType.HIGH_BALL.value -> stringResource(R.string.high_ball)
		SsWallFeatureType.OVERHANG.value  -> stringResource(R.string.overhang)
		SsWallFeatureType.ROOF.value      -> stringResource(R.string.roof)
		SsWallFeatureType.SLAB.value      -> stringResource(R.string.slab)
		SsWallFeatureType.TOP_OUT.value   -> stringResource(R.string.top_out)
		SsWallFeatureType.TRAVERSE.value  -> stringResource(R.string.traverse)
		else                              -> ""
	}
}
