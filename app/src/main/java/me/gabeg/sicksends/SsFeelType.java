package me.gabeg.sicksends;

/**
 * Types for how the climb felt on a scale from easy to hard.
 */
public enum SsFeelType
{

	EASY(1),
	PRETTY_EASY(2),
	NORMAL(3),
	PRETTY_HARD(4),
	HARD(5)

	/**
	 * Value associated with an enum.
	 */
	private long mValue;

	/**
	 */
	private SsFeelType(long value)
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
