package me.gabeg.sicksends.sport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.gabeg.sicksends.rope.SsGenericRopeProblem

/**
 * Aspects of a sport problem that are saved.
 */
@Entity(tableName = "sport")
data class SsSportProblem (

	/**
	 * Row ID of the table.
	 */
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	override var id: Long = 0,

	/**
	 * Timestamp at which the problem was climbed.
	 */
	@ColumnInfo(name = "timestamp")
	override var timestamp: Long = 0,

	/**
	 * Name of the problem.
	 */
	@ColumnInfo(name = "name")
	override var name: String? = null,

	/**
	 * Climbing grade of the problem.
	 */
	@ColumnInfo(name = "grade")
	override var grade: String? = null,

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	@ColumnInfo(name = "perceived_grade")
	override var perceivedGrade: String? = null,

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
	override var howDidItFeelScale : Int = 0,

	/**
	 * Number of attempts done on the problem.
	 */
	@ColumnInfo(name = "num_attempt")
	override var numAttempt : Long = 0,

	/**
	 * Name of where the problem is located.
	 */
	@ColumnInfo(name = "location_name")
	override var locationName: String? = null,

	/**
	 * Latitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lat")
	override var locationLat: String? = null,

	/**
	 * Longitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lon")
	override var locationLon: String? = null,

	/**
	 * Whether the problem is sent or not.
	 */
	@ColumnInfo(name = "is_send")
	override var isSend : Boolean = false,

	/**
	 * Whether the problem is a project or not.
	 */
	@ColumnInfo(name = "is_project")
	override var isProject : Boolean = false,

	/**
	 * Whether the problem is a flash or not.
	 */
	@ColumnInfo(name = "is_flash")
	override var isFlash : Boolean = false,

	/**
	 * Whether the problem is located outdoors or not.
	 */
	@ColumnInfo(name = "is_outdoor")
	override var isOutdoor : Boolean = false,

	/**
	 * Types of route features on the problem.
	 */
	@ColumnInfo(name = "route_feature_type")
	override var routeFeatureType: Long = 0,

	/**
	 * Types of holds on the problem.
	 */
	@ColumnInfo(name = "hold_type")
	override var holdType: Long = 0,

	/**
	 * Types of climbing techniques used on the problem.
	 */
	@ColumnInfo(name = "climbing_technique_type")
	override var climbingTechniqueType: Long = 0,

	/**
	 * File path to the image.
	 */
	@ColumnInfo(name = "image_path")
	override var imagePath: String? = null,

	/**
	 * Notes on the problem.
	 */
	@ColumnInfo(name = "note")
	override var note: String? = null,

	/**
	 * Number of takes done on the problem.
	 */
	@ColumnInfo(name = "num_take")
	override var numTake : Int = 0,

	/**
	 * Whether the problem was onsighted or not.
	 */
	@ColumnInfo(name = "is_onsight")
	override var isOnsight : Boolean = false,

	/**
	 * Whether the problem was a redpoint or not.
	 */
	@ColumnInfo(name = "is_redpoint")
	override var isRedpoint : Boolean = false,

) : SsGenericRopeProblem()
