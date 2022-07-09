package me.gabeg.sicksends.problem.type

import java.util.*

/**
 * Types of climbing techniques that a person may utilize.
 *
 * TODO: Change into hand and foot work?
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
	TOE_HOOK(1 shl 18);

	////BACK_STEP(1 << 1),
	////BUMP(1 << 4),
	////DEADPOINT(1 << 6),
	////EDGING(1 << 9),
	////FLAG(1 << 13),
	////FOOT_JAM(1 << 14),
	////HEEL_TOE_CAM(1 << 18),
	////LOCKOFF(1 << 21),

	companion object
	{

		/**
		 * Create an empty EnumSet of this type.
		 *
		 * @return An empty EnumSet of this type.
		 */
		fun emptySet() : EnumSet<SsClimbingTechniqueType>
		{
			return EnumSet.noneOf(SsClimbingTechniqueType::class.java)
		}

	}

}