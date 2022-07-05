package me.gabeg.sicksends.problem.type

/**
 * Types of climbing features on a route that a person may encounter.
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
	TRAVERSE(1 shl 10)

	//FACE(1 << 3),
	//OVERHANG(1 << 5),
	//ROOF(1 << 6),
	//SLAB(1 << 7),
	//ARETE(1 << 10),
	//CHIMNEY(1 << 0),
	//DIHEDRAL(1 << 2),
	//CRACK(1 << 1),
	//TRAVERSE(1 << 9);
	//// Only boulder
	//HIGH_BALL(1 << 4),
	//// Assume every boulder is topout but not necessarily every trad
	//TOP_OUT(1 << 8),

}