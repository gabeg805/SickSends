package me.gabeg.sicksends.problem

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import me.gabeg.sicksends.boulder.SsBoulderProblem
import me.gabeg.sicksends.sport.SsSportProblem
import me.gabeg.sicksends.toprope.SsTopRopeProblem
import me.gabeg.sicksends.trad.SsTradProblem
import androidx.room.RoomDatabase
import me.gabeg.sicksends.boulder.SsBoulderDao
import me.gabeg.sicksends.sport.SsSportDao
import me.gabeg.sicksends.toprope.SsTopRopeDao
import me.gabeg.sicksends.trad.SsTradDao
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.problem.type.SsRouteFeatureType
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType

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
	abstract fun boulderDao(): SsBoulderDao

	/**
	 * Store sport problems.
	 */
	abstract fun sportDao(): SsSportDao

	/**
	 * Store top rope problems.
	 */
	abstract fun topRopeDao(): SsTopRopeDao

	/**
	 * Store trad problems.
	 */
	abstract fun tradDao(): SsTradDao

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
		fun getInstance(context : Context, coroutineScope : CoroutineScope): SsProblemDatabase
		{
			// If the instance is not null, return it. Otherwise create it
			return sInstance ?: synchronized(this)
			{
				val instance = Room.databaseBuilder(
						context.applicationContext,
						SsProblemDatabase::class.java,
						DB_NAME)
					.addCallback(TestDatabaseCallback(coroutineScope))
					.build()

				// Set the instance
				sInstance = instance

				// Return the instance. For some reason this is considered
				// non-null but the "sInstance" variable is not
				instance
			}
		}

		/**
		 * Callback for populating the database, for testing purposes.
		 */
		class TestDatabaseCallback(private val coroutineScope : CoroutineScope)
			: RoomDatabase.Callback()
		{

			override fun onCreate(db: SupportSQLiteDatabase)
			{
				super.onCreate(db)

				//PopulateDbAsync(sInstance).execute();
				sInstance?.let { database ->
					coroutineScope.launch {
						populateDatabase(database.boulderDao())
					}
				}
			}

			suspend fun populateDatabase(boulderDao: SsBoulderDao)
			{
				var boulders : MutableList<SsBoulderProblem> = mutableListOf()
				var timestamp : Long = System.currentTimeMillis() / 1000;

				boulders.add(SsBoulderProblem())
				boulders.add(SsBoulderProblem())
				boulders.add(SsBoulderProblem())

				boulders[0].timestamp = timestamp - 86400 * 1
				boulders[1].timestamp = timestamp - 86400 * 2
				boulders[2].timestamp = timestamp - 86400 * 3
				boulders[0].name = "Blue"
				boulders[1].name = "Yellow"
				boulders[2].name = "Orange"
				boulders[0].name = "V0"
				boulders[1].name = "V1"
				boulders[2].name = "V2"
				boulders[0].name = ""
				boulders[1].name = "V1"
				boulders[2].name = "V2"

				boulders[0].howDidItFeelScale = 3
				boulders[1].howDidItFeelScale = 3
				boulders[2].howDidItFeelScale = 3

				boulders[0].numAttempt = 1
				boulders[1].numAttempt = 1
				boulders[2].numAttempt = 1

				boulders[0].isProject = false
				boulders[1].isProject = false
				boulders[2].isProject = false

				boulders[0].isFlash = true
				boulders[1].isFlash = true
				boulders[2].isFlash = false

				boulders[0].isOutdoor = true
				boulders[1].isOutdoor = true
				boulders[2].isOutdoor = false

				boulders[0].holdType = SsHoldType.JUG.value
				boulders[1].holdType = SsHoldType.CRIMP.value or SsHoldType.PINCH.value or SsHoldType.POCKET.value
				boulders[2].holdType = SsHoldType.POCKET.value or SsHoldType.SLOPER.value

				boulders[0].routeFeatureType = SsRouteFeatureType.FACE.value
				boulders[1].routeFeatureType = SsRouteFeatureType.ARETE.value or SsRouteFeatureType.OVERHANG.value
				boulders[2].routeFeatureType = SsRouteFeatureType.SLAB.value or SsRouteFeatureType.TOP_OUT.value

				boulders[0].climbingTechniqueType = SsClimbingTechniqueType.CAMPUS.value
				boulders[1].climbingTechniqueType = SsClimbingTechniqueType.KNEE_BAR.value or SsClimbingTechniqueType.SMEARING.value
				boulders[2].climbingTechniqueType = SsClimbingTechniqueType.DROP_KNEE.value or SsClimbingTechniqueType.DYNO.value or SsClimbingTechniqueType.MANTLE.value

				Log.i(TAG, "Doing database work in the background!");

				for (i in 0 until boulders.size)
				{
					Log.i(TAG, "Adding a boulder to the database!");
					boulderDao.insert(boulders[i]);
				}

			}

		}

		/**
		 * Populate the database in the background.
		 */
		//class PopulateDbAsync :
		//	AsyncTask<Void, Void, Void>()
		//{

		//	lateinit var mBoulderDao : SsBoulderDao
		//	var boulders : MutableList<SsBoulderProblem> = mutableListOf()

		//	constructor(db : SsProblemDatabase?)
		//	{
		//		if (db != null)
		//		{
		//			mBoulderDao = db.boulderDao()!!
		//		}

		//		boulders.add(SsBoulderProblem())
		//		boulders.add(SsBoulderProblem())
		//		boulders.add(SsBoulderProblem())

		//		var timestamp : Long = System.currentTimeMillis() / 1000;

		//		boulders[0].timestamp = timestamp - 86400 * 1
		//		boulders[1].timestamp = timestamp - 86400 * 2
		//		boulders[2].timestamp = timestamp - 86400 * 3
		//		boulders[0].name = "Blue"
		//		boulders[1].name = "Yellow"
		//		boulders[2].name = "Orange"
		//		boulders[0].name = "V0"
		//		boulders[1].name = "V1"
		//		boulders[2].name = "V2"
		//		boulders[0].name = ""
		//		boulders[1].name = "V1"
		//		boulders[2].name = "V2"

		//		boulders[0].howDidItFeelScale = 3
		//		boulders[1].howDidItFeelScale = 3
		//		boulders[2].howDidItFeelScale = 3

		//		boulders[0].numAttempt = 1
		//		boulders[1].numAttempt = 1
		//		boulders[2].numAttempt = 1

		//		boulders[0].isProject = false
		//		boulders[1].isProject = false
		//		boulders[2].isProject = false

		//		boulders[0].isFlash = true
		//		boulders[1].isFlash = true
		//		boulders[2].isFlash = false

		//		boulders[0].isOutdoor = true
		//		boulders[1].isOutdoor = true
		//		boulders[2].isOutdoor = false

		//		boulders[0].holdType = SsHoldType.JUG.value
		//		boulders[1].holdType = SsHoldType.CRIMP.value or SsHoldType.PINCH.value or SsHoldType.POCKET.value
		//		boulders[2].holdType = SsHoldType.POCKET.value or SsHoldType.SLOPER.value

		//		boulders[0].routeFeatureType = SsRouteFeatureType.FACE.value
		//		boulders[1].routeFeatureType = SsRouteFeatureType.ARETE.value or SsRouteFeatureType.OVERHANG.value
		//		boulders[2].routeFeatureType = SsRouteFeatureType.SLAB.value or SsRouteFeatureType.TOP_OUT.value

		//		boulders[0].climbingTechniqueType = SsClimbingTechniqueType.CAMPUS.value
		//		boulders[1].climbingTechniqueType = SsClimbingTechniqueType.KNEE_BAR.value or SsClimbingTechniqueType.SMEARING.value
		//		boulders[2].climbingTechniqueType = SsClimbingTechniqueType.DROP_KNEE.value or SsClimbingTechniqueType.DYNO.value or SsClimbingTechniqueType.MANTLE.value
		//	}

		//	override fun doInBackground(vararg params : Void) : Void
		//	{
		//		Log.i(TAG, "Doing database work in the background!");
		//		mBoulderDao.deleteAll();

		//		for (int i=0; i <= boulders.length-1; i++)
		//		{
		//			Log.i(TAG, "Adding a boulder to the database!");
		//			mBoulderDao.insert(boulders[i]);
		//		}

		//	}

		//}

	}

}
