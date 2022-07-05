package me.gabeg.sicksends.addproblem.generic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.shared.SsSharedBaseClimbingDataStore
import me.gabeg.sicksends.shared.getYesOrNo
import me.gabeg.sicksends.shared.howDidItFeelToName
import kotlin.math.roundToLong

/**
 * Add problem view model.
 */
abstract class SsAddGenericProblemViewModel<out T : SsGenericProblem>(
	private val savedStateHandle: SavedStateHandle,
	val problem : T,
	val dataStore : SsSharedBaseClimbingDataStore) : ViewModel()
{

	/**
	 * Number of questions.
	 */
	var size = 0

	/**
	 * Visibility of all questions.
	 */
	var allVisibility = MutableList(size) { mutableStateOf(false) }

	/**
	 * List of answers.
	 */
	var answers = MutableList<Any?>(size) { null }

	// YOOOO
	var hasGradingSystemBeenSelected = false

	/**
	 * Questions.
	 */
	val gradingSystemQuestion = "What grading system was used?"
	val gradeQuestion = "What was the grade?"
	val perceivedGradeQuestion = "What grade do you think it was?"
	val howDidItFeelQuestion = "How did it feel?"
	val nameQuestion = "What is the name of the climb?"
	val noteQuestion = "Do you have any notes for the climb?"
	val numAttemptQuestion = "How many attempts did you do?"

	/**
	 * Indices.
	 */
	var gradeIndex = 0
	var nameIndex = 0
	var attemptIndex = 0
	var projectIndex = 0
	var outdoorIndex = 0
	var locationIndex = 0
	var noteIndex = 0

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
			return if (visible)
			{
				question
			}
			else
			{
				if (text.isNotEmpty())
				{
					text
				}
				else
				{
					""
				}
			}
			//return if (visible || (text != question)) text else ""
		}

		/**
		 * Get the subtitle to show.
		 *
		 * @return The subtitle to show.
		 */
		@Composable
		fun getSubtitle(state : Boolean?, question : String, visible : Boolean) : String
		{
			return if (visible)
			{
				question
			}
			else
			{
				if (state != null)
				{
					getYesOrNo(state)
				}
				else
				{
					""
				}
			}
		}

	}

	/**
	 * Constructor.
	 */
	init
	{
		viewModelScope.launch {
			setupIndices()
			setupSize()

			// Defaults
			problem.gradingSystem = dataStore.getDefaultGradingSystem()
		}
	}

	/**
	 * Get all the indices of each question.
	 *
	 * @return A list of all the indices of each question.
	 */
	fun getAllIndices() : List<Int>
	{
		return listOf(gradeIndex, nameIndex, attemptIndex, projectIndex,
			outdoorIndex, locationIndex, noteIndex)
	}

	/**
	 * Get the subtitle for the grade section.
	 */
	fun getGradeSubtitle() : String
	{
		val gradingSystem = problem.gradingSystem
		val grade = problem.grade
		val howDidItFeel = problem.howDidItFeelName
		val perceivedGrade = problem.perceivedGrade ?: ""

		if (gradingSystem.isEmpty())
		{
			return ""
		}
		else if (grade.isEmpty())
		{
			return gradingSystem
		}
		else if (howDidItFeel.isEmpty() && perceivedGrade.isEmpty())
		{
			return "$gradingSystem  |  $grade"
		}
		else
		{
			return "$gradingSystem  |  $grade  |  " +
				if (howDidItFeel.isNotEmpty()) howDidItFeel else perceivedGrade
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
	fun getInitialGradingSystem() : String
	{
		return problem.gradingSystem

		//val defaultGradingSystem = dataStore.observeDefaultGradingSystem()
		//val problemGradingSystem = problem.gradingSystem

		//return if (problemGradingSystem.isEmpty()) defaultGradingSystem
		//	else problemGradingSystem
	}

	/**
	 * Get the initial how did it feel value.
	 *
	 * @return The initial how did it feel value.
	 */
	//fun getInitialHowDidItFeelScale() : String
	//{
	//	return problem.howDidItFeelName
	//}

	/**
	 * Get the initial name.
	 *
	 * @return The initial name.
	 */
	//fun getInitialName() : String
	//{
	//	return problem.name ?: ""

	//	//val initial = problem.name ?: ""

	//	//return getInitialSubtitle(initial, nameQuestion)
	//}

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
		return (index >= 0) && (index < size)
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
	 * Setup the indices that each question should be shown at.
	 */
	suspend fun setupIndices()
	{
		println("Setting up indices!")
		val askName = dataStore.getQuestionName()
		val askFlash = dataStore.getQuestionIsFlash()
		val askNumAttempt = dataStore.getQuestionNumAttempt()
		val askProject = dataStore.getQuestionIsProject()
		val askOutdoor = dataStore.getQuestionIsOutdoor()
		val askLocation = dataStore.getQuestionLocation()
		val askNote = dataStore.getQuestionNote()

		if (askName)
		{
			nameIndex++
			attemptIndex++
			projectIndex++
			outdoorIndex++
			locationIndex++
			noteIndex++
		}

		if (askFlash || askNumAttempt)
		{
			attemptIndex++
			projectIndex++
			outdoorIndex++
			locationIndex++
			noteIndex++
		}

		if (askProject)
		{
			projectIndex++
			outdoorIndex++
			locationIndex++
			noteIndex++
		}

		if (askOutdoor)
		{
			outdoorIndex++
			locationIndex++
			noteIndex++
		}

		if (askLocation)
		{
			locationIndex++
			noteIndex++
		}

		if (askNote)
		{
			noteIndex++
		}
	}

	/**
	 * Find the size of the list of questions and answers.
	 */
	fun setupSize()
	{
		val indices = getAllIndices()

		size = (indices.maxOrNull() ?: -1) + 1
		allVisibility = MutableList(size) { mutableStateOf(false) }
		answers = MutableList<Any?>(size) { null }
		println("Setting up size! $size")
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
