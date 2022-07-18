package me.gabeg.sicksends.addproblem.generic.question

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import me.gabeg.sicksends.addproblem.SsBody
import me.gabeg.sicksends.addproblem.SsQuestion
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel.Companion.getSubtitle
import me.gabeg.sicksends.db.generic.SsGenericProblem
import me.gabeg.sicksends.problem.type.SsHowDidItFeelType
import me.gabeg.sicksends.problem.ui.SsGradeIcon
import me.gabeg.sicksends.shared.getAllBoulderGradesForGradingSystem
import me.gabeg.sicksends.shared.getAllHowDidItFeelNames
import me.gabeg.sicksends.shared.getExampleGrade
import me.gabeg.sicksends.shared.howDidItFeelToName
import me.gabeg.sicksends.ui.SsButtonToggleGroup
import me.gabeg.sicksends.ui.SsDropdownMenuState
import me.gabeg.sicksends.ui.SsExposedDropdownMenu
import me.gabeg.sicksends.ui.rememberSsDropdownMenuState
import kotlin.math.round

/**
 * Grade question.
 */
@Composable
fun SsGradeQuestion(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState)
{
	// Index
	val index = viewModel.findIndex(viewModel.grade)

	// Question
	SsQuestion(
		viewModel = viewModel,
		icon = { modifier ->
			SsGradeIcon(modifier = modifier)
		},
		body = { visible, onDone ->
			SsGradeBody(
				viewModel = viewModel,
				scrollState = scrollState,
				visible = visible,
				onDone = onDone)
		},
		index = index,
		scrollState = scrollState)
}

/**
 * Body of the grade question.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SsGradeBody(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	scrollState : LazyListState,
	visible : Boolean = true,
	onDone : () -> Unit = {})
{

	// Title and question
	val title = viewModel.grade.title
	val question = viewModel.grade.question

	// Grade
	val grade = viewModel.problem.grade
	//val grade by viewModel.problem.observableGrade.observeAsState("")

	// Subtitle
	val subtitle = getSubtitle(grade, question, visible)

	// Body
	SsBody(title, subtitle)
	{
		AnimatedVisibility(visible = visible)
		{
			Column()
			{

				// Get all grades for a grading system
				val gradingSystem = viewModel.problem.gradingSystem
				val allGrades = getAllBoulderGradesForGradingSystem(gradingSystem)

				// Index of the saved grade
				val index = allGrades.indexOf(grade)

				// Save the state of the dropdown menu
				val menuState = rememberSsDropdownMenuState(index)

				menuState.select(index)

				// Dropdown menu with all the grades
				SsExposedDropdownMenu(
					options = allGrades,
					state = menuState,
					onMenuItemClickedListener = { index, name ->
						// Set the observable so that the Done button is shown
						viewModel.problem.observableGrade.value = name

						onDone()
					})

			}
		}
	}

}

/**
 * A page asking a user to select a perceived grade.
 */
@Composable
fun SsPerceivedGradePage(
	viewModel : SsAddGenericProblemViewModel<SsGenericProblem>,
	onGradeSelected : (grade : String) -> Unit)
{

	// Get all grades for a grading system
	val gradingSystem = viewModel.problem.gradingSystem
	val allGrades = getAllBoulderGradesForGradingSystem(gradingSystem)

	// Index of the saved grade
	val index = allGrades.indexOf(viewModel.problem.perceivedGrade)

	// Save the state of the dropdown menu
	val menuState = remember { SsDropdownMenuState(index = index) }

	// Dropdown menu with all the grades
	SsExposedDropdownMenu(
		options = allGrades,
		state = menuState,
		onMenuItemClickedListener = { index, name ->
			viewModel.problem.perceivedGrade = name

			onGradeSelected(name)
		})
}
