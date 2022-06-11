package me.gabeg.sicksends.datetime

import me.gabeg.sicksends.db.generic.SsGenericProblem
import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert a problem to a date, or relative date.
 */
fun convertProblemToRelativeDate(problem: SsGenericProblem) : String
{

	// No problem entered
	if (problem == null)
	{
		return ""
	}

	// Get today's date and the date of the problem
	var today = Date()
	var date = Date(problem.timestamp*1000)

	// Compare the two dates to see what the date text should say
	if (isSameDate(today, date))
	{
		return "Today"
	}
	else if (isDayBeforeDate(today, date))
	{
		return "Yesterday"
	}
	else if (isSameYear(today, date))
	{
		return SimpleDateFormat("EEE, MMM d").format(date)
	}
	else
	{
		return SimpleDateFormat("MMM d, yyyy").format(date)
	}
}

/**
 * Check if a date occurs before another date.
 */
fun isDayBeforeDate(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)
	cal2.add(Calendar.DAY_OF_MONTH, 1)

	// Get the year, month, and day
	// TODO: Can I just do this with year and day-of-year?
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)
	val month1 = cal1.get(Calendar.MONTH)
	val month2 = cal2.get(Calendar.MONTH)
	val day1 = cal1.get(Calendar.DAY_OF_MONTH)
	val day2 = cal2.get(Calendar.DAY_OF_MONTH)

	return (year1 == year2) && (month1 == month2) && (day1 == day2)
}

/**
 * Check if two dates occur on the same date.
 */
fun isSameDate(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)

	// Get the year, month, and day
	// TODO: Can I just do this with year and day-of-year?
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)
	val month1 = cal1.get(Calendar.MONTH)
	val month2 = cal2.get(Calendar.MONTH)
	val day1 = cal1.get(Calendar.DAY_OF_MONTH)
	val day2 = cal2.get(Calendar.DAY_OF_MONTH)

	return (year1 == year2) && (month1 == month2) && (day1 == day2)
}

/**
 * Check if two dates occur in the same year.
 */
fun isSameYear(date1 : Date, date2 : Date) : Boolean
{

	// Get calendar instances
	val cal1 = Calendar.getInstance()
	val cal2 = Calendar.getInstance()

	// Setup the calendars
	cal1.setTime(date1)
	cal2.setTime(date2)

	// Get the year
	val year1 = cal1.get(Calendar.YEAR)
	val year2 = cal2.get(Calendar.YEAR)

	return (year1 == year2)
}
