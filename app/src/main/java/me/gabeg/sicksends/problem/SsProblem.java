package me.gabeg.sicksends.problem;

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
	@ColumnInfo(name="id")
	public long mId;

	/**
	 * Timestamp at which the problem was climbed.
	 */
	@ColumnInfo(name="timestamp")
	public long mTimestamp;

	/**
	 * Name of the problem.
	 */
	@ColumnInfo(name="name")
	public String mName;

	/**
	 * Climbing grade of the problem.
	 */
	@ColumnInfo(name="grade")
	public String mGrade;

	/**
	 * Climbing grade that the user thinks the problem should be.
	 */
	@ColumnInfo(name="perceived_grade")
	public String mPerceivedGrade;

	/**
	 * How how did it feel on a scale from easy to hard?
	 */
	@ColumnInfo(name="feel_scale")
	public int mFeelScale;

	/**
	 * Number of attempts done on the problem.
	 */
	@ColumnInfo(name="num_attempt")
	public int mNumAttempt;

	/**
	 * Latitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name="location_lat")
	public String mLocationLat;

	/**
	 * Longitude coordinates of where the problem is located.
	 */
	@ColumnInfo(name="location_lon")
	public String mLocationLon;

	/**
	 * Whether the problem is a project or not.
	 */
	@ColumnInfo(name="is_project")
	public boolean mIsProject;

	/**
	 * Whether the problem is a flash or not.
	 */
	@ColumnInfo(name="is_flash")
	public boolean mIsFlash;

	/**
	 * Whether the problem is located indoors or not.
	 */
	@ColumnInfo(name="is_indoor")
	public boolean mIsIndoor;

	/**
	 * Was the problem located outdoors?
	 */
	@ColumnInfo(name="is_outdoor")
	public boolean mIsOutdoor;

	/**
	 * Types of route features on the problem.
	 */
	@ColumnInfo(name="route_feature_type")
	public long mRouteFeatureType;

	/**
	 * Types of holds on the problem.
	 */
	@ColumnInfo(name="hold_type")
	public long mHoldType;

	/**
	 * Types of climbing techniques used on the problem.
	 */
	@ColumnInfo(name="climbing_technique_type")
	public long mClimbingTechniqueType;

	/**
	 * Path to the image.
	 */
	@ColumnInfo(name="image_path")
	public String mImagePath;

	/**
	 * Get whether the problem is a flash or not.
	 *
	 * @return Whether the problem is a flash or not.
	 */
	public boolean isFlash()
	{
		return this.mIsFlash;
	}

	/**
	 * Get whether the problem is indoor or not.
	 *
	 * @return Whether the problem is indoor or not.
	 */
	public boolean isIndoor()
	{
		return this.mIsIndoor;
	}

	/**
	 * Get whether the problem is outdoor or not.
	 *
	 * @return Whether the problem is outdoor or not.
	 */
	public boolean isOutdoor()
	{
		return this.mIsOutdoor;
	}

	/**
	 * Get whether the problem is a project or not.
	 *
	 * @return The whether the problem is a project or not.
	 */
	public boolean isProject()
	{
		return this.mIsProject;
	}

	/**
	 * Get the types of climbing techniques used on the problem.
	 *
	 * @return The types of climbing techniques used on the problem.
	 */
	public long getClimbingTechniqueType()
	{
		return this.mClimbingTechniqueType;
	}

	/**
	 * Get how hard did the problem feel on a scale from easy to hard.
	 *
	 * @return How hard did the problem feel on a scale from easy to hard.
	 */
	public int getFeelScale()
	{
		return this.mFeelScale;
	}

	/**
	 * Get the climbing grade of the problem.
	 *
	 * @return The climbing grade of the problem.
	 */
	public String getGrade()
	{
		return this.mGrade;
	}

	/**
	 * Get the row ID.
	 *
	 * @return The row ID.
	 */
	public long getId()
	{
		return this.mId;
	}

	/**
	 * Get the path to the image.
	 *
	 * @return The path to the image.
	 */
	public String getImagePath()
	{
		return this.mImagePath;
	}

	/**
	 * @see #isFlash()
	 */
	public boolean getIsFlash()
	{
		return this.isFlash();
	}

	/**
	 * @see #isIndoor()
	 */
	public boolean getIsIndoor()
	{
		return this.isIndoor();
	}

	/**
	 * @see #isOutdoor()
	 */
	public boolean getIsOutdoor()
	{
		return this.isOutdoor();
	}

	/**
	 * @see #isProject()
	 */
	public boolean getIsProject()
	{
		return this.isProject();
	}

	/**
	 * Get the types of holds on the problem.
	 *
	 * @return The types of holds on the problem.
	 */
	public long getHoldType()
	{
		return this.mHoldType;
	}

	/**
	 * Get the latitude coordinates of where the problem is located.
	 *
	 * @return The latitude coordinates of where the problem is located.
	 */
	public String getLocationLat()
	{
		return this.mLocationLat;
	}

	/**
	 * Get the longitude coordinates of where the problem is located.
	 *
	 * @return The longitude coordinates of where the problem is located.
	 */
	public String getLocationLon()
	{
		return this.mLocationLon;
	}

	/**
	 * Get the name of the problem.
	 *
	 * @return The name of the problem.
	 */
	public String getName()
	{
		return this.mName;
	}

	/**
	 * Get the number of attempts done on the problem.
	 *
	 * @return The number of attempts done on the problem.
	 */
	public int getNumAttempt()
	{
		return this.mNumAttempt;
	}

	/**
	 * Get the perceived, by the user, climbing grade of the problem.
	 *
	 * @return The perceived climbing grade of the problem.
	 */
	public String getPerceivedGrade()
	{
		return this.mPerceivedGrade;
	}

	/**
	 * Get the type of route features on the problem.
	 *
	 * @return The type of route features on the problem.
	 */
	public long getRouteFeatureType()
	{
		return this.mRouteFeatureType;
	}

	/**
	 * Get the timestamp at which the problem was climbed.
	 *
	 * @return The timestamp at which the problem was climbed.
	 */
	public long getTimestamp()
	{
		return this.mTimestamp;
	}

	/**
	 * Set the types of climbing techniques used on the problem.
	 *
	 * @param  techs  The types of climbing techniques used on the problem.
	 */
	public void setClimbingTechniqueType(long techs)
	{
		this.mClimbingTechniqueType = techs;
	}

	/**
	 * Set how hard did the problem feel on a scale from easy to hard.
	 *
	 * @param  scale  How hard did the problem feel on a scale from easy to hard.
	 */
	public void setFeelScale(int scale)
	{
		this.mFeelScale = scale;
	}

	/**
	 * Set the climbing grade of the problem.
	 *
	 * @param  grade  The climbing grade of the problem.
	 */
	public void setGrade(String grade)
	{
		this.mGrade = grade;
	}

	/**
	 * Set the row ID.
	 *
	 * @param  id  The row ID.
	 */
	public void setId(long id)
	{
		this.mId = id;
	}

	/**
	 * Set the path to the image.
	 *
	 * @param  path  The path to the image.
	 */
	public void setImagePath(String path)
	{
		this.mImagePath = path;
	}

	/**
	 * Set whether the problem is a flash or not.
	 *
	 * @param  flash  Whether the problem is a flash or not.
	 */
	public void setIsFlash(boolean flash)
	{
		this.mIsFlash = flash;
	}

	/**
	 * Set whether the problem is indoor or not.
	 *
	 * @param  indoor  Whether the problem is indoor or not.
	 */
	public void setIsIndoor(boolean indoor)
	{
		this.mIsIndoor = indoor;
	}

	/**
	 * Set whether the problem is outdoor or not.
	 *
	 * @param  outdoor  Whether the problem is outdoor or not.
	 */
	public void setIsOutdoor(boolean outdoor)
	{
		this.mIsOutdoor = outdoor;
	}

	/**
	 * Set whether the problem is a project or not.
	 *
	 * @param  project  Whether the problem is a project or not.
	 */
	public void setIsProject(boolean project)
	{
		this.mIsProject = project;
	}

	/**
	 * Get the types of holds on the problem.
	 *
	 * @param  holds  The types of holds on the problem.
	 */
	public void setHoldType(long holds)
	{
		this.mHoldType = holds;
	}

	/**
	 * Set the latitude coordinates of where the problem is located.
	 *
	 * @param  lat  The latitude coordinates of where the problem is located.
	 */
	public void setLocationLat(String lat)
	{
		this.mLocationLat = lat;
	}

	/**
	 * Set the longitude coordinates of where the problem is located.
	 *
	 * @param  lon  The longitude coordinates of where the problem is located.
	 */
	public void setLocationLon(String lon)
	{
		this.mLocationLon = lon;
	}

	/**
	 * Set the name of the problem.
	 *
	 * @param  name  The name of the problem.
	 */
	public void setName(String name)
	{
		this.mName = name;
	}

	/**
	 * Set the number of attempts done on the problem.
	 *
	 * @param  num  The number of attempts done on the problem.
	 */
	public void setNumAttempt(int num)
	{
		this.mNumAttempt = num;
	}

	/**
	 * Set the perceived, by the user, climbing grade of the problem.
	 *
	 * @param  grade  The perceived climbing grade of the problem.
	 */
	public void setPerceivedGrade(String grade)
	{
		this.mPerceivedGrade = grade;
	}

	/**
	 * Set the type of route features on the problem.
	 *
	 * @param  features  The type of route features on the problem.
	 */
	public void setRouteFeatureType(long features)
	{
		this.mRouteFeatureType = features;
	}

	/**
	 * Set the timestamp at which the problem was climbed.
	 *
	 * @param  timestamp  The timestamp at which the problem was climbed.
	 */
	public void setTimestamp(long timestamp)
	{
		this.mTimestamp = timestamp;
	}

}
