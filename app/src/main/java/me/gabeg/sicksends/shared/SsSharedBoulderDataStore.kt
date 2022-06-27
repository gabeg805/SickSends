package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.gabeg.sicksends.R

/**
 * Save the data store to the context.
 */
val Context.boulderDataStore : DataStore<Preferences> by preferencesDataStore(name = "boulder")

/**
 * Boulder climbing data store.
 */
class SsSharedBoulderDataStore(context : Context)
	: SsSharedBaseClimbingDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.boulderDataStore

	/**
	 * Get all the grading systems for bouldering.
	 *
	 * @return All the grading systems for bouldering.
	 */
	@Composable
	override fun getAllGradingSystems() : List<String>
	{
		return getAllBoulderGradingSystems()
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	override fun getClimbName() : String
	{
		return stringResource(R.string.boulder)
	}

}

/**
 * Hilt module to provide an instance of a boulder data store.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsSharedBoulderDataStoreModule
{

	/**
	 * Provide an instance of a boulder data store.
	 */
	@Provides
	fun provideDataStore(@ApplicationContext context : Context) : SsSharedBoulderDataStore
	{
		return SsSharedBoulderDataStore(context)
	}

}
