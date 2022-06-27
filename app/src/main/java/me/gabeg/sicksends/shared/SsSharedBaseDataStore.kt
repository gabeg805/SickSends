package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Base data store class.
 */
abstract class SsSharedBaseDataStore(context : Context)
{

	/**
	 * Data store.
	 */
	protected abstract val dataStore : DataStore<Preferences>

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
	 * Edit a string from the preferences data store.
	 */
	suspend fun editString(key : String, value : String)
	{
		val prefKey = stringPreferencesKey(key)

		dataStore.edit { preferences ->
			preferences[prefKey] = value
		}
	}

	/**
	 * Get a boolean from a flow.
	 *
	 * @return A boolean.
	 */
	suspend fun getBoolean(flow : Flow<Boolean>) : Boolean
	{
		return flow.first()
	}

	/**
	 * Get a boolean flow from the data store.
	 *
	 * @return A boolean flow.
	 */
	fun getBooleanFlow(key : String, defaultValue : Boolean = false) : Flow<Boolean>
	{
		val prefKey = booleanPreferencesKey(key)

		return dataStore.data.map { preferences ->
			preferences[prefKey] ?: defaultValue
		}
	}

	/**
	 * Get a string from the preferences data store.
	 *
	 * @return A string.
	 */
	suspend fun getString(flow : Flow<String>) : String
	{
		return flow.first()
	}

	/**
	 * Get a string from the preferences data store.
	 *
	 * @return A string flow.
	 */
	fun getStringFlow(key : String, defaultValue : String = "") : Flow<String>
	{
		val prefKey = stringPreferencesKey(key)

		return dataStore.data.map { preferences ->
			preferences[prefKey] ?: defaultValue
		}
	}

	/**
	 * Observe a boolean from a flow.
	 *
	 * @return An observed boolean.
	 */
	@Composable
	fun observeBoolean(flow : Flow<Boolean>, defaultValue : Boolean = false) : Boolean
	{
		val value by flow.asLiveData().observeAsState(defaultValue)

		return value
	}

	/**
	 * Observe a string from a flow.
	 *
	 * @return A observed string.
	 */
	@Composable
	fun observeString(flow : Flow<String>, defaultValue : String = "") : String
	{
		val value by flow.asLiveData().observeAsState(defaultValue)

		return value
	}

}