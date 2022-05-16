package me.gabeg.sicksends.problem

import android.content.Context
import androidx.room.Database
import me.gabeg.sicksends.boulder.SsBoulder
import me.gabeg.sicksends.sport.SsSport
import me.gabeg.sicksends.toprope.SsTopRope
import me.gabeg.sicksends.trad.SsTrad
import androidx.room.RoomDatabase
import me.gabeg.sicksends.boulder.SsBoulderDao
import me.gabeg.sicksends.sport.SsSportDao
import me.gabeg.sicksends.toprope.SsTopRopeDao
import me.gabeg.sicksends.trad.SsTradDao
import me.gabeg.sicksends.problem.SsProblemDatabase
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database that can be used to access stored climbing information.
 */
@Database(
	entities = [SsBoulder::class, SsSport::class, SsTopRope::class, SsTrad::class],
	version = 1)
abstract class SsProblemDatabase : RoomDatabase()
{
	/**
	 * Store bouldering problems.
	 */
	abstract fun boulderDao(): SsBoulderDao?

	/**
	 * Store sport problems.
	 */
	abstract fun sportDao(): SsSportDao?

	/**
	 * Store top rope problems.
	 */
	abstract fun topRopeDao(): SsTopRopeDao?

	/**
	 * Store trad problems.
	 */
	abstract fun tradDao(): SsTradDao?

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
		fun getInstance(context: Context): SsProblemDatabase?
		{
			// If the instance is not null, return it. Otherwise create it
			return sInstance ?: synchronized(this)
			{
				val appContext = context.applicationContext
				val instance = Room.databaseBuilder(appContext,
						SsProblemDatabase::class.java, DB_NAME)
					.addCallback(sDatabaseCallback)
					.build()

				// Set and return the instance
				sInstance = instance
				sInstance
			}
		}

		/**
		 * Callback for populating the database, for testing purposes.
		 */
		private val sDatabaseCallback: Callback = object : Callback()
		{
			override fun onOpen(db: SupportSQLiteDatabase)
			{
				super.onOpen(db)
				//new PopulateDbAsync(sInstance).execute();
			}
		}

		///**
		// * Populate the database in the background.
		// */
		//private static class PopulateDbAsync
		//	extends AsyncTask<Void, Void, Void>
		//{
		//	private final SsBoulderDao mBoulderDao;
		//	SsBoulder[] boulders;
		//	PopulateDbAsync(SsProblemDatabase db)
		//	{
		//		mBoulderDao = db.boulderDao();
		//		boulders = new SsBoulder[3];
		//		boulders[0] = new SsBoulder();
		//		boulders[1] = new SsBoulder();
		//		boulders[2] = new SsBoulder();
		//		long timestamp = System.currentTimeMillis() / 1000;
		//		boulders[0].timestamp = timestamp - 86400 * 1;
		//		boulders[1].timestamp = timestamp - 86400 * 2;
		//		boulders[2].timestamp = timestamp - 86400 * 3;
		//		boulders[0].name = "Blue";
		//		boulders[1].name = "Yellow";
		//		boulders[2].name = "Orange";
		//		boulders[0].name = "V0";
		//		boulders[1].name = "V1";
		//		boulders[2].name = "V2";
		//		boulders[0].name = "";
		//		boulders[1].name = "V1";
		//		boulders[2].name = "V2";
		//		boulders[0].feelType = 3;
		//		boulders[1].feelType = 3;
		//		boulders[2].feelType = 3;
		//		boulders[0].attempt = 1;
		//		boulders[1].attempt = 1;
		//		boulders[2].attempt = 1;
		//		boulders[0].isProject = false;
		//		boulders[1].isProject = false;
		//		boulders[2].isProject = false;
		//		boulders[0].isFlash = true;
		//		boulders[1].isFlash = true;
		//		boulders[2].isFlash = false;
		//		boulders[0].isIndoor = true;
		//		boulders[1].isIndoor = true;
		//		boulders[2].isIndoor = false;
		//		boulders[0].holdType = SsHoldType.JUG.getValue();
		//		boulders[1].holdType = SsHoldType.CRIMP.getValue() | SsHoldType.PINCH.getValue() | SsHoldType.POCKET.getValue();
		//		boulders[2].holdType = SsHoldType.POCKET.getValue() | SsHoldType.SLOPER.getValue();
		//		boulders[0].routeType = SsRouteType.FACE.getValue();
		//		boulders[1].routeType = SsRouteType.ARETE.getValue() | SsRouteType.OVERHANG.getValue();
		//		boulders[2].routeType = SsRouteType.SLAB.getValue() | SsRouteType.TOP_OUT.getValue();
		//		boulders[0].techniqueType = SsTechniqueType.CAMPUS.getValue();
		//		boulders[1].techniqueType = SsTechniqueType.KNEE_BAR.getValue() | SsTechniqueType.SMEARING.getValue();
		//		boulders[2].techniqueType = SsTechniqueType.DROP_KNEE.getValue() | SsTechniqueType.DYNO.getValue() | SsTechniqueType.MANTLE.getValue();
		//	}
		//	@Override
		//	protected Void doInBackground(final Void... params)
		//	{
		//		Log.i(TAG, "Doing database work in the background!");
		//		mBoulderDao.deleteAll();
		//		for (int i=0; i <= boulders.length-1; i++)
		//		{
		//			Log.i(TAG, "Adding a boulder to the database!");
		//			mBoulderDao.insert(boulders[i]);
		//		}
		//		return null;
		//	}
		//}
	}

}
