package me.gabeg.sicksends.problem;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.lang.System;

import me.gabeg.sicksends.problem.type.SsHoldType;
import me.gabeg.sicksends.problem.type.SsRouteType;
import me.gabeg.sicksends.problem.type.SsTechniqueType;
import me.gabeg.sicksends.boulder.SsBoulder;
import me.gabeg.sicksends.boulder.SsBoulderDao;
import me.gabeg.sicksends.sport.SsSport;
import me.gabeg.sicksends.sport.SsSportDao;
import me.gabeg.sicksends.toprope.SsTopRope;
import me.gabeg.sicksends.toprope.SsTopRopeDao;
import me.gabeg.sicksends.trad.SsTrad;
import me.gabeg.sicksends.trad.SsTradDao;

/**
 * Database that can be used to access stored climbing information.
 */
@Database(entities={SsBoulder.class, SsSport.class, SsTopRope.class, SsTrad.class}, version=1)
public abstract class SsProblemDatabase
	extends RoomDatabase
{

	public static final String TAG = "SsProblemDatabase";

	/**
	 * Store bouldering problems.
	 */
	public abstract SsBoulderDao boulderDao();

	/**
	 * Store sport problems.
	 */
	public abstract SsSportDao sportDao();

	/**
	 * Store top rope problems.
	 */
	public abstract SsTopRopeDao topRopeDao();

	/**
	 * Store trad problems.
	 */
	public abstract SsTradDao tradDao();

	/**
	 * Name of the database.
	 */
	public static final String DB_NAME = "SsClimbingProblems.db";

	/**
	 * Lock object for the single instance.
	 */
	private static final Object LOCK = new Object();

	/**
	 * Singleton instance of the database.
	 */
	private static SsProblemDatabase sInstance;

	/**
	 * Create a static instance of the database.
	 *
	 * @param  context  Application context.
	 *
	 * @return A static instance of the database.
	 */
	public static SsProblemDatabase getInstance(Context context)
	{
		synchronized (LOCK)
		{
			if (sInstance == null)
			{
				Context appContext = context.getApplicationContext();
				sInstance = Room.databaseBuilder(appContext, SsProblemDatabase.class,
					DB_NAME)
					//.allowMainThreadQueries()
					.addCallback(sDatabaseCallback)
					.build();
			}

			return sInstance;
		}
	}

	/**
	 * Callback for populating the database, for testing purposes.
	 */
	private static RoomDatabase.Callback sDatabaseCallback =
		new RoomDatabase.Callback()
	{
		@Override
		public void onOpen(@NonNull SupportSQLiteDatabase db)
		{
			super.onOpen(db);
			new PopulateDbAsync(sInstance).execute();
		}
	};

	/**
	 * Populate the database in the background.
	 */
	private static class PopulateDbAsync
		extends AsyncTask<Void, Void, Void>
	{

		private final SsBoulderDao mBoulderDao;
		SsBoulder[] boulders;

		PopulateDbAsync(SsProblemDatabase db)
		{
			mBoulderDao = db.boulderDao();
			boulders = new SsBoulder[3];
			boulders[0] = new SsBoulder();
			boulders[1] = new SsBoulder();
			boulders[2] = new SsBoulder();

			long timestamp = System.currentTimeMillis() / 1000;

			boulders[0].timestamp = timestamp - 86400 * 1;
			boulders[1].timestamp = timestamp - 86400 * 2;
			boulders[2].timestamp = timestamp - 86400 * 3;

			boulders[0].name = "Blue";
			boulders[1].name = "Yellow";
			boulders[2].name = "Orange";

			boulders[0].name = "V0";
			boulders[1].name = "V1";
			boulders[2].name = "V2";

			boulders[0].name = "";
			boulders[1].name = "V1";
			boulders[2].name = "V2";

			boulders[0].feelType = 3;
			boulders[1].feelType = 3;
			boulders[2].feelType = 3;

			boulders[0].attempt = 1;
			boulders[1].attempt = 1;
			boulders[2].attempt = 1;

			boulders[0].isProject = false;
			boulders[1].isProject = false;
			boulders[2].isProject = false;

			boulders[0].isFlash = true;
			boulders[1].isFlash = true;
			boulders[2].isFlash = false;

			boulders[0].isIndoor = true;
			boulders[1].isIndoor = true;
			boulders[2].isIndoor = false;

			boulders[0].holdType = SsHoldType.JUG.getValue();
			boulders[1].holdType = SsHoldType.CRIMP.getValue() | SsHoldType.PINCH.getValue() | SsHoldType.POCKET.getValue();
			boulders[2].holdType = SsHoldType.POCKET.getValue() | SsHoldType.SLOPER.getValue();

			boulders[0].routeType = SsRouteType.FACE.getValue();
			boulders[1].routeType = SsRouteType.ARETE.getValue() | SsRouteType.OVERHANG.getValue();
			boulders[2].routeType = SsRouteType.SLAB.getValue() | SsRouteType.TOP_OUT.getValue();

			boulders[0].techniqueType = SsTechniqueType.CAMPUS.getValue();
			boulders[1].techniqueType = SsTechniqueType.KNEE_BAR.getValue() | SsTechniqueType.SMEARING.getValue();
			boulders[2].techniqueType = SsTechniqueType.DROP_KNEE.getValue() | SsTechniqueType.DYNO.getValue() | SsTechniqueType.MANTLE.getValue();
		}

		@Override
		protected Void doInBackground(final Void... params)
		{
			Log.i(TAG, "Doing database work in the background!");
			mBoulderDao.deleteAll();

			for (int i=0; i <= boulders.length-1; i++)
			{
				Log.i(TAG, "Adding a boulder to the database!");
				mBoulderDao.insert(boulders[i]);
			}

			return null;
		}

	}

}
