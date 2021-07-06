package me.gabeg.sicksends.problem.type;

/**
 * Types of climbing holds that a person may encounter.
 */
public enum SsHoldType
{

	CRIMP(1 << 0),
	JUG(1 << 1),
	PINCH(1 << 2),
	POCKET(1 << 3),
	SLOPER(1 << 4);



	//// Maybe have arete only for outdoor
	//// In the heptagon figure, I don't really need arete, and same goes volume
	//// Horn can go
	//// Maybe just do a pentagon for shapes
	////ARETE(1 << 0),
	//CRIMP(1 << 1),
	////HORN(1 << 2),
	//JUG(1 << 3),
	//PINCH(1 << 4),
	//POCKET(1 << 5),
	////SIDE_PULL(1 << 6),
	//SLOPER(1 << 7),
	////UNDERCLING(1 << 8),
	////VOLUME(1 << 9);
	//// Volume only for indoor

	/**
	 * Value associated with an enum.
	 */
	private final long mValue;

	/**
	 */
	private SsHoldType(long value)
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
