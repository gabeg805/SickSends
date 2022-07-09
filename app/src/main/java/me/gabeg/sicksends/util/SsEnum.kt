package me.gabeg.sicksends.util

import java.util.*

/**
 * Toggle the enum in a set. If it is present, remove it, and if it is not
 * present, add it.
 *
 * @param enum Enum to toggle in a set.
 */
fun <T : Enum<T>> EnumSet<T>.toggle(enum : T)
{
	// Remove it
	if (this.contains(enum))
	{
		this.remove(enum)
	}
	// Add it
	else
	{
		this.add(enum)
	}
}

/**
 * Convert a set of enums to their normal names.
 *
 * @param set An enum set.
 * @param allNames All possible names for the enum type.
 *
 * @return A list of enums with their normal names.
 */
inline fun <reified T : Enum<T>> EnumSet<T>.toNames(allNames : List<String>) : List<String>
{
	// Store all the enum names
	val names = mutableListOf<String>()

	// Get all enums
	val allEnums = enumValues<T>()

	// Iterate over each enum in the set
	//set.forEach {
	this.forEach {

		// Find the index of the enum
		val index = allEnums.indexOf(it)

		// Get the name and add it
		names.add(allNames[index])
	}

	return names
}
