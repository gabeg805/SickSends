package me.gabeg.sicksends.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import me.gabeg.sicksends.R

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
		getBoulderGradingSystemFont() ->
			getAllBoulderGradesFont()

		// UK
		getBoulderGradingSystemUk() ->
			getAllBoulderGradesUk()

		// V scale
		getBoulderGradingSystemVscale() ->
			getAllBoulderGradesVscale()

		// Unknown
		else ->
			listOf()
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
 * Get the name of the Fontainebleau bouldering grade.
 *
 * @return The name of the Fontainebleau bouldering grade.
 */
@Composable
fun getBoulderGradingSystemFont(): String
{
	return stringResource(R.string.grade_boulder_font)
}

/**
 * Get the name of the UK UK bouldering grade.
 *
 * @return The name of the UK UK bouldering grade.
 */
@Composable
fun getBoulderGradingSystemUk(): String
{
	return stringResource(R.string.grade_boulder_uk)
}

/**
 * Get the name of the V-scale bouldering grade.
 *
 * @return The name of the V-scale bouldering grade.
 */
@Composable
fun getBoulderGradingSystemVscale(): String
{
	return stringResource(R.string.grade_boulder_vscale)
}
