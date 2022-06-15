package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


/**
 * Base data store class for climbing.
 */
abstract class SsSharedBaseClimbingDataStore(context : Context)
	: SsSharedBaseDataStore(context)
{

	/**
	 * Key names.
	 */
	abstract val KEY_DEFAULT_GRADING_SYSTEM : String
	abstract val KEY_WILL_CLIMB : String
	abstract val KEY_WILL_GRADE_WITH : String

	/**
	 * Build the key that is used to determine if a boulder grade will be used
	 * or not.
	 */
	fun buildWillGradeWithKey(grade : String) : String
	{
		return KEY_WILL_GRADE_WITH + grade
	}

	/**
	 * Edit whether the user will boulder or not.
	 *
	 * @param willClimb Whether or not the user will climb this type of
	 *                  climb.
	 */
	suspend fun editWillClimb(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB, willClimb)
	}

	/**
	 * Edit whether the user will use a particular bouldering grade or not.
	 *
	 * @param  grade      Name of a grade.
	 * @param  willGrade  Flag indicating whether the user will use the grade or
	 *                    not.
	 */
	suspend fun editWillGradeWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeWithKey(grade)

		editBoolean(key, willGrade)
	}

	/**
	 * Get all the grading systems for a type of climbing.
	 *
	 * @return All the grading systems for a type of climbing.
	 */
	@Composable
	abstract fun getAllGradingSystems() : List<String>

	/**
	 * Get all the grading systems for a type of climbing that a user will use.
	 *
	 * @return All the grading systems for a type of climbing that a user will
	 *         use.
	 */
	@Composable
	fun getAllGradingSystemsWillUse() : List<String>
	{
		val allGradingSystems = getAllGradingSystems()
		var allGradingSystemsWillUse = mutableListOf<String>()

		// Iterate over each grading system
		for (system in allGradingSystems)
		{
			// Check if the grading system will be used
			val willUse by getWillGradeWith(system).asLiveData().observeAsState()

			// The grading system will be used. Add it to the list
			if (willUse == true)
			{
				allGradingSystemsWillUse.add(system)
			}
		}

		return allGradingSystemsWillUse
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	abstract fun getClimbName() : String

	/**
	 * Get the default grading system for a type of climbing.
	 *
	 * @return The default grading system for a type of climbing.
	 */
	@Composable
	fun getDefaultGradingSystem() : String
	{
		val allGradingSystems = getAllGradingSystems()
		val flow = getString(KEY_DEFAULT_GRADING_SYSTEM, allGradingSystems[2])
		var value: String

		runBlocking {
			value = flow.first()
		}

		return value
	}

	/**
	 * Get whether or not the user will boulder.
	 *
	 * @return True if the user will boulder, and False otherwise.
	 */
	@Composable
	fun getWillClimb() : Boolean
	{
		val flow = getWillClimbFlow()
		val value by flow.asLiveData().observeAsState()

		return value ?: false
	}

	/**
	 * Get the flow of whether or not the user will boulder.
	 *
	 * @return Flow of if the user will boulder.
	 */
	fun getWillClimbFlow() : Flow<Boolean>
	{
		return getBoolean(KEY_WILL_CLIMB)
	}

	/**
	 * Get whether the user will use a particular bouldering grade or not.
	 *
	 * @param  grade  Name of a grade.
	 *
	 * @return True if the user will use a particular bouldering grade, and
	 *     False otherwise.
	 */
	fun getWillGradeWith(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeWithKey(grade)

		return getBoolean(key)
	}

}
