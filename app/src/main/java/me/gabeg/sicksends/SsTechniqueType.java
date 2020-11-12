package me.gabeg.sicksends;

/**
 * Types of climbing techniques that a person may utilize.
 */
public enum SsTechniqueType
{

	ARM_BAR(1 << 0),
	BACK_STEP(1 << 1),
	BAT_HANG(1 << 2),
	BICYCLE(1 << 3),
	BUMP(1 << 4),
	CAMPUS(1 << 5),
	DEADPOINT(1 << 6),
	DROP_KNEE(1 << 7),
	DYNO(1 << 8),
	EDGING(1 << 9),
	FIGURE_FOUR(1 << 10),
	FINGER_JAM(1 << 11),
	FIST_JAM(1 << 12),
	FLAG(1 << 13),
	FOOT_JAM(1 << 14),
	GASTON(1 << 15),
	HAND_JAM(1 << 16),
	HEEL_HOOK(1 << 17),
	HEEL_TOE_CAM(1 << 18),
	KNEE_BAR(1 << 19),
	LAYBACK(1 << 20),
	LOCKOFF(1 << 21),
	MANTLE(1 << 22),
	ROSE_MOVE(1 << 23),
	SMEARING(1 << 24),
	STEMMING(1 << 25),
	TOE_HOOK(1 << 26);

	/**
	 * Value associated with an enum.
	 */
	private long mValue;

	/**
	 */
	private SsTechniqueType(long value)
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
