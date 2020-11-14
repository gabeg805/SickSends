package me.gabeg.sicksends;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Database that can be used to access stored climbing information.
 */
//@Database(entities={SsBoulderProblem.class, SsSportProblem.class, SsTopRopeProblem.class, SsTradProblem.class}, version=1)
@Database(entities={SsBoulderProblem.class, SsLeadProblem.class, SsTopRopeProblem.class, SsTradProblem.class}, version=1)
public abstract class SsDatabase
	extends RoomDatabase
{

	/**
	 * Store bouldering problems.
	 */
	public abstract SsBoulderDao boulderDao();

	/**
	 * Store lead problems.
	 */
	public abstract SsLeadDao leadDao();

	///**
	// * Store sport problems.
	// */
	//public abstract SsSportDao sportDao();

	/**
	 * Store top rope problems.
	 */
	public abstract SsTopRopeDao topRopeDao();

	/**
	 * Store trad problems.
	 */
	public abstract SsTradDao tradDao();

}
