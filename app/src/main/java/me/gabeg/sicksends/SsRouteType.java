package me.gabeg.sicksends;

/**
 * Types of climbing features on a route that a person may encounter.
 */
public enum SsRouteType
{

	CHIMNEY(1 << 0),
	CRACK(1 << 1),
	DIHEDRAL(1 << 2),
	FACE(1 << 3),
	HIGH_BALL(1 << 4),
	OVERHANG(1 << 5),
	ROOF(1 << 6),
	SLAB(1 << 7),
	TOP_OUT(1 << 8),
	TRAVERSE(1 << 9);

	/**
	 * Value associated with an enum.
	 */
	private long mValue;

	/**
	 */
	private SsRouteType(long value)
	{
		this.mValue = value;
	}

	/**
	 * @return Value of the enum.
	 */
	public long getValue()
	{
		return this.mValue;
	}

}
