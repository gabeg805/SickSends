package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.gabeg.sicksends.R

/**
 * Save the data store to the context.
 */
val Context.topRopeDataStore : DataStore<Preferences> by preferencesDataStore(name = "top_rope")

/**
 * Top rope climbing data store.
 */
class SsSharedTopRopeDataStore(context : Context)
	: SsSharedBaseClimbingDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.topRopeDataStore

	/**
	 * Key names.
	 */
	override val KEY_DEFAULT_GRADING_SYSTEM = "key_default_grading_system"
	override val KEY_WILL_CLIMB             = "key_will_climb"
	override val KEY_WILL_GRADE_WITH        = "key_will_grade_with_"

	/**
	 * Get all the grading systems for top rope climbing.
	 *
	 * @return All the grading systems for top rope climbing.
	 */
	@Composable
	override fun getAllGradingSystems() : List<String>
	{
		return getAllTopRopeGradingSystems()
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	override fun getClimbName() : String
	{
		return stringResource(R.string.top_rope)
	}

}
