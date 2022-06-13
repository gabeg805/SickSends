package me.gabeg.sicksends.db.generic

import android.util.Log
import me.gabeg.sicksends.shared.getHowDidItFeelScaleName

/**
 * Generic problem attributes.
 */
abstract class SsGenericProblem
{

	/**
	 */
	companion object
	{

		const val WHERE =
			"WHERE (:isOutdoor IS NULL OR is_outdoor = :isOutdoor) " +
			"AND   (:isProject IS NULL OR is_project = :isProject) " +
			"AND   (:isFlash   IS NULL OR is_flash   = :isFlash) "

		const val ORDER_BY = "ORDER BY timestamp DESC"

	}

	/**
	 * Row ID of the table.
	 */
	abstract var id : Long

	/**
	 * Timestamp at which the problem was climbed.
	 */
	abstract var timestamp : Long

	/**
	 * Grading system of the problem.
	 */
	abstract var gradingSystem : String

	/**
	 * Climbing grade of the problem.
	 */
	abstract var grade : String

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	abstract var perceivedGrade : String?

	/**
	 * How how did it feel on a scale from easy to hard?
	 *
	 * 0 = Very Easy
	 * 1 = Easy
	 * 2 = Normal
	 * 3 = Hard
	 * 4 = Very Hard
	 */
	abstract var howDidItFeelScale : Int?

	/**
	 * How how did it feel on a scale from easy to hard?
	 *
	 * 0 = Very Easy
	 * 1 = Easy
	 * 2 = Normal
	 * 3 = Hard
	 * 4 = Very Hard
	 */
	var howDidItFeel : String = getHowDidItFeelScaleName(howDidItFeelScale)

	/**
	 * Name of the problem.
	 */
	abstract var name : String?

	/**
	 * Number of attempts done on the problem.
	 */
	abstract var numAttempt : Long?

	/**
	 * Name of where the problem is located.
	 */
	abstract var locationName : String?

	/**
	 * Latitude coordinates of where the problem is located.
	 */
	abstract var locationLat : String?

	/**
	 * Longitude coordinates of where the problem is located.
	 */
	abstract var locationLon : String?

	/**
	 * Whether the problem is a project or not.
	 */
	abstract var isProject : Boolean?

	/**
	 * Whether the problem is a flash or not.
	 */
	abstract var isFlash : Boolean?

	/**
	 * Whether the problem is located outdoors or not.
	 */
	abstract var isOutdoor : Boolean?

	/**
	 * Types of route features on the problem.
	 */
	abstract var routeFeatureType : Long?

	/**
	 * Types of holds on the problem.
	 */
	abstract var holdType : Long?

	/**
	 * Types of climbing techniques used on the problem.
	 */
	abstract var climbingTechniqueType : Long?

	/**
	 * File path to the image.
	 */
	abstract var imagePath : String?

	/**
	 * Notes on the problem.
	 */
	abstract var note : String?

	fun debug()
	{
		Log.i("ProblemDebug", "id                    : $id")
		Log.i("ProblemDebug", "timestamp             : $timestamp")
		Log.i("ProblemDebug", "name                  : $name")
		Log.i("ProblemDebug", "grade                 : $grade")
		Log.i("ProblemDebug", "perceivedGrade        : $perceivedGrade")
		Log.i("ProblemDebug", "howDidItFeel          : $howDidItFeelScale")
		Log.i("ProblemDebug", "numAttempt            : $numAttempt")
		Log.i("ProblemDebug", "locationName          : $locationName")
		Log.i("ProblemDebug", "locationLat           : $locationLat")
		Log.i("ProblemDebug", "locationLon           : $locationLon")
		Log.i("ProblemDebug", "isProject             : $isProject")
		Log.i("ProblemDebug", "isFlash               : $isFlash")
		Log.i("ProblemDebug", "isOutdoor             : $isOutdoor")
		Log.i("ProblemDebug", "routeFeatureType      : $routeFeatureType")
		Log.i("ProblemDebug", "holdType              : $holdType")
		Log.i("ProblemDebug", "climbingTechniqueType : $climbingTechniqueType")
		Log.i("ProblemDebug", "imagePath             : $imagePath")
		Log.i("ProblemDebug", "note                  : $note")
	}

}