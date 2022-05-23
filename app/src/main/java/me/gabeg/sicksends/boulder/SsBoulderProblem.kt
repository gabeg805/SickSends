package me.gabeg.sicksends.boulder

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Aspects of a boulder problem that are saved.
 */
@Entity(tableName = "boulder")
data class SsBoulderProblem (

	/**
	 * Row ID of the table.
	 */
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long = 0,

	/**
	 * Timestamp at which the problem was climbed.
	 */
	@ColumnInfo(name = "timestamp")
	var timestamp: Long = 0,

	/**
	 * Name of the problem.
	 */
	@ColumnInfo(name = "name")
	var name: String? = null,

	/**
	 * Climbing grade of the problem.
	 */
	@ColumnInfo(name = "grade")
	var grade: String? = null,

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	@ColumnInfo(name = "perceived_grade")
	var perceivedGrade: String? = null,

	/**
	 * How how did it feel on a scale from easy to hard?
	 *
	 * 0 = Undefined
	 * 1 = Very Easy
	 * 2 = Easy
	 * 3 = Normal
	 * 4 = Hard
	 * 5 = Very Hard
	 */
	@ColumnInfo(name = "how_did_it_feel_scale")
	var howDidItFeelScale : Int = 0,

	/**
	 * Number of attempts done on the problem.
	 */
	@ColumnInfo(name = "num_attempt")
	var numAttempt : Long = 0,

	/**
	 * Name of where the problem is located.
	 */
	@ColumnInfo(name = "location_name")
	var locationName: String? = null,

	/**
	 * Latitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lat")
	var locationLat: String? = null,

	/**
	 * Longitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lon")
	var locationLon: String? = null,

	/**
	 * Whether the problem is sent or not.
	 */
	@ColumnInfo(name = "is_send")
	var isSend : Boolean = false,

	/**
	 * Whether the problem is a project or not.
	 */
	@ColumnInfo(name = "is_project")
	var isProject : Boolean = false,

	/**
	 * Whether the problem is a flash or not.
	 */
	@ColumnInfo(name = "is_flash")
	var isFlash : Boolean = false,

	/**
	 * Whether the problem is located outdoors or not.
	 */
	@ColumnInfo(name = "is_outdoor")
	var isOutdoor : Boolean = false,

	/**
	 * Types of route features on the problem.
	 */
	@ColumnInfo(name = "route_feature_type")
	var routeFeatureType: Long = 0,

	/**
	 * Types of holds on the problem.
	 */
	@ColumnInfo(name = "hold_type")
	var holdType: Long = 0,

	/**
	 * Types of climbing techniques used on the problem.
	 */
	@ColumnInfo(name = "climbing_technique_type")
	var climbingTechniqueType: Long = 0,

	/**
	 * File path to the image.
	 */
	@ColumnInfo(name = "image_path")
	var imagePath: String? = null,

	/**
	 * Notes on the problem.
	 */
	@ColumnInfo(name = "note")
	var note: String? = null)

{

	/**
	 * Debug the attributes.
	 */
	fun debug()
	{
		Log.i("ProblemDebug", "id                    : $id")
		Log.i("ProblemDebug", "timestamp             : $timestamp")
		Log.i("ProblemDebug", "name                  : $name")
		Log.i("ProblemDebug", "grade                 : $grade")
		Log.i("ProblemDebug", "perceivedGrade        : $perceivedGrade")
		Log.i("ProblemDebug", "howDidItFeel          : $howDidItFeelScale")
		Log.i("ProblemDebug", "numAttemp             : $numAttempt")
		Log.i("ProblemDebug", "locationName          : $locationName")
		Log.i("ProblemDebug", "locationLat           : $locationLat")
		Log.i("ProblemDebug", "locationLon           : $locationLon")
		Log.i("ProblemDebug", "isSend                : $isSend")
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
