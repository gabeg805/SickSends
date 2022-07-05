package me.gabeg.sicksends.db.toprope

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.gabeg.sicksends.db.generic.SsGenericRopeProblem

/**
 * Aspects of a top rope problem that are saved.
 */
@Entity(tableName = "top_rope")
data class SsTopRopeProblem(

	/**
	 * Row ID of the table.
	 */
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	override var id : Long = 0,

	/**
	 * Timestamp at which the problem was climbed.
	 */
	@ColumnInfo(name = "timestamp")
	override var timestamp : Long = 0,

	/**
	 * Grading system of the problem.
	 */
	@ColumnInfo(name = "grading_system")
	override var gradingSystem : String = "",

	/**
	 * Climbing grade of the problem.
	 */
	@ColumnInfo(name = "grade")
	override var grade : String = "",

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	@ColumnInfo(name = "perceived_grade")
	override var perceivedGrade : String? = null,

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
	override var howDidItFeel : Int? = null,

	/**
	 * Name of the problem.
	 */
	@ColumnInfo(name = "name")
	override var name : String? = null,

	/**
	 * Number of attempts done on the problem.
	 */
	@ColumnInfo(name = "num_attempt")
	override var numAttempt : Long? = null,

	/**
	 * Name of where the problem is located.
	 */
	@ColumnInfo(name = "location_name")
	override var locationName : String? = null,

	/**
	 * Latitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lat")
	override var locationLat : String? = null,

	/**
	 * Longitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name = "location_lon")
	override var locationLon : String? = null,

	/**
	 * Whether the problem is a project or not.
	 */
	@ColumnInfo(name = "is_project")
	override var isProject : Boolean? = null,

	/**
	 * Whether the problem is a flash or not.
	 */
	@ColumnInfo(name = "is_flash")
	override var isFlash : Boolean? = null,

	/**
	 * Whether the problem is located outdoors or not.
	 */
	@ColumnInfo(name = "is_outdoor")
	override var isOutdoor : Boolean? = null,

	/**
	 * Types of wall features on the problem.
	 */
	@ColumnInfo(name = "wall_feature_type")
	override var wallFeature : Long? = null,

	/**
	 * Types of holds on the problem.
	 */
	@ColumnInfo(name = "hold_type")
	override var hold : Long? = null,

	/**
	 * Types of climbing techniques used on the problem.
	 */
	@ColumnInfo(name = "climbing_technique_type")
	override var climbingTechnique : Long? = null,

	/**
	 * File path to the image.
	 */
	@ColumnInfo(name = "media_path")
	override var mediaPath : String? = null,

	/**
	 * Notes on the problem.
	 */
	@ColumnInfo(name = "note")
	override var note : String? = null,

	/**
	 * Number of takes done on the problem.
	 */
	@ColumnInfo(name = "num_take")
	override var numTake : Int? = null,

	/**
	 * Whether the problem was onsighted or not.
	 */
	@ColumnInfo(name = "is_onsight")
	override var isOnsight : Boolean? = null,

	/**
	 * Whether the problem was a redpoint or not.
	 */
	@ColumnInfo(name = "is_redpoint")
	override var isRedpoint : Boolean? = null,

) : SsGenericRopeProblem()

/**
 * Hilt module to provide an instance of a top rope problem.
 */
@InstallIn(SingletonComponent::class)
@Module
class SsTopRopeProblemModule
{

	/**
	 * Provide an instance of a top rope problem.
	 */
	@Provides
	fun provideTopRopeProblem() : SsTopRopeProblem
	{
		return SsTopRopeProblem()
	}

}
