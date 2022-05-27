package me.gabeg.sicksends.shared

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import me.gabeg.sicksends.R

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sick_sends")

class SsSharedDataStore(context : Context)
{

	// Data store
	private val dataStore = context.dataStore

	// List of names of all types of climbs a user can do
	//private var mAllClimbNames = listOf<String>()

	// Key names
	val KEY_IS_APP_FIRST_RUN         = "key_is_app_first_run"
	val KEY_WILL_CLIMB_BOULDER       = "key_will_climb_boulder"
	val KEY_WILL_CLIMB_SPORT         = "key_will_climb_sport"
	val KEY_WILL_CLIMB_TOP_ROPE      = "key_will_climb_top_rope"
	val KEY_WILL_CLIMB_TRAD          = "key_will_climb_trad"
	val KEY_WILL_GRADE_BOULDER_WITH  = "key_will_grade_boulder_with_"
	val KEY_WILL_GRADE_SPORT_WITH    = "key_will_grade_sport_with_"
	val KEY_WILL_GRADE_TOP_ROPE_WITH = "key_will_grade_top_rope_with_"
	val KEY_WILL_GRADE_TRAD_WITH     = "key_will_grade_trad_with_"

	//init
	//{
	//	setupAllClimbNames(context)
	//}

	/**
	 * Build the key that is used to determine if a boulder grade will be used
	 * or not.
	 */
	fun buildWillGradeBoulderWithKey(grade : String) : String
	{
		return KEY_WILL_GRADE_BOULDER_WITH+grade
	}

	/**
	 * Build the key that is used to determine if a sport grade will be used or
	 * not.
	 */
	fun buildWillGradeSportWithKey(grade : String) : String
	{
		return KEY_WILL_GRADE_SPORT_WITH+grade
	}

	/**
	 * Build the key that is used to determine if a top rope grade will be used
	 * or not.
	 */
	fun buildWillGradeTopRopeWithKey(grade : String) : String
	{
		return KEY_WILL_GRADE_TOP_ROPE_WITH+grade
	}

	/**
	 * Build the key that is used to determine if a trad grade will be used or
	 * not.
	 */
	fun buildWillGradeTradWithKey(grade : String) : String
	{
		return KEY_WILL_GRADE_TRAD_WITH+grade
	}

	/**
	 * Convert the name of a climb the user can do to an icon ID.
	 *
	 * @param  name  The name of a climb the user can do.
	 *
	 * @return The icon ID of a climb the user can do.
	 */
	@Composable
	fun climbNameToIcon(name : String) : Int
	{
		val possibleNames = getAllClimbNames()
		val possibleIcons = getAllClimbIcons()
		val index = possibleNames.indexOf(name)

		return if (index >= 0) possibleIcons[index] else -1
	}

	/**
	 * Edit a boolean from the preferences data store.
	 */
	suspend fun editBoolean(key : String, value : Boolean)
	{
		val prefKey = booleanPreferencesKey(key)

		dataStore.edit { preferences ->
			preferences[prefKey] = value
		}
	}

	/**
	 * Edit whether this is the app's first run or not.
	 *
	 * @param  isFirst  Whether this is the app's first run or not.
	 */
	suspend fun editIsAppFirstRun(isFirst: Boolean)
	{
		editBoolean(KEY_IS_APP_FIRST_RUN, isFirst)
	}

	/**
	 * Edit whether the user will boulder or not.
	 *
	 * @param  willClimb  Whether or not the user will climb this type of
	 *                    climb.
	 */
	suspend fun editWillClimbBoulder(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB_BOULDER, willClimb)
	}

	/**
	 * Edit whether the user will climb sport or not.
	 *
	 * @param  willClimb  Whether or not the user will climb this type of
	 *                    climb.
	 */
	suspend fun editWillClimbSport(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB_SPORT, willClimb)
	}

	/**
	 * Edit whether the user will climb top rope or not.
	 *
	 * @param  willClimb  Whether or not the user will climb this type of
	 *                    climb.
	 */
	suspend fun editWillClimbTopRope(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB_TOP_ROPE, willClimb)
	}

	/**
	 * Edit whether the user will climb trad or not.
	 *
	 * @param  willClimb  Whether or not the user will climb this type of
	 *                    climb.
	 */
	suspend fun editWillClimbTrad(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB_TRAD, willClimb)
	}

	/**
	 * Edit whether the user will use a particular bouldering grade or not.
	 *
	 * @param  grade      Name of a grade.
	 * @param  willGrade  Flag indicating whether the user will use the grade or
	 *                    not.
	 */
	suspend fun editWillGradeBoulderWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeBoulderWithKey(grade)

		editBoolean(key, willGrade)
	}

	/**
	 * Edit whether the user will use a particular sport grade or not.
	 *
	 * @param  grade      Name of a grade.
	 * @param  willGrade  Flag indicating whether the user will use the grade or
	 *                    not.
	 */
	suspend fun editWillGradeSportWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeSportWithKey(grade)

		editBoolean(key, willGrade)
	}

	/**
	 * Edit whether the user will use a particular top rope grade or not.
	 *
	 * @param  grade      Name of a grade.
	 * @param  willGrade  Flag indicating whether the user will use the grade or
	 *                    not.
	 */
	suspend fun editWillGradeTopRopeWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeTopRopeWithKey(grade)

		editBoolean(key, willGrade)
	}

	/**
	 * Edit whether the user will use a particular trad grade or not.
	 *
	 * @param  grade      Name of a grade.
	 * @param  willGrade  Flag indicating whether the user will use the grade or
	 *                    not.
	 */
	suspend fun editWillGradeTradWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeTradWithKey(grade)

		editBoolean(key, willGrade)
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
	 * Get all the icons of the climbs a user will do. This can be Boulder,
	 * Sport, Top Rope, and/or Trad, depending on what the user has indicated.
	 *
	 * @return List of all the icons of climbs a user will do.
	 */
	suspend fun getAllClimbIconsWillClimb() : List<Int>
	{
		val possibleIcons = getAllClimbIcons()
		val willClimbFlows = getAllWillClimb()
		val allIcons = mutableListOf<Int>()

		// Iterate over both lists
		possibleIcons.zip(willClimbFlows).forEach { (icon, flow) ->

			// Get whether the type of climb will be climbed or not
			var willClimb = flow.first()

			// If the type of climb will be climbed, add it to the list
			if (willClimb)
			{
				allIcons.add(icon)
			}
		}

		return allIcons
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
	 * Get all the names of the climbs a user will do. This can be Boulder,
	 * Sport, Top Rope, and/or Trad, depending on what the user has indicated.
	 *
	 * @return List of all the names of climbs a user will do.
	 */
	//suspend fun getAllClimbNamesWillClimb() : List<String>
	//{
	//	val possibleNames = getAllClimbNames()
	//	val willClimbFlows = getAllWillClimb()
	//	val allNames = mutableListOf<String>()

	//	// Iterate over both lists
	//	possibleNames.zip(willClimbFlows).forEach { (name, flow) ->

	//		// Get whether the type of climb will be climbed or not
	//		var willClimb = flow.first()
	//		Log.i("SsSharedDataStore", name + "? " + willClimb)

	//		// If the type of climb will be climbed, add it to the list
	//		if (willClimb)
	//		{
	//			allNames.add(name)
	//		}
	//	}

	//	return allNames
	//}

	/**
	 * Get all the icons of the items the user can navigate to.
	 *
	 * @return All the icons of the items the user can navigate to.
	 */
	fun getAllNavigationIcons() : List<Int>
	{
		val icons = getAllClimbIcons().toMutableList()

		icons.add(0, R.mipmap.home)

		return icons.toList()
	}

	/**
	 * Get all the names of the items the user can navigate to.
	 *
	 * @return All the names of the items the user can navigate to.
	 */
	@Composable
	fun getAllNavigationNames() : List<String>
	{
		val names = getAllClimbNames().toMutableList()

		names.add(0, stringResource(R.string.home))

		return names.toList()
	}

	/**
	 * Get whether or not the user will climb a type of climb.  Do this for all
	 * climbs.  The returned list will be in the following order:
	 *
	 * Boulder, Sport, Top Rope, Trad
	 *
	 * @return List of whether or not the user will climb a type of climb, in
	 *     order of: Boulder, Sport, Top Rope, Trad.
	 */
	fun getAllWillClimb() : List<Flow<Boolean>>
	{
		return listOf(getWillClimbBoulder(), getWillClimbSport(),
			getWillClimbTopRope(), getWillClimbTrad())
	}

	/**
	 * Get whether this is the app's first run or not.
	 *
	 * @return True if it is the app's first run, and False otherwise.
	 */
	fun getAppFirstRun(): Flow<Boolean>
	{
		return getBoolean(KEY_IS_APP_FIRST_RUN, defaultValue = true)
	}

	/**
	 * Get a boolean from the preferences data store.
	 *
	 * @return A boolean.
	 */
	fun getBoolean(key : String, defaultValue : Boolean = false) : Flow<Boolean>
	{
		val prefKey = booleanPreferencesKey(key)

		return dataStore.data.map { preferences ->
			preferences[prefKey] ?: defaultValue
		}
	}

	/**
	 * Get whether or not the user will boulder.
	 *
	 * @return True if the user will boulder. and False otherwise.
	 */
	fun getWillClimbBoulder(): Flow<Boolean>
	{
		return getBoolean(KEY_WILL_CLIMB_BOULDER)
	}

	/**
	 * Get whether or not the user will climb sport.
	 *
	 * @return True if the user will climb sport, and False otherwise.
	 */
	fun getWillClimbSport(): Flow<Boolean>
	{
		return getBoolean(KEY_WILL_CLIMB_SPORT)
	}

	/**
	 * Get whether or not the user will climb top rope.
	 *
	 * @return True if the user will climb top rope, and False otherwise.
	 */
	fun getWillClimbTopRope(): Flow<Boolean>
	{
		return getBoolean(KEY_WILL_CLIMB_TOP_ROPE)
	}

	/**
	 * Get whether or not the user will climb trad.
	 *
	 * @return True if the user will climb trad, and False otherwise.
	 */
	fun getWillClimbTrad(): Flow<Boolean>
	{
		return getBoolean(KEY_WILL_CLIMB_TRAD)
	}

	/**
	 * Get whether the user will use a particular bouldering grade or not.
	 *
	 * @param  grade  Name of a grade.
	 *
	 * @return True if the user will use a particular bouldering grade, and
	 *     False otherwise.
	 */
	fun getWillGradeBoulderWith(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeBoulderWithKey(grade)

		return getBoolean(key)
	}

	/**
	 * Get whether the user will use a particular sport grade or not.
	 *
	 * @param  grade  Name of a grade.
	 *
	 * @return True if the user will use a particular sport grade, and False
	 *     otherwise.
	 */
	fun getWillGradeSportWith(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeSportWithKey(grade)

		return getBoolean(key)
	}

	/**
	 * Get whether the user will use a particular top rope grade or not.
	 *
	 * @param  grade  Name of a grade.
	 *
	 * @return True if the user will use a particular top rope grade, and False
	 *     otherwise.
	 */
	fun getWillGradeTopRopeWith(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeTopRopeWithKey(grade)

		return getBoolean(key)
	}

	/**
	 * Get whether the user will use a particular trad grade or not.
	 *
	 * @param  grade  Name of a grade.
	 *
	 * @return True if the user will use a particular top rope grade, and False
	 *     otherwise.
	 */
	fun getWillGradeTradWith(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeTradWithKey(grade)

		return getBoolean(key)
	}

	/**
	 * Setup all the climb names.
	 */
	//private fun setupAllClimbNames(context : Context)
	//{
	//	mAllClimbNames = listOf<String>(
	//		context.resources.getString(R.string.boulder),
	//		context.resources.getString(R.string.sport),
	//		context.resources.getString(R.string.top_rope),
	//		context.resources.getString(R.string.trad))
	//}

}
