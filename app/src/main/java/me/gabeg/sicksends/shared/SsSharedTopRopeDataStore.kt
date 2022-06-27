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

/**
 * Hilt module to provide an instance of a top rope data store.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsSharedTopRopeDataStoreModule
{

	/**
	 * Provide an instance of a top rope data store.
	 */
	@Provides
	fun provideDataStore(@ApplicationContext context : Context) : SsSharedTopRopeDataStore
	{
		return SsSharedTopRopeDataStore(context)
	}

}
