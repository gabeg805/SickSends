package me.gabeg.sicksends.shared

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
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
	 * Get a string from the preferences data store.
	 *
	 * @return A string.
	 */
	fun getString(key : String, defaultValue : String = "") : Flow<String>
	{
		val prefKey = stringPreferencesKey(key)

		return dataStore.data.map { preferences ->
			preferences[prefKey] ?: defaultValue
		}
	}

}