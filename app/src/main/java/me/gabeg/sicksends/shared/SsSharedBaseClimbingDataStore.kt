package me.gabeg.sicksends.shared

import android.content.Context
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking


/**
 * Base data store class for climbing.
 */
abstract class SsSharedBaseClimbingDataStore(context : Context)
	: SsSharedBaseDataStore(context)
{

	/**
	 * Key names.
	 */
	val KEY_DEFAULT_GRADING_SYSTEM      = "key_default_grading_system"
	val KEY_WILL_CLIMB                  = "key_will_climb"
	val KEY_WILL_GRADE_WITH             = "key_will_grade_with_"
	val KEY_QUESTION_PERCEIVED_GRADE    = "key_question_perceived_grade"
	val KEY_QUESTION_HOW_DID_IT_FEEL    = "key_question_how_did_it_feel"
	val KEY_QUESTION_NAME               = "key_question_name"
	val KEY_QUESTION_NUM_ATTEMPT        = "key_question_num_attempt"
	val KEY_QUESTION_IS_FLASH           = "key_question_is_flash"
	val KEY_QUESTION_IS_PROJECT         = "key_question_is_project"
	val KEY_QUESTION_IS_OUTDOOR         = "key_question_is_outdoor"
	val KEY_QUESTION_LOCATION           = "key_question_location"
	val KEY_QUESTION_LOCATION_NAME      = "key_question_location_name"
	val KEY_QUESTION_WALL_FEATURE       = "key_question_route_feature_type"
	val KEY_QUESTION_HOLD               = "key_question_hold_type"
	val KEY_QUESTION_CLIMBING_TECHNIQUE = "key_question_climbing_technique_type"
	val KEY_QUESTION_MEDIA_PATH         = "key_question_media_path"
	val KEY_QUESTION_NOTE               = "key_question_note"

	/**
	 * Build the key that is used to determine if a grading system will be used
	 * or not.
	 *
	 * @param gradingSystem A grading system.
	 *
	 * @return A key to check if a grading system will be used or not.
	 */
	fun buildWillGradeWithKey(gradingSystem : String) : String
	{
		return KEY_WILL_GRADE_WITH + gradingSystem
	}

	/**
	 * Edit the default grading system.
	 *
	 * @param gradingSystem A grading system.
	 */
	suspend fun editDefaultGradingSystem(gradingSystem : String)
	{
		editString(KEY_DEFAULT_GRADING_SYSTEM, gradingSystem)
	}

	/**
	 * Edit whether the climbing technique question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionClimbingTechnique(value : Boolean)
	{
		editBoolean(KEY_QUESTION_CLIMBING_TECHNIQUE, value)
	}

	/**
	 * Edit whether the hold question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionHold(value : Boolean)
	{
		editBoolean(KEY_QUESTION_HOLD, value)
	}

	/**
	 * Edit whether the how did it feel question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionHowDidItFeel(value : Boolean)
	{
		editBoolean(KEY_QUESTION_HOW_DID_IT_FEEL, value)
	}

	/**
	 * Edit whether the is flash question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionIsFlash(value : Boolean)
	{
		editBoolean(KEY_QUESTION_IS_FLASH, value)
	}

	/**
	 * Edit whether the is project question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionIsProject(value : Boolean)
	{
		editBoolean(KEY_QUESTION_IS_PROJECT, value)
	}

	/**
	 * Edit whether the is outdoor question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionIsOutdoor(value : Boolean)
	{
		editBoolean(KEY_QUESTION_IS_OUTDOOR, value)
	}

	/**
	 * Edit whether the location question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionLocation(value : Boolean)
	{
		editBoolean(KEY_QUESTION_LOCATION, value)
	}

	/**
	 * Edit whether the media path question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionMediaPath(value : Boolean)
	{
		editBoolean(KEY_QUESTION_MEDIA_PATH, value)
	}

	/**
	 * Edit whether the name question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionName(value : Boolean)
	{
		editBoolean(KEY_QUESTION_NAME, value)
	}

	/**
	 * Edit whether the notes question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionNote(value : Boolean)
	{
		editBoolean(KEY_QUESTION_NOTE, value)
	}

	/**
	 * Edit whether the number of attempts question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionNumAttempt(value : Boolean)
	{
		editBoolean(KEY_QUESTION_NUM_ATTEMPT, value)
	}

	/**
	 * Edit whether the perceived grade question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionPerceivedGrade(value : Boolean)
	{
		editBoolean(KEY_QUESTION_PERCEIVED_GRADE, value)
	}

	/**
	 * Edit whether the wall feature type question should be asked or not.
	 *
	 * @param value Whether the question should be asked or not.
	 */
	suspend fun editQuestionWallFeature(value : Boolean)
	{
		editBoolean(KEY_QUESTION_WALL_FEATURE, value)
	}

	/**
	 * Edit whether the user will boulder or not.
	 *
	 * @param willClimb Whether or not the user will climb this type of
	 *                  climb.
	 */
	suspend fun editWillClimb(willClimb : Boolean)
	{
		editBoolean(KEY_WILL_CLIMB, willClimb)
	}

	/**
	 * Edit whether the user will use a particular bouldering grade or not.
	 *
	 * @param grade     Name of a grade.
	 * @param willGrade Flag indicating whether the user will use the grade or
	 *                  not.
	 */
	suspend fun editWillGradeWith(grade: String, willGrade: Boolean)
	{
		val key = buildWillGradeWithKey(grade)

		editBoolean(key, willGrade)
	}

	/**
	 * Get all the grading systems for a type of climbing.
	 *
	 * @return All the grading systems for a type of climbing.
	 */
	@Composable
	abstract fun getAllGradingSystems() : List<String>

	/**
	 * Get the name of the type of climbing this data store is for.
	 *
	 * @return The name of the type of climbing this data store is for.
	 */
	@Composable
	abstract fun getClimbName() : String

	/**
	 * Get the default grading system for a type of climbing.
	 *
	 * @return The default grading system for a type of climbing.
	 */
	suspend fun getDefaultGradingSystem() : String
	{
		val flow = getDefaultGradingSystemFlow()

		return getString(flow)
	}

	/**
	 * Get the default grading system for a type of climbing.
	 *
	 * @return The default grading system for a type of climbing.
	 */
	fun getDefaultGradingSystemFlow() : Flow<String>
	{
		return getStringFlow(KEY_DEFAULT_GRADING_SYSTEM)
	}

	/**
	 * Get whether the climbing technique question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionClimbingTechnique() : Boolean
	{
		val flow = getQuestionClimbingTechniqueFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the climbing technique question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionClimbingTechniqueFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_CLIMBING_TECHNIQUE, false)
	}

	/**
	 * Get whether the hold question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionHold() : Boolean
	{
		val flow = getQuestionHoldFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the hold question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionHoldFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_HOLD, false)
	}

	/**
	 * Get whether the how did it feel question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionHowDidItFeel() : Boolean
	{
		val flow = getQuestionHowDidItFeelFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the how did it feel question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionHowDidItFeelFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_HOW_DID_IT_FEEL, false)
	}

	/**
	 * Get whether the is flash question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionIsFlash() : Boolean
	{
		val flow = getQuestionIsFlashFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the is flash question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionIsFlashFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_IS_FLASH, false)
	}

	/**
	 * Get whether the is outdoor question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionIsOutdoor() : Boolean
	{
		val flow = getQuestionIsOutdoorFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the is outdoor question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionIsOutdoorFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_IS_OUTDOOR, false)
	}

	/**
	 * Get whether the is project question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionIsProject() : Boolean
	{
		val flow = getQuestionIsProjectFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the is project question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionIsProjectFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_IS_PROJECT, false)
	}

	/**
	 * Get whether the location question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionLocation() : Boolean
	{
		val flow = getQuestionLocationFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the location question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionLocationFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_LOCATION, false)
	}

	/**
	 * Get whether the media path question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionMediaPathFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_MEDIA_PATH, false)
	}

	/**
	 * Get whether the name question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionName() : Boolean
	{
		val flow = getQuestionNameFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the name question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionNameFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_NAME, false)
	}

	/**
	 * Get whether the notes question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionNote() : Boolean
	{
		val flow = getQuestionNoteFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the notes question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionNoteFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_NOTE, false)
	}

	/**
	 * Get whether the number of attempts question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionNumAttempt() : Boolean
	{
		val flow = getQuestionNumAttemptFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the number of attempts question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionNumAttemptFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_NUM_ATTEMPT, false)
	}

	/**
	 * Get whether the perceived grade question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionPerceivedGrade() : Boolean
	{
		val flow = getQuestionPerceivedGradeFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the perceived grade question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionPerceivedGradeFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_PERCEIVED_GRADE, false)
	}

	/**
	 * Get whether the wall feature question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	suspend fun getQuestionWallFeature() : Boolean
	{
		val flow = getQuestionWallFeatureFlow()

		return getBoolean(flow)
	}

	/**
	 * Get whether the wall feature question should be asked or not.
	 *
	 * @return Whether the question should be asked or not.
	 */
	fun getQuestionWallFeatureFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_QUESTION_WALL_FEATURE, false)
	}

	/**
	 * Get whether or not the user will boulder.
	 *
	 * @return True if the user will boulder, and False otherwise.
	 */
	suspend fun getWillClimb() : Boolean
	{
		val flow = getWillClimbFlow()

		return getBoolean(flow)
	}

	/**
	 * Get the flow of whether or not the user will boulder.
	 *
	 * @return Flow of if the user will boulder.
	 */
	fun getWillClimbFlow() : Flow<Boolean>
	{
		return getBooleanFlow(KEY_WILL_CLIMB)
	}

	/**
	 * Get whether the user will use a particular bouldering grade or not.
	 *
	 * @param grade Name of a grade.
	 *
	 * @return True if the user will use a particular bouldering grade, and
	 *         False otherwise.
	 */
	suspend fun getWillGradeWith(grade: String) : Boolean
	{
		val flow = getWillGradeWithFlow(grade)

		return getBoolean(flow)
	}

	/**
	 * Get whether the user will use a particular bouldering grade or not.
	 *
	 * @param grade Name of a grade.
	 *
	 * @return True if the user will use a particular bouldering grade, and
	 *         False otherwise.
	 */
	fun getWillGradeWithFlow(grade: String) : Flow<Boolean>
	{
		val key = buildWillGradeWithKey(grade)

		return getBooleanFlow(key)
	}

	/**
	 * Observe all the grading systems for a type of climbing that a user will
	 * use.
	 *
	 * @return Observe all the grading systems for a type of climbing that a
	 *         user will use.
	 */
	@Composable
	fun getAllGradingSystemsWillUse() : List<String>
	{
		val allGradingSystems = getAllGradingSystems()
		var allGradingSystemsWillUse = mutableListOf<String>()

		// Iterate over each grading system
		for (system in allGradingSystems)
		{
			// Check if the grading system will be used
			runBlocking {
				val willUse = getWillGradeWith(system)

				// The grading system will be used. Add it to the list
				if (willUse)
				{
					allGradingSystemsWillUse.add(system)
				}
			}
		}

		return allGradingSystemsWillUse
	}

	/**
	 * Observe all the grading systems for a type of climbing that a user will
	 * use.
	 *
	 * @return Observe all the grading systems for a type of climbing that a
	 *         user will use.
	 */
	@Composable
	fun observeAllGradingSystemsWillUse() : List<String>
	{
		val allGradingSystems = getAllGradingSystems()
		var allGradingSystemsWillUse = mutableListOf<String>()

		// Iterate over each grading system
		for (system in allGradingSystems)
		{
			// Check if the grading system will be used
			val willUse = observeWillGradeWith(system)

			// The grading system will be used. Add it to the list
			if (willUse)
			{
				allGradingSystemsWillUse.add(system)
			}
		}

		return allGradingSystemsWillUse
	}

	/**
	 * Observe the default grading system for a type of climbing.
	 *
	 * @return The observed default grading system for a type of climbing.
	 */
	@Composable
	fun observeDefaultGradingSystem() : String
	{
		val flow = getDefaultGradingSystemFlow()

		return observeString(flow)
	}

	/**
	 * Observe whether the climbing technique question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionClimbingTechnique() : Boolean
	{
		val flow = getQuestionClimbingTechniqueFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the hold question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionHold() : Boolean
	{
		val flow = getQuestionHoldFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the how did it feel question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionHowDidItFeel() : Boolean
	{
		val flow = getQuestionHowDidItFeelFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the is flash question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionIsFlash() : Boolean
	{
		val flow = getQuestionIsFlashFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the is outdoor question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionIsOutdoor() : Boolean
	{
		val flow = getQuestionIsOutdoorFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the is project question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionIsProject() : Boolean
	{
		val flow = getQuestionIsProjectFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the location question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionLocation() : Boolean
	{
		val flow = getQuestionLocationFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the media path question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionMediaPath() : Boolean
	{
		val flow = getQuestionMediaPathFlow()
		
		return observeBoolean(flow)
	}

	/**
	 * Observe whether the name question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionName() : Boolean
	{
		val flow = getQuestionNameFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the notes question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionNote() : Boolean
	{
		val flow = getQuestionNoteFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the number of attempts question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionNumAttempt() : Boolean
	{
		val flow = getQuestionNumAttemptFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the perceived grade question should be asked or not.
	 *
	 * @return Observed state hether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionPerceivedGrade() : Boolean
	{
		val flow = getQuestionPerceivedGradeFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the wall feature question should be asked or not.
	 *
	 * @return Observed state whether the question should be asked or not.
	 */
	@Composable
	fun observeQuestionWallFeature() : Boolean
	{
		val flow = getQuestionWallFeatureFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether or not the user will boulder.
	 *
	 * @return Observe state whether the user will climb a specific type or not.
	 */
	@Composable
	fun observeWillClimb() : Boolean
	{
		val flow = getWillClimbFlow()

		return observeBoolean(flow)
	}

	/**
	 * Observe whether the user will use a particular bouldering grade or not.
	 *
	 * @param grade Name of a grade.
	 *
	 * @return Observe state whether the user will use a particular grading
	 *         system or not.
	 */
	@Composable
	fun observeWillGradeWith(grade: String) : Boolean
	{
		val flow = getWillGradeWithFlow(grade)

		return observeBoolean(flow)
	}

}
