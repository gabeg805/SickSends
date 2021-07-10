package me.gabeg.sicksends.problem.type;

/**
 * Types of climbing holds that a person may encounter.
 */
public enum SsHoldType
{

	CRIMP(1 << 0),
	HORN(1 << 1),
	JUG(1 << 2),
	PINCH(1 << 3),
	POCKET(1 << 4),
	SLOPER(1 << 5),
	SIDE_PULL(1 << 6),
	UNDERCLING(1 << 7),
	VOLUME(1 << 8);
	// Volume only for indoor

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
