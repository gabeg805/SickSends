package me.gabeg.sicksends.problem

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.boulder.SsBoulderDao
import me.gabeg.sicksends.boulder.SsBoulderProblem
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.type.SsRouteFeatureType
import me.gabeg.sicksends.sport.SsSportDao
import me.gabeg.sicksends.sport.SsSportProblem
import me.gabeg.sicksends.toprope.SsTopRopeDao
import me.gabeg.sicksends.toprope.SsTopRopeProblem
import me.gabeg.sicksends.trad.SsTradDao
import me.gabeg.sicksends.trad.SsTradProblem

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
				Log.i("SsProblemDatabase", "Populating/Creating the database!")
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
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 19) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 20) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 21) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = ""
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 22) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 23) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 24) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 0
					} else if (i == 25) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 0
					} else if (i == 26) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = false
						p.howDidItFeelScale = 1
					} else if (i == 27) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = true
						p.isFlash = false
						p.isOutdoor = true
						p.howDidItFeelScale = 2
					} else if (i == 28) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = false
						p.howDidItFeelScale = 4
					} else if (i == 29) {
						p.name = "Silence at the Disco"
						p.grade = "V8"
						p.locationName = "Yosemite Valley"
						p.isProject = false
						p.isFlash = true
						p.isOutdoor = true
						p.howDidItFeelScale = 4
					}

					boulderDao.insert(p);
			}

			//boulders.add(SsBoulderProblem())
				//boulders.add(SsBoulderProblem())
				//boulders.add(SsBoulderProblem())

				//boulders[0].timestamp = timestamp - 86400 * 1
				//boulders[1].timestamp = timestamp - 86400 * 2
				//boulders[2].timestamp = timestamp - 86400 * 3

				//boulders[0].name = "Blue"
				//boulders[1].name = "Yellow"
				//boulders[2].name = "Orange"

				//boulders[0].grade = "V4"
				//boulders[1].grade = "V6"
				//boulders[2].grade = "V3"

				//boulders[0].howDidItFeelScale = 0
				//boulders[1].howDidItFeelScale = 3
				//boulders[2].howDidItFeelScale = 2

				//boulders[0].numAttempt = 2
				//boulders[1].numAttempt = 5
				//boulders[2].numAttempt = 1

				//boulders[0].isProject = false
				//boulders[1].isProject = false
				//boulders[2].isProject = false

				//boulders[0].isFlash = false
				//boulders[1].isFlash = false
				//boulders[2].isFlash = true

				//boulders[0].isOutdoor = false
				//boulders[1].isOutdoor = false
				//boulders[2].isOutdoor = true

				//boulders[0].holdType = SsHoldType.JUG.value
			//	boulders[1].holdType = SsHoldType.CRIMP.value or SsHoldType.PINCH.value or SsHoldType.POCKET.value
			//	boulders[2].holdType = SsHoldType.POCKET.value or SsHoldType.SLOPER.value

			//	boulders[0].routeFeatureType = SsRouteFeatureType.FACE.value
			//	boulders[1].routeFeatureType = SsRouteFeatureType.ARETE.value or SsRouteFeatureType.OVERHANG.value
			//	boulders[2].routeFeatureType = SsRouteFeatureType.SLAB.value or SsRouteFeatureType.TOP_OUT.value

			//	boulders[0].climbingTechniqueType = SsClimbingTechniqueType.CAMPUS.value
			//	boulders[1].climbingTechniqueType = SsClimbingTechniqueType.KNEE_BAR.value or SsClimbingTechniqueType.SMEARING.value
			//	boulders[2].climbingTechniqueType = SsClimbingTechniqueType.DROP_KNEE.value or SsClimbingTechniqueType.DYNO.value or SsClimbingTechniqueType.MANTLE.value

				//Log.i(TAG, "Doing database work in the background!");

				//for (i in 0 until boulders.size)
			//	{
			//		Log.i(TAG, "Adding a boulder to the database!");
			//		boulderDao.insert(boulders[i]);
			//	}

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
