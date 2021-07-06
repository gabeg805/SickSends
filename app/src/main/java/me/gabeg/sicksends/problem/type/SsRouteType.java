package me.gabeg.sicksends.problem.type;

/**
 * Types of climbing features on a route that a person may encounter.
 */
public enum SsRouteType
{


	ARETE(1 << 0),
	CHIMNEY(1 << 1),
	CRACK(1 << 2),
	DIHEDRAL(1 << 3),
	FACE(1 << 4),
	HIGH_BALL(1 << 5),
	OVERHANG(1 << 6),
	ROOF(1 << 7),
	SLAB(1 << 8),
	TOP_OUT(1 << 9),
	TRAVERSE(1 << 10);



	//FACE(1 << 3),
	//OVERHANG(1 << 5),
	//ROOF(1 << 6),
	//SLAB(1 << 7),

	//ARETE(1 << 10),
	//CHIMNEY(1 << 0),
	//DIHEDRAL(1 << 2),
	//CRACK(1 << 1),

	//TRAVERSE(1 << 9);

	//// Only boulder
	//HIGH_BALL(1 << 4),

	//// Assume every boulder is topout but not necessarily every trad
	//TOP_OUT(1 << 8),

	/**
	 * Value associated with an enum.
	 */
	private final long mValue;

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
