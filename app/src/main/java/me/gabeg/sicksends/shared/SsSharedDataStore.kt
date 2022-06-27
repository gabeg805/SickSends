package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

/**
 * Save the data store to the context.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sick_sends")

/**
 * Shared data store.
 */
class SsSharedDataStore(context : Context) : SsSharedBaseDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.dataStore

	/**
	 * Key names.
 	 */
	val KEY_IS_APP_FIRST_RUN = "key_is_app_first_run"

	/**
	 * Edit whether this is the app's first run or not.
	 *
	 * @param  isFirst  Whether this is the app's first run or not.
	 */
	suspend fun editIsAppFirstRun(isFirst: Boolean)
	{
		editBoolean(KEY_IS_APP_FIRST_RUN, isFirst)
	}

	///**
	// * Get all the icons of the climbs a user will do. This can be Boulder,
	// * Sport, Top Rope, and/or Trad, depending on what the user has indicated.
	// *
	// * @return List of all the icons of climbs a user will do.
	// */
	//suspend fun getAllClimbIconsWillClimb() : List<Int>
	//{
	//	val possibleIcons = getAllClimbIcons()
	//	val willClimbFlows = getAllWillClimb()
	//	val allIcons = mutableListOf<Int>()

	//	// Iterate over both lists
	//	possibleIcons.zip(willClimbFlows).forEach { (icon, flow) ->

	//		// Get whether the type of climb will be climbed or not
	//		var willClimb = flow.first()

	//		// If the type of climb will be climbed, add it to the list
	//		if (willClimb)
	//		{
	//			allIcons.add(icon)
	//		}
	//	}

	//	return allIcons
	//}

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
	 * Get whether this is the app's first run or not.
	 *
	 * @return True if it is the app's first run, and False otherwise.
	 */
	fun getAppFirstRunFlow(): Flow<Boolean>
	{
		return getBooleanFlow(KEY_IS_APP_FIRST_RUN, defaultValue = true)
	}

	/**
	 * Get whether this is the app's first run or not.
	 *
	 * @return True if it is the app's first run, and False otherwise.
	 */
	@Composable
	fun observeAppFirstRun(): Boolean
	{
		val flow = getAppFirstRunFlow()

		return observeBoolean(flow, defaultValue = true)
	}

}
