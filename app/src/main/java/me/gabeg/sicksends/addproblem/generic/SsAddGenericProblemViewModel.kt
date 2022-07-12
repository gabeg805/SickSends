package me.gabeg.sicksends.addproblem.generic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.shared.SsSharedBaseClimbingDataStore
import me.gabeg.sicksends.shared.getYesOrNo

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

	// YOOOO
	var hasGradingSystemBeenSelected = false

	/**
	 * Questions.
	 */
	//val gradingSystemQuestion = "What grading system was used?"
	//val gradeQuestion = "What was the grade?"
	//val perceivedGradeQuestion = "What grade do you think it was?"
	//val howDidItFeelQuestion = "How did it feel?"

	////val numAttemptQuestion = "How many attempts did you do?"
	//val numAttemptQuestion = "How many times did you attempt it?"
	////val projectQuestion = "Are you projecting this problem?"
	//val projectQuestion = "Is this a project?"
	////val outdoorQuestion = "Was the problem outdoors?"
	//val outdoorQuestion = "Was it outdoors?"
	////val flashQuestion = "Did you flash the problem?"
	val flashQuestion = "Was this a flash?"

	//val nameQuestion = "What was the name?"
	////val nameQuestion = "What is the name of the problem?"
	////val noteQuestion = "Do you have any notes for the climb?"
	//val noteQuestion = "Do you have any notes?"

	////val holdQuestion = "What type of holds did the problem have?"
	//val holdQuestion = "What type of holds were there?"
	//val wallFeatureQuestion = "What features did the wall have?"
	//val climbingTechniqueQuestion = "What type of techniques did you use?"

	/**
	 * Indices.
	 */
	var grade = QuestionIndex(
		index = 0,
		questions = listOf(
			"What grading system was used?",
			"What was the grade?",
			"What grade do you think it was?",
			"How did it feel?"))
	var name = QuestionIndex(
		index = 0,
		question = "What was the name of the climb?")
	var numAttempt = QuestionIndex(
		index = 0,
		question = "How many attempts did you do?")
	var project = QuestionIndex(
		index = 0,
		question = "Was this a project?")
	var outdoor = QuestionIndex(
		index = 0,
		question = "Was this outdoors?")
	var location = QuestionIndex(
		index = 0,
		question = "Where was it located?")
	var note = QuestionIndex(
		index = 0,
		question = "Would you like to write down any notes?")
	var hold = QuestionIndex(
		index = 0,
		question = "What type of holds were there?")
	var wallFeature = QuestionIndex(
		index = 0,
		question = "What features did the wall have?")
	var climbingTechnique = QuestionIndex(
		index = 0,
		question = "What type of techniques did you use?")

	/**
	 * Index of a question. The class is a container for the value so that the
	 * object can be passed by reference to a function, and then its value can
	 * be changed, if need be.
	 *
	 * TODO: Can maybe move isAnswered() checks to here?
	 */
	data class QuestionIndex(
		var index : Int,
		val question : String = "",
		val questions : List<String> = listOf())

	/**
	 * Companion.
	 */
	companion object
	{

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

		/**
		 * Get the subtitle to show.
		 *
		 * @return The subtitle to show.
		 */
		fun getSubtitle(names : List<String>, question : String, visible : Boolean) : String
		{
			return if (visible)
			{
				question
			}
			else
			{
				if (names.isNotEmpty())
				{
					names.joinToString()
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
			setupSize()

			// Defaults
			problem.gradingSystem = dataStore.getDefaultGradingSystem()
		}
	}

	/**
	 * Find the index of a question. If it is not set yet, find the next
	 * available index and set the value of the object.
	 *
	 * @return The index of a question.
	 */
	fun findIndex(question : QuestionIndex) : Int
	{
		if (question.index == 0)
		{
			question.index = findNextAvailableIndex()
		}

		return question.index
	}

	/**
	 * Find the next available index.
	 *
	 * @return The next available index.
	 */
	fun findNextAvailableIndex() : Int
	{
		return getAllIndices().maxBy { it.index }.index + 1
	}

	/**
	 * Get all the indices of each question.
	 *
	 * @return A list of all the indices of each question.
	 */
	fun getAllIndices() : List<QuestionIndex>
	{
		return listOf(grade, name, numAttempt, project, outdoor, location, note,
			hold, wallFeature, climbingTechnique)
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

		if (index == grade.index)
		{
			return this.problem.grade.isNotEmpty()
		}
		else if (index == name.index)
		{
			return this.problem.name?.isNotEmpty() ?: false
		}
		else if (index == numAttempt.index)
		{
			return (this.problem.isFlash != null) || (this.problem.numAttempt != null)
		}
		else if (index == project.index)
		{
			return this.problem.isProject != null
		}
		else if (index == outdoor.index)
		{
			return this.problem.isOutdoor != null
		}
		else if (index == location.index)
		{
			// TODO: Might want to check lat/lon or simply not null
			return this.problem.locationName?.isNotEmpty() ?: false
		}
		else if (index == note.index)
		{
			return this.problem.note?.isNotEmpty() ?: false
		}
		else if (index == hold.index)
		{
			return this.problem.hold.isNotEmpty()
		}
		else if (index == wallFeature.index)
		{
			return this.problem.wallFeature.isNotEmpty()
		}
		else if (index == climbingTechnique.index)
		{
			return this.problem.climbingTechnique.isNotEmpty()
		}
		else
		{
			return false
		}
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
	 * Find the number of questions.
	 */
	suspend fun findNumOfQuestions() : Int
	{
		// Determine if a question should be asked or not
		val askName = dataStore.getQuestionName()
		val askFlash = dataStore.getQuestionIsFlash()
		val askNumAttempt = dataStore.getQuestionNumAttempt()
		val askProject = dataStore.getQuestionIsProject()
		val askOutdoor = dataStore.getQuestionIsOutdoor()
		val askLocation = dataStore.getQuestionLocation()
		val askNote = dataStore.getQuestionNote()
		val askHold = dataStore.getQuestionHold()
		val askWallFeature = dataStore.getQuestionWallFeature()
		val askClimbingTechnique = dataStore.getQuestionClimbingTechnique()

		// All determinations for each question
		val allAsk = listOf(askName, askFlash, askNumAttempt, askProject,
			askOutdoor, askLocation, askNote, askHold, askWallFeature,
			askClimbingTechnique)

		// Number of questions. There should be at least one question, the grade
		var num = 1

		// Add up the number of times a question should be asked
		num += allAsk.count { it }

		return num
	}

	/**
	 * Find the size of the list of questions and answers.
	 */
	suspend fun setupSize()
	{
		//val indices = getAllIndices()

		//size = (indices.maxOrNull() ?: -1) + 1
		size = findNumOfQuestions()
		allVisibility = MutableList(size) { mutableStateOf(false) }
		//answers = MutableList<Any?>(size) { null }
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
