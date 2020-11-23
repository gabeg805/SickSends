package me.gabeg.sicksends;

/**
 * Types of climbing techniques that a person may utilize.
 */
public enum SsTechniqueType
{

	ARM_BAR(1 << 0),
	BAT_HANG(1 << 1),
	BICYCLE(1 << 2),
	CAMPUS(1 << 3),
	DROP_KNEE(1 << 4),
	DYNO(1 << 5),
	FIGURE_FOUR(1 << 6),
	FINGER_JAM(1 << 7),
	FIST_JAM(1 << 8),
	GASTON(1 << 9),
	HAND_JAM(1 << 10),
	HEEL_HOOK(1 << 11),
	KNEE_BAR(1 << 12),
	LAYBACK(1 << 13),
	MANTLE(1 << 14),
	ROSE_MOVE(1 << 15),
	SMEARING(1 << 16),
	STEMMING(1 << 17),
	TOE_HOOK(1 << 18);



	//// CHange this shit into hand work and foot work
	//HEEL_HOOK(1 << 17),
	//LAYBACK(1 << 20),
	//MANTLE(1 << 22),
	//SMEARING(1 << 24),
	//TOE_HOOK(1 << 26);

	//BICYCLE(1 << 3),
	//DROP_KNEE(1 << 7),
	//DYNO(1 << 8),

	//KNEE_BAR(1 << 19),
	//CAMPUS(1 << 5),

	//GASTON(1 << 15),

	//HAND_JAM(1 << 16),
	//FINGER_JAM(1 << 11),
	//FIST_JAM(1 << 12),

	//STEMMING(1 << 25),

	//ARM_BAR(1 << 0),

	////BACK_STEP(1 << 1),
	////BUMP(1 << 4),
	////DEADPOINT(1 << 6),
	////EDGING(1 << 9),
	////FLAG(1 << 13),
	////FOOT_JAM(1 << 14),
	////HEEL_TOE_CAM(1 << 18),
	////LOCKOFF(1 << 21),

	//FIGURE_FOUR(1 << 10),

	//ROSE_MOVE(1 << 23),
	//BAT_HANG(1 << 2),

	/**
	 * Value associated with an enum.
	 */
	private final long mValue;

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
