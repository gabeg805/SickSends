package me.gabeg.sicksends.addproblem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.shared.SsSharedBaseDataStore
import me.gabeg.sicksends.shared.getHowDidItFeelScaleName

/**
 * Add problem view model.
 */
abstract class SsAddProblemViewModel<T : SsGenericProblem>(
	private val savedStateHandle: SavedStateHandle,
	val problem : T) : ViewModel()
{

	/**
	 * Number of questions.
	 */
	var length = 10

	/**
	 * Visibility of all questions.
	 */
	val allVisibility = MutableList(length) { mutableStateOf(false) }

	/**
	 * List of attributes.
	 */
	var questions = MutableList<Any?>(length) { null }

	/**
	 * The current attribute being set for the problem.
	 */
	var current : Any? = null

	/**
	 * Get the initial grading system.
	 *
	 * @return The initial grading system.
	 */
	@Composable
	fun getInitialGradingSystem(dataStore: SsSharedBaseDataStore) : String
	{
		val defaultGradingSystem = dataStore.getDefaultGradingSystem()
		val problemGradingSystem = problem.gradingSystem

		return if (problemGradingSystem.isEmpty()) defaultGradingSystem
			else problemGradingSystem
	}

	/**
	 * Get the initial how did it feel value.
	 *
	 * @return The initial how did it feel value.
	 */
	@Composable
	fun getInitialHowDidItFeelScale() : String
	{
		return getHowDidItFeelScaleName(problem.howDidItFeelScale)
	}

	/**
	 * Get the initial name.
	 *
	 * @return The initial name.
	 */
	@Composable
	fun getInitialName() : String
	{
		return problem.name ?: ""
	}

	/**
	 * Get the initial note.
	 *
	 * @return The initial note.
	 */
	@Composable
	fun getInitialNote() : String
	{
		return problem.note ?: ""
	}

	/**
	 * Get the initial number of attempts.
	 *
	 * @return The initial number of attempts.
	 */
	@Composable
	fun getInitialNumAttempt() : String
	{
		val numAttempt = problem.numAttempt

		return numAttempt?.toString() ?: ""
	}

	/**
	 * Get the initial grading system that should be shown.
	 */
	/**
	 * Get the visibility of a question.
	 *
	 * @param index Index of a question.
	 *
	 * @return Mutable boolean of the visibility of a question, or null if the
	 *     index is invalid.
	 */
	fun getVisible(index : Int) : MutableState<Boolean>
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return mutableStateOf(false)
		}

		// Get the visibility
		return allVisibility[index]
	}

	/**
	 * Get the visibility of a question.
	 *
	 * @param index Index of a question.
	 *
	 * @return Mutable boolean of the visibility of a question, or null if the
	 *     index is invalid.
	 */
	fun getVisibility(index : Int) : MutableState<Boolean>?
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return null
		}

		// Get the visibility
		return allVisibility[index]
	}

	/**
	 * Hide a question.
	 *
	 * @param index Index of a question.
	 */
	fun hide(index : Int)
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return
		}

		//// Get the question's visibility
		//val visibility = allVisibility[index]

		//// Hide the question if it is visible
		//if (visibility.value)
		//{
		//	visibility.value = false
		//}

		allVisibility[index].value = false
	}

	/**
	 * Hide all questions.
	 */
	fun hideAll(ignore : List<Int> = listOf())
	{
		// Iterate over each question and hide each one
		for (i in allVisibility.indices)
		{

			// Ignore this index
			if (i in ignore)
			{
				continue
			}

			// Hide this index
			hide(i)
		}
	}

	/**
	 * Check if a question is answered or not.
	 *
	 * @param index Index of a question.
	 *
	 * @return True if the question is answered, and False otherwise.
	 */
	fun isAnswered(index : Int) : Boolean
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return false
		}

		// Check if the question is answered
		return questions.getOrNull(index) != null
	}

	/**
	 * Check if an index is valid.
	 *
	 * @param index Index of a question.
	 *
	 * @return True if the index is valid, and False otherwise.
	 */
	fun isValidIndex(index : Int) : Boolean
	{
		return (index >= 0) && (index < length)
	}

	/**
	 * Check if a question is visible or not.
	 *
	 * @param index Index of a question.
	 *
	 * @return True if the question is visible, and False otherwise.
	 */
	fun isVisible(index : Int) : Boolean
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return false
		}

		// Get the visibility
		return allVisibility[index].value
	}

	/**
	 * Show a question.
	 *
	 * @param index Index of a question.
	 */
	fun show(index : Int)
	{

		// Invalid index
		if (!isValidIndex(index))
		{
			return
		}

		//// Get the question's visibility
		//val visibility = allVisibility[index]

		//// Show the question if it is hidden
		//if (!visibility.value)
		//{
		//	visibility.value = true
		//}

		allVisibility[index].value = true
	}

	/**
	 * Only show a particular question and hide all the others.
	 *
	 * @param index Index of a question.
	 */
	fun showOnly(index : Int)
	{
		println("Hide all : $index")
		hideAll(ignore = listOf(index))
		println("Show index : $index")
		show(index)
	}

}
