package me.gabeg.sicksends.problem.type

/**
 * Types of climbing techniques that a person may utilize.
 *
 * @param  value  Value associated with an enum.
 */
enum class SsClimbingTechniqueType(val value : Long)
{

	ARM_BAR(1 shl 0),
	BAT_HANG(1 shl 1),
	BICYCLE(1 shl 2),
	CAMPUS(1 shl 3),
	DROP_KNEE(1 shl 4),
	DYNO(1 shl 5),
	FIGURE_FOUR(1 shl 6),
	FINGER_JAM(1 shl 7),
	FIST_JAM(1 shl 8),
	GASTON(1 shl 9),
	HAND_JAM(1 shl 10),
	HEEL_HOOK(1 shl 11),
	KNEE_BAR(1 shl 12),
	LAYBACK(1 shl 13),
	MANTLE(1 shl 14),
	ROSE_MOVE(1 shl 15),
	SMEAR(1 shl 16),
	STEM(1 shl 17),
	TOE_HOOK(1 shl 18)

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

}