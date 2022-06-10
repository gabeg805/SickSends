package me.gabeg.sicksends.shared

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.gabeg.sicksends.R

val Context.boulderDataStore : DataStore<Preferences> by preferencesDataStore(name = "boulder")

class SsSharedBoulderDataStore(context : Context) : SsSharedBaseDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.boulderDataStore

	/**
	 * Key names.
	 */
	companion object
	{
		val KEY_DEFAULT_GRADING_SYSTEM = "key_default_grading_system"
		val KEY_WILL_BOULDER           = "key_will_boulder"
		val KEY_WILL_GRADE_WITH        = "key_will_grade_boulder_with_"
	}

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
	 * @param  willClimb  Whether or not the user will climb this type of
	 *                    climb.
	 */
	suspend fun editWillBoulder(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_BOULDER, willClimb)
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
	 * Get all the grading systems for bouldering that the user will use.
	 *
	 * @return All the grading systems for bouldering that the user will use.
	 */
	@Composable
	fun getAllGradingSystemsWillUse() : List<String>
	{
		val allGradingSystems = getAllBoulderGradingSystems()
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
	 * Get the default grading system for bouldering.
	 *
	 * @return The default grading system for bouldering.
	 */
	@Composable
	override fun getDefaultGradingSystem() : String
	{
		val allGradingSystems = getAllBoulderGradingSystems()
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
	fun getWillBoulder(): Boolean
	{
		val flow = getWillBoulderFlow()
		val value by flow.asLiveData().observeAsState()

		return value ?: false
	}

	/**
	 * Get the flow of whether or not the user will boulder.
	 *
	 * @return Flow of if the user will boulder.
	 */
	fun getWillBoulderFlow(): Flow<Boolean>
	{
		return getBoolean(KEY_WILL_BOULDER)
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
