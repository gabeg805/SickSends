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
val Context.sportDataStore : DataStore<Preferences> by preferencesDataStore(name = "sport")

/**
 * Sport climbing data store.
 */
class SsSharedSportDataStore(context : Context)
	: SsSharedBaseClimbingDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.sportDataStore

	/**
	 * Get all the grading systems for sport climbing.
	 *
	 * @return All the grading systems for sport climbing.
	 */
	@Composable
	override fun getAllGradingSystems() : List<String>
	{
		return getAllSportGradingSystems()
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	override fun getClimbName() : String
	{
		return stringResource(R.string.sport)
	}

}

/**
 * Hilt module to provide an instance of a sport data store.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsSharedSportDataStoreModule
{

	/**
	 * Provide an instance of a sport data store.
	 */
	@Provides
	fun provideDataStore(@ApplicationContext context : Context) : SsSharedSportDataStore
	{
		return SsSharedSportDataStore(context)
	}

}
