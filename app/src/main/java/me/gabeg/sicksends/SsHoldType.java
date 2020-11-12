package me.gabeg.sicksends;

/**
 * Types of climbing holds that a person may encounter.
 */
public enum SsHoldType
{

	ARETE(1 << 0),
	CRIMP(1 << 1),
	HORN(1 << 2),
	JUG(1 << 3),
	PINCH(1 << 4),
	POCKET(1 << 5),
	SIDE_PULL(1 << 6),
	SLOPER(1 << 7),
	UNDERCLING(1 << 8),
	VOLUME(1 << 9);

	/**
	 * Value associated with an enum.
	 */
	private long mValue;

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
