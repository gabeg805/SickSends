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
val Context.tradDataStore : DataStore<Preferences> by preferencesDataStore(name = "trad")

/**
 * Trad climbing data store.
 */
class SsSharedTradDataStore(context : Context)
	: SsSharedBaseClimbingDataStore(context)
{

	/**
	 * Data store.
	 */
	override val dataStore = context.tradDataStore

	/**
	 * Get all the grading systems for trad climbing.
	 *
	 * @return All the grading systems for trad climbing.
	 */
	@Composable
	override fun getAllGradingSystems() : List<String>
	{
		return getAllTradGradingSystems()
	}

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	override fun getClimbName() : String
	{
		return stringResource(R.string.trad)
	}

}

/**
 * Hilt module to provide an instance of a trad data store.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsSharedTradDataStoreModule
{

	/**
	 * Provide an instance of a trad data store.
	 */
	@Provides
	fun provideDataStore(@ApplicationContext context : Context) : SsSharedTradDataStore
	{
		return SsSharedTradDataStore(context)
	}

}
