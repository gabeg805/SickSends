package me.gabeg.sicksends.db.generic

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.room.Ignore
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.shared.howDidItFeelToName

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
	abstract var howDidItFeel : Int?

	/**
	 * How how did it feel on a scale from easy to hard?
	 *
	 * 0 = Very Easy
	 * 1 = Easy
	 * 2 = Normal
	 * 3 = Hard
	 * 4 = Very Hard
	 */
	@Ignore
	var howDidItFeelName : String = ""
	//	get() = howDidItFeelToName(howDidItFeel)

	/**
	 * Check if there is a "How did it feel" scale.
	 *
	 * @return True if there is a scale, and False if it is null or NORMAL.
	 */
	@Ignore
	var hasHowDidItFeel : Boolean = false
		get() = (howDidItFeel != null)
			&& (howDidItFeel != SsHowDidItFeelType.NORMAL.value)

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
	 * Types of wall features on the problem.
	 */
	abstract var wallFeature : Long?

	/**
	 * Types of holds on the problem.
	 */
	abstract var hold : Long?

	/**
	 * Types of climbing techniques used on the problem.
	 */
	abstract var climbingTechnique : Long?

	/**
	 * File path to the image.
	 */
	abstract var mediaPath : String?

	/**
	 * Notes on the problem.
	 */
	abstract var note : String?

	/**
	 * An observable of whether the problem is a flash or not.
	 */
	@Ignore
	var observableIsFlash = object : MutableLiveData<Boolean?>(null)
	{
		override fun setValue(value : Boolean?)
		{
			super.setValue(value)

			isFlash = value
		}
	}

	/**
	 * An observable of whether the problem is a project or not.
	 */
	@Ignore
	var observableIsProject = object : MutableLiveData<Boolean?>(null)
	{
		override fun setValue(value : Boolean?)
		{
			super.setValue(value)

			isProject = value
		}
	}

	/**
	 * Generic extra data to associate with a problem.
	 */
	@Ignore
	var data : Any? = null

	/**
	 * Debug.
	 */
	fun debug()
	{
		Log.i("ProblemDebug", "id                    : $id")
		Log.i("ProblemDebug", "timestamp             : $timestamp")
		Log.i("ProblemDebug", "name                  : $name")
		Log.i("ProblemDebug", "grade                 : $grade")
		Log.i("ProblemDebug", "perceivedGrade        : $perceivedGrade")
		Log.i("ProblemDebug", "howDidItFeel          : $howDidItFeel")
		Log.i("ProblemDebug", "numAttempt            : $numAttempt")
		Log.i("ProblemDebug", "locationName          : $locationName")
		Log.i("ProblemDebug", "locationLat           : $locationLat")
		Log.i("ProblemDebug", "locationLon           : $locationLon")
		Log.i("ProblemDebug", "isProject             : $isProject")
		Log.i("ProblemDebug", "isFlash               : $isFlash")
		Log.i("ProblemDebug", "isOutdoor             : $isOutdoor")
		Log.i("ProblemDebug", "wallFeatureType       : $wallFeature")
		Log.i("ProblemDebug", "holdType              : $hold")
		Log.i("ProblemDebug", "climbingTechniqueType : $climbingTechnique")
		Log.i("ProblemDebug", "mediaPath             : $mediaPath")
		Log.i("ProblemDebug", "note                  : $note")
	}

}
