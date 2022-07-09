package me.gabeg.sicksends.db.typeconverter

import androidx.room.TypeConverter
import me.gabeg.sicksends.problem.type.SsHoldType
import java.util.*

class SsHoldTypeConverter
{

	/**
	 * Convert a value to an enum set.
	 *
	 * @param value A value that corresponds to one or more enums.
	 *
	 * @returns An enum set.
	 */
	@TypeConverter
	fun fromLong(value : Long?) : EnumSet<SsHoldType>
	{
		val set = SsHoldType.emptySet()

		// Value is null. Return an empty enum set
		if (value == null)
		{
			return set
		}

		// Get each enum
		val allEnums = SsHoldType.values()

		// Iterate over each value
		allEnums.forEach {

			// Enum contains this value. Add it to the set
			if ((it.value and value) != 0L)
			{
				set.add(it)
			}

		}

		return set
	}

	/**
	 * Convert a value to an enum set.
	 *
	 * @param value A value that corresponds to one or more enums.
	 *
	 * @returns An enum set.
	 */
	@TypeConverter
	fun enumSetToLong(set : EnumSet<SsHoldType>) : Long?
	{
		// The enum set is empty. Return null
		if (set.isEmpty())
		{
			return null
		}

		// The cumulative value of each enum
		var value = 0L

		// Save each value of each enum
		set.forEach {
			value += it.value
		}

		return value
	}

}