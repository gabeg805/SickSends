package me.gabeg.sicksends.problem.type

/**
 * Types of climbing holds that a person may encounter.
 *
 * TODO: Volume only for indoor
 *
 * @param  value  Value associated with an enum.
 */
enum class SsHoldType(val value : Long)
{

	CRIMP(1 shl 0),
	HORN(1 shl 1),
	JUG(1 shl 2),
	PINCH(1 shl 3),
	POCKET(1 shl 4),
	SLOPER(1 shl 5),
	SIDE_PULL(1 shl 6),
	UNDERCLING(1 shl 7),
	VOLUME(1 shl 8)

}