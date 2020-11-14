package me.gabeg.sicksends;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
 * Aspects of a climbing problem that are saved.
 */
public abstract class SsProblem
{

	/**
	 * Row ID of the table.
	 */
	@PrimaryKey(autoGenerate=true)
	public int id;

	/**
	 * Timestamp of when the problem was climbed.
	 */
	@ColumnInfo(name="timestamp")
	public long timestamp;

	/**
	 * Name of the problem.
	 */
	@ColumnInfo(name="name")
	public String name;

	/**
	 * Climbing grade of the problem.
	 */
	@ColumnInfo(name="grade")
	public String grade;

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	@ColumnInfo(name="perceived_grade")
	public String perceivedGrade;

	/**
	 * How how did it feel on a scale from easy to hard?
	 */
	@ColumnInfo(name="feel_type")
	public int feelType;

	/**
	 * Number of attempts done on the problem.
	 */
	@ColumnInfo(name="attempt")
	public int attempt;

	/**
	 * Latitude of where the problem is located.
	 */
	@ColumnInfo(name="location_lat")
	public String locationLat;

	/**
	 * Longitude of where the problem is located.
	 */
	@ColumnInfo(name="location_lon")
	public String locationLon;

	/**
	 * Is the problem a project?
	 */
	@ColumnInfo(name="is_project")
	public boolean isProject;

	/**
	 * Was the problem flashed?
	 */
	@ColumnInfo(name="is_flash")
	public boolean isFlash;

	/**
	 * Was the problem located indoors?
	 */
	@ColumnInfo(name="is_indoor")
	public boolean isIndoor;

	/**
	 * Was the problem located outdoors?
	 */
	@ColumnInfo(name="is_outdoor")
	public boolean isOutdoor;

	/**
	 * Types of holds on the problem.
	 */
	@ColumnInfo(name="hold_type")
	public long holdType;

	/**
	 * Types of route features on the problem.
	 */
	@ColumnInfo(name="route_type")
	public long routeType;

	/**
	 * Types of climbing techniques used on the problem.
	 */
	@ColumnInfo(name="technique_type")
	public long techniqueType;

	/**
	 * Path to the image.
	 */
	@ColumnInfo(name="image_path")
	public String imagePath;

}
