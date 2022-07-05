package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import me.gabeg.sicksends.R

/**
 * Save the data store to the context.
 */
val Context.generalClimbingDataStore : DataStore<Preferences> by preferencesDataStore(name = "general_climbing")

/**
 * General climbing data store.
 */
class SsSharedGeneralClimbingDataStore(context : Context)
	: SsSharedBaseClimbingDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.generalClimbingDataStore

	/**
	 * Get all the grading systems for general climbing.
	 *
	 * @return All the grading systems for general climbing.
	 */
	@Composable
	override fun getAllGradingSystems() : List<String>
	{
		return listOf()
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	override fun getClimbName() : String
	{
		return stringResource(R.string.general)
	}

}
