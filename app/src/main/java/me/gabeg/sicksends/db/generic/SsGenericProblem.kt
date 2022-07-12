package me.gabeg.sicksends.db.generic

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.room.Ignore
import me.gabeg.sicksends.problem.type.SsClimbingTechniqueType
import me.gabeg.sicksends.problem.type.SsHoldType
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.problem.type.SsWallFeatureType
import me.gabeg.sicksends.shared.howDidItFeelToName
import java.util.*

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
	 * Value for how a problem felt, on a scale from very easy to very hard?
	 *
	 * 0 = Very Easy
	 * 1 = Easy
	 * 2 = Normal
	 * 3 = Hard
	 * 4 = Very Hard
	 */
	abstract var howDidItFeel : Int?

	/**
	 * The name for how a problem felt.
	 */
	@Ignore
	var howDidItFeelName : String = ""

	/**
	 * Check if there is a non-normal value set for how a problem felt.
	 *
	 * @return True if there is a how did it feel value, and False if it is null
	 *         or NORMAL.
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
	abstract var wallFeature : EnumSet<SsWallFeatureType>

	/**
	 * Types of holds on the problem.
	 */
	abstract var hold : EnumSet<SsHoldType>

	/**
	 * Check if there is one or more hold set.
	 *
	 * @return True if there is one or more hold set, and False otherwise.
	 */
	@Ignore
	var hasHold : Boolean = false
		get() = hold.isNotEmpty()

	/**
	 * Types of climbing techniques used on the problem.
	 */
	abstract var climbingTechnique : EnumSet<SsClimbingTechniqueType>

	/**
	 * File path to the image.
	 */
	abstract var mediaPath : String?

	/**
	 * Notes on the problem.
	 */
	abstract var note : String?

	/**
	 * An observable of the number of times a problem was attempted.
	 */
	@Ignore
	var observableNumAttempt = object : MutableLiveData<Long?>(null)
	{
		override fun setValue(value : Long?)
		{
			super.setValue(value)

			numAttempt = value
		}
	}

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
	 * An observable name of the problem.
	 */
	@Ignore
	var observableName = object : MutableLiveData<String?>(null)
	{
		override fun setValue(value : String?)
		{
			super.setValue(value)

			name = value
		}
	}

	/**
	 * An observable note on the problem.
	 */
	@Ignore
	var observableNote = object : MutableLiveData<String?>(null)
	{
		override fun setValue(value : String?)
		{
			super.setValue(value)

			note = value
		}
	}

	/**
	 * An observable of the type of holds on the problem.
	 */
	@Ignore
	var observableHold = object : MutableLiveData<EnumSet<SsHoldType>>(SsHoldType.emptySet())
	{
		override fun setValue(value : EnumSet<SsHoldType>)
		{
			super.setValue(value)

			hold = value
		}
	}

	/**
	 * An observable of the type of features on the wall of the problem.
	 */
	@Ignore
	var observableWallFeature = object : MutableLiveData<EnumSet<SsWallFeatureType>>(SsWallFeatureType.emptySet())
	{
		override fun setValue(value : EnumSet<SsWallFeatureType>)
		{
			super.setValue(value)

			wallFeature = value
		}
	}

	/**
	 * An observable of the type of climbing techniques used on the problem.
	 */
	@Ignore
	var observableClimbingTechnique = object : MutableLiveData<EnumSet<SsClimbingTechniqueType>>(SsClimbingTechniqueType.emptySet())
	{
		override fun setValue(value : EnumSet<SsClimbingTechniqueType>)
		{
			super.setValue(value)

			climbingTechnique = value
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
