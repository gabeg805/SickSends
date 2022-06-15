package me.gabeg.sicksends.addproblem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.shared.SsSharedBaseClimbingDataStore
import me.gabeg.sicksends.shared.SsSharedBaseDataStore
import kotlin.math.roundToLong

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
	 * List of answers.
	 */
	var answers = MutableList<Any?>(length) { null }

	/**
	 * Questions.
	 */
	val nameQuestion = "What is the name of the climb?"
	val noteQuestion = "Do you have any notes for the climb?"
	val numAttemptQuestion = "How many attempts did you do?"

	/**
	 * The current attribute being set for the problem.
	 */
	var current : Any? = null

	/**
	 * Companion.
	 */
	companion object
	{

		/**
		 * Get the initial subtitle to show.
		 *
		 * @return The initial subtitle to show.
		 */
		fun getInitialSubtitle(initial: String, question: String): String
		{
			return if (initial.isEmpty()) question else initial
		}

		/**
		 * Get the subtitle to show.
		 *
		 * @return The subtitle to show.
		 */
		fun getSubtitle(text : String, question : String, visible : Boolean) : String
		{
			return if (visible || (text != question)) text else ""
		}

	}

	/**
	 * Get the subtitle for the grade section.
	 */
	fun getGradeSubtitle() : String
	{
		var gradingSystem = problem.gradingSystem
		var grade = problem.grade
		var feel = problem.howDidItFeel

		if (gradingSystem.isEmpty())
		{
			return ""
		}
		else if (grade.isEmpty())
		{
			return gradingSystem
		}
		else if (feel.isEmpty())
		{
			return "$gradingSystem  |  $grade"
		}
		else
		{
			return "$gradingSystem  |  $grade  |  $feel"
		}
	}

	/**
	 * Get the initial grading system.
	 *
	 * @return The initial grading system.
	 */
	// TODO: The initial problem should already be populated with the data store
	// defaults, so that this check against the data store does not need to
	// happen
	@Composable
	fun getInitialGradingSystem(dataStore: SsSharedBaseClimbingDataStore) : String
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
	fun getInitialHowDidItFeelScale() : String
	{
		return problem.howDidItFeel
	}

	/**
	 * Get the initial name.
	 *
	 * @return The initial name.
	 */
	fun getInitialName() : String
	{
		return problem.name ?: ""

		//val initial = problem.name ?: ""

		//return getInitialSubtitle(initial, nameQuestion)
	}

	/**
	 * Get the initial note.
	 *
	 * @return The initial note.
	 */
	fun getInitialNote() : String
	{
		return problem.note ?: ""

		//val initial = problem.note ?: ""

		//return getInitialSubtitle(initial, noteQuestion)
	}

	/**
	 * Get the initial number of attempts.
	 *
	 * @return The initial number of attempts.
	 */
	fun getInitialNumAttemptSubtitle() : String
	{
		val initial = problem.numAttempt?.toString() ?: ""

		return getInitialSubtitle(initial, numAttemptQuestion)
	}

	/**
	 * Number of attempts subtitle.
	 */
	fun getNumAttemptsSubtitle(text : String, visible : Boolean) : String
	{
		return getSubtitle(text, numAttemptQuestion, visible)
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
		return answers.getOrNull(index) != null
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
	 * Set the number of attempts from a text field value.
	 *
	 * @param fieldValue A text field value
	 *
	 * @return The number of attempts from a text field value.
	 */
	fun numAttemptsFromTextFieldValue(fieldValue: TextFieldValue) : Long?
	{
		var text = fieldValue.text
		var sanitizedText = text.replace(numAttemptQuestion, "")

		return if (sanitizedText.isEmpty())
			null
		else
			sanitizedText.toFloat().roundToLong()
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
