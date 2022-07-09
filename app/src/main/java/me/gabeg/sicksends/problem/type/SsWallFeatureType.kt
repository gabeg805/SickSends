package me.gabeg.sicksends.problem.type

import java.util.*

/**
 * Types of climbing features on a route that a person may encounter.
 *
 * TODO: High ball and Topout are only boulder.
 * TODO: Assume every outdoor boulder is topout?
 *
 * @param  value  Value associated with an enum.
 */
enum class SsWallFeatureType(val value : Long)
{

	ARETE(1 shl 0),
	CHIMNEY(1 shl 1),
	CRACK(1 shl 2),
	DIHEDRAL(1 shl 3),
	FACE(1 shl 4),
	HIGH_BALL(1 shl 5),
	OVERHANG(1 shl 6),
	ROOF(1 shl 7),
	SLAB(1 shl 8),
	TOP_OUT(1 shl 9),
	TRAVERSE(1 shl 10);

	companion object
	{

		/**
		 * Create an empty EnumSet of this type.
		 *
		 * @return An empty EnumSet of this type.
		 */
		fun emptySet() : EnumSet<SsWallFeatureType>
		{
			return EnumSet.noneOf(SsWallFeatureType::class.java)
		}

	}

}