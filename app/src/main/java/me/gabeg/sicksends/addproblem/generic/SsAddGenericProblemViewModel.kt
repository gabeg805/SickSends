package me.gabeg.sicksends.addproblem.generic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
	var gradingSystem = QuestionIndex(
		index = 0,
		title = "Grading System",
		question = "What was the grading system?")

	var grade = QuestionIndex(
		index = 0,
		title = "Grade",
		question = "What was the grade?")
		//questions = listOf(
		//	"What grading system was used?",
		//	"What was the grade?",
		//	"What grade do you think it was?",
		//	"How did it feel?"))

	var howDiditFeel = QuestionIndex(
		index = 0,
		title = "Thoughts",
		question = "How did it feel?")

	var perceivedGrade = QuestionIndex(
		index = 0,
		question = "What grade do you think it was?")

	var name = QuestionIndex(
		index = 0,
		title = "Name",
		question = "What was the name of the climb?")

	var flash = QuestionIndex(
		index = 0,
		title = "Flash",
		question = "Was this a flash?")

	var numAttempt = QuestionIndex(
		index = 0,
		title = "Attempts",
		question = "How many attempts did you do?")

	var project = QuestionIndex(
		index = 0,
		title = "Project",
		question = "Was this a project?")

	var outdoor = QuestionIndex(
		index = 0,
		title = "Outdoors",
		question = "Was this outdoors?")

	var location = QuestionIndex(
		index = 0,
		question = "Where was it located?")

	var note = QuestionIndex(
		index = 0,
		title = "Notes",
		question = "Would you like to write down any notes?")

	var hold = QuestionIndex(
		index = 0,
		title = "Holds",
		question = "What type of holds were there?")

	var wallFeature = QuestionIndex(
		index = 0,
		title = "Wall Features",
		question = "What features did the wall have?")

	var climbingTechnique = QuestionIndex(
		index = 0,
		title = "Climbing Techniques",
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
		val title : String = "",
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
	 * Find the currently visible index.
	 *
	 * @return The currently visible index. If unable to find a visible index,
	 *         -1 is returned.
	 */
	fun findCurrentVisibleIndex() : Int
	{
		// Find the visible index
		allVisibility.forEachIndexed { index, state ->
			if (state.value)
			{
				return index
			}
		}

		// Unable to find any visible index
		return -1
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
	 * Find the number of questions.
	 */
	suspend fun findNumOfQuestions() : Int
	{
		// Determine if a question should be asked or not
		val askHowDidItFeel = dataStore.getQuestionHowDidItFeel()
		val askPerceivedGrade = dataStore.getQuestionPerceivedGrade()
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
		val allAsk = listOf(askHowDidItFeel, askPerceivedGrade, askName,
			askFlash, askNumAttempt, askProject, askOutdoor, askLocation,
			askNote, askHold, askWallFeature, askClimbingTechnique)

		// Number of questions. There should be at least twos questions:
		// Grade and grading system
		var num = 2

		// Add up the number of times a question should be asked
		num += allAsk.count { it }

		return num
	}

	/**
	 * Get the list of all grades for a type of grading system.
	 *
	 * @param gradingSystem A grading system.
	 *
	 * @return A list of all grades for a type of grading system.
	 */
	@Composable
	abstract fun getAllGrades(gradingSystem : String) : List<String>

	/**
	 * Get all the indices of each question.
	 *
	 * @return A list of all the indices of each question.
	 */
	fun getAllIndices() : List<QuestionIndex>
	{
		return listOf(gradingSystem, grade, howDiditFeel, perceivedGrade, name,
			flash, numAttempt, project, outdoor, location, note, hold,
			wallFeature, climbingTechnique)
	}

	/**
	 * Get the subtitle for the grade section.
	 */
	//fun getGradeSubtitle() : String
	//{
	//	val gradingSystem = problem.gradingSystem
	//	val grade = problem.grade
	//	val howDidItFeel = problem.howDidItFeelName
	//	val perceivedGrade = problem.perceivedGrade ?: ""

	//	if (gradingSystem.isEmpty())
	//	{
	//		return ""
	//	}
	//	else if (grade.isEmpty())
	//	{
	//		return gradingSystem
	//	}
	//	else if (howDidItFeel.isEmpty() && perceivedGrade.isEmpty())
	//	{
	//		return "$gradingSystem  |  $grade"
	//	}
	//	else
	//	{
	//		return "$gradingSystem  |  $grade  |  " +
	//			if (howDidItFeel.isNotEmpty()) howDidItFeel else perceivedGrade
	//	}
	//}

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

		if (index == gradingSystem.index)
		{
			return this.problem.gradingSystem.isNotEmpty()
		}
		else if (index == grade.index)
		{
			return this.problem.grade.isNotEmpty()
		}
		else if (index == howDiditFeel.index)
		{
			return this.problem.howDidItFeel != null
		}
		else if (index == perceivedGrade.index)
		{
			return this.problem.perceivedGrade?.isNotEmpty() ?: false
		}
		else if (index == name.index)
		{
			return this.problem.name?.isNotEmpty() ?: false
		}
		else if (index == numAttempt.index)
		{
			return this.problem.numAttempt != null
		}
		else if (index == flash.index)
		{
			return this.problem.isFlash != null
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
			return this.problem.hasHold
			//return this.problem.hold.isNotEmpty()
		}
		else if (index == wallFeature.index)
		{
			return this.problem.hasWallFeature
			//return this.problem.wallFeature.isNotEmpty()
		}
		else if (index == climbingTechnique.index)
		{
			return this.problem.hasClimbingTechnique
			//return this.problem.climbingTechnique.isNotEmpty()
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

		println("Hide all : $index")
		hideAll(ignore = listOf(index))
		println("Show     : $index")

		allVisibility[index].value = true
	}

	/**
	 * Show the next question.
	 */
	fun showNext()
	{
		// Find the currently visible question
		val index = findCurrentVisibleIndex()

		// Hide this index if there are no other questions left
		if (index+1 >= size)
		{
			hide(index)
		}

		// Show the next question
		show(index+1)
	}

	/**
	 * Show the previous question.
	 */
	fun showPrevious()
	{
		// Find the currently visible question
		val index = findCurrentVisibleIndex()

		// Show the previous question
		show(index-1)
	}

	/**
	 * Only show a particular question and hide all the others.
	 *
	 * @param index Index of a question.
	 */
	//fun showOnly(index : Int)
	//{
	//	println("Hide all : $index")
	//	hideAll(ignore = listOf(index))
	//	println("Show index : $index")
	//	show(index)
	//}

	/**
	 * Start showing things with the view model.
	 */
	@Composable
	fun startShow()
	{
		LaunchedEffect(true)
		{
			val askGradingSystem = problem.gradingSystem.isEmpty()
			val index = if (askGradingSystem) 0 else 1

			println("Showing $index index!")
			show(index)
		}
	}

}
