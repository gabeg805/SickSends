package me.gabeg.sicksends.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.db.boulder.SsBoulderDao
import me.gabeg.sicksends.db.boulder.SsBoulderProblem
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.type.SsRouteFeatureType
import me.gabeg.sicksends.db.sport.SsSportDao
import me.gabeg.sicksends.db.sport.SsSportProblem
import me.gabeg.sicksends.db.toprope.SsTopRopeDao
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.db.trad.SsTradDao
import me.gabeg.sicksends.db.trad.SsTradProblem
import javax.inject.Singleton

/**
 * Database that can be used to access stored climbing information.
 */
@Database(
	entities = [SsBoulderProblem::class, SsSportProblem::class, SsTopRopeProblem::class, SsTradProblem::class],
	version = 1)
abstract class SsProblemDatabase : RoomDatabase()
{

	/**
	 * Store bouldering problems.
	 */
	abstract fun boulderDao() : SsBoulderDao

	/**
	 * Store sport problems.
	 */
	abstract fun sportDao() : SsSportDao

	/**
	 * Store top rope problems.
	 */
	abstract fun topRopeDao() : SsTopRopeDao

	/**
	 * Store trad problems.
	 */
	abstract fun tradDao() : SsTradDao

	/**
	 * Companion object.
	 */
	companion object
	{

		/**
		 * Tag for the database.
		 */
		const val TAG = "SsProblemDatabase"

		/**
		 * Name of the database.
		 */
		const val DB_NAME = "SsClimbingProblems.db"

		/**
		 * Singleton instance of the database.
		 */
		@Volatile
		private var sInstance: SsProblemDatabase? = null

		/**
		 * Create a static instance of the database.
		 *
		 * @param  context  Application context.
		 *
		 * @return A static instance of the database.
		 */
		fun getInstance(context : Context,
			coroutineScope : CoroutineScope? = null) : SsProblemDatabase
		{
			println("Getting instance of the database! " + sInstance != null)
			// If the instance is not null, return it. Otherwise create it
			return sInstance ?: synchronized(this)
			{
				println("Creating the instance!")
				val instance = Room.databaseBuilder(
						context.applicationContext,
						SsProblemDatabase::class.java,
						DB_NAME)
					.addCallback(TestDatabaseCallback(coroutineScope))
					.build()

				// Set the instance
				println("Setting the instance! " + instance != null)
				sInstance = instance

				// Return the instance. For some reason this is considered
				// non-null but the "sInstance" variable is not
				instance
			}
		}

		/**
		 * Callback for populating the database, for testing purposes.
		 */
		class TestDatabaseCallback(private val coroutineScope : CoroutineScope?)
			: RoomDatabase.Callback()
		{

			override fun onCreate(db: SupportSQLiteDatabase)
			{
				super.onCreate(db)

				println("Test database callback onCreate()!")
				//PopulateDbAsync(sInstance).execute();
				sInstance?.let { database ->
					coroutineScope?.launch {
						populateDatabase(database.boulderDao())
					} ?: null
				}
			}

			override fun onOpen(db: SupportSQLiteDatabase)
			{
				super.onOpen(db)

				println("Test database callback ON OPEN()!")
				//sInstance?.let { database ->
			//		coroutineScope?.launch {
			//			populateDatabase(database.boulderDao())
			//		} ?: null
			//	}
			}

			suspend fun populateDatabase(boulderDao: SsBoulderDao)
			{
				println("Populating/Creating the database!")
				var boulders : MutableList<SsBoulderProblem> = mutableListOf()
				var timestamp : Long = System.currentTimeMillis() / 1000;

				for (i in 0..29)
				{
					var p = SsBoulderProblem()

					p.timestamp = timestamp - 86400*(i % 5)
					p.holdType = SsHoldType.CRIMP.value or SsHoldType.PINCH.value or SsHoldType.POCKET.value
					p.routeFeatureType = SsRouteFeatureType.ARETE.value or SsRouteFeatureType.OVERHANG.value
					p.climbingTechniqueType = SsClimbingTechniqueType.KNEE_BAR.value or SsClimbingTechniqueType.SMEARING.value
					p.numAttempt = i.toLong()
					p.isFlash = false
					p.isOutdoor = false

					if (i == 0) {
						p.name = ""
						p.grade = "V0"
						p.locationName = ""
						p.howDidItFeelScale = 0
					} else if (i == 1) {
						p.name = ""
						p.grade = "V0"
						p.locationName = ""
						p.howDidItFeelScale = 1
					} else if (i == 2) {
						p.name = ""
						p.grade = "V0"
						p.locationName = ""
						p.howDidItFeelScale = 2
					} else if (i == 3) {
						p.name = "The Flying Salmon"
						p.grade = "V2"
						p.locationName = ""
						p.howDidItFeelScale = 0
					} else if (i == 4) {
						p.name = "The Flying Salmon"
						p.grade = "V2"
						p.locationName = ""
						p.howDidItFeelScale = 4
					} else if (i == 5) {
						p.name = "The Flying Salmon"
						p.grade = "V2"
						p.locationName = ""
						p.howDidItFeelScale = 5
					} else if (i == 6) {
						p.name = "The Flying Salmon"
						p.grade = "V2"
						p.locationName = "Wide Boyz"
						p.howDidItFeelScale = 0
					} else if (i == 7) {
						p.name = "The Flying Salmon"
						p.grade = "V4"
						p.locationName = "Wide Boyz"
						p.howDidItFeelScale = 0
					} else if (i == 8) {
						p.name = "The Flying Salmon"
						p.grade = "V4"
						p.locationName = "Wide Boyz"
						p.howDidItFeelScale = 1
					} else if (i == 9) {
						p.name = "The Flying Salmon"
						p.grade = "V4"
						p.locationName = "Wide Boyz"
						p.howDidItFeelScale = 2
					} else if (i == 10) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 11) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 12) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 13) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 14) {
						p.name = ""
						p.grade = "V10"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 1
					} else if (i == 15) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 2
					} else if (i == 16) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 4
					} else if (i == 17) {
						p.name = ""
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 5
					} else if (i == 18) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 19) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 20) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 21) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 22) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 23) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 24) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 25) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 26) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 1
					} else if (i == 27) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 2
					} else if (i == 28) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 4
					} else if (i == 29) {
						p.name = "Silence in the Night"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 4
					}

					boulderDao.insert(p);
				}

			}

		}

	}

}

/**
 * Hilt module to provide attributes from the problem database class.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsProblemDatabaseModule
{

	/**
	 * Provide the Boulder DAO.
	 */
	@Provides
	fun provideBoulderDao(db : SsProblemDatabase) : SsBoulderDao
	{
		return db.boulderDao()
	}

	/**
	 * Provide the database object.
	 */
	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context : Context) : SsProblemDatabase
	{
		return SsProblemDatabase.getInstance(context)
	}

	/**
	 * Provide the Sport DAO.
	 */
	@Provides
	fun provideSportDao(db : SsProblemDatabase) : SsSportDao
	{
		return db.sportDao()
	}

	/**
	 * Provide the Top Rope DAO.
	 */
	@Provides
	fun provideTopRopeDao(db : SsProblemDatabase) : SsTopRopeDao
	{
		return db.topRopeDao()
	}

	/**
	 * Provide the Trad DAO.
	 */
	@Provides
	fun provideTradDao(db : SsProblemDatabase) : SsTradDao
	{
		return db.tradDao()
	}

}
