package me.gabeg.sicksends.boulder

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.gabeg.sicksends.db.SsProblemDatabase
import me.gabeg.sicksends.main.WhereProblem
import me.gabeg.sicksends.problem.SsProblemScreen

@Composable
fun SsBoulderScreen(db : SsProblemDatabase, innerPadding : PaddingValues,
	lazyListState : LazyListState, whereProblem: WhereProblem)
{
	val boulderDao = db.boulderDao()
	val boulderRepo = SsBoulderRepository(boulderDao)
	val boulderViewModel = SsBoulderViewModel(boulderRepo)
	val problems :  List<SsBoulderProblem>

	if (whereProblem.isAll())
	{
		Log.i("ALAKSJHDLAjsD", "Finding all problems")
		val allProblems: List<SsBoulderProblem> by boulderViewModel.allProblems.observeAsState(listOf())
		problems = allProblems
	}
	else
	{
		Log.i("YOOOOOOOOOOO", "Finding where problems")
		val findProblems: List<SsBoulderProblem> by boulderViewModel
			.getProblemsWhere(
				whereProblem.isOutdoor,
				whereProblem.isProject,
				whereProblem.isFlash)
			.observeAsState(listOf())
		problems = findProblems
	}

	var expanded by remember { mutableStateOf(false) }
	var isAllChecked by remember { mutableStateOf(false) }
	var isFlashChecked by remember { mutableStateOf(false) }
	var isProjectChecked by remember { mutableStateOf(false) }
	var isSendChecked by remember { mutableStateOf(false) }
	var isIndoorChecked by remember { mutableStateOf(false) }
	var isOutdoorChecked by remember { mutableStateOf(false) }

	Column()
	{
		SsProblemScreen(problems, innerPadding = innerPadding, lazyListState)
		//SsProblemScreen(allProblems, innerPadding = innerPadding, lazyListState)
	}
}

//Row(
//	modifier = Modifier
//		.fillMaxWidth())
//{
//	TextButton(
//		onClick = {
//			Log.i("YOOOOOOO", "Filter button clicked!")
//			expanded = true
//		})
//	{
//		Icon(Icons.Default.FilterAlt, contentDescription = "Filter")
//		Text("Filter")
//	}

//	DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
//	{

//		DropdownMenuItem(
//			onClick = {
//				isAllChecked = !isAllChecked
//			})
//		{
//			Checkbox(
//				checked = isAllChecked,
//				onCheckedChange = { state ->
//					isAllChecked = state
//				})
//			Text("All")
//		}

//		Divider(
//			modifier = Modifier
//				.height(1.dp),
//			color = Color.Black)

//		DropdownMenuItem(
//			onClick = {
//				isFlashChecked = !isFlashChecked
//			})
//		{
//			Checkbox(
//				checked = isFlashChecked,
//				onCheckedChange = { state ->
//					isFlashChecked = state
//				})
//			Text("Flash")
//		}

//		DropdownMenuItem(
//			onClick = {
//				isProjectChecked = !isProjectChecked
//			})
//		{
//			Checkbox(
//				checked = isProjectChecked,
//				onCheckedChange = { state ->
//					isProjectChecked = state
//				})
//			Text("Project")
//		}

//		DropdownMenuItem(
//			onClick = {
//				isSendChecked = !isSendChecked
//			})
//		{
//			Checkbox(
//				checked = isSendChecked,
//				onCheckedChange = { state ->
//					isSendChecked = state
//				})
//			Text("Send")
//		}

//		DropdownMenuItem(
//			onClick = {
//				isIndoorChecked = !isIndoorChecked
//			})
//		{
//			Checkbox(
//				checked = isIndoorChecked,
//				onCheckedChange = { state ->
//					isIndoorChecked = state
//				})
//			Text("Indoor")
//		}

//		DropdownMenuItem(
//			onClick = {
//				isOutdoorChecked = !isOutdoorChecked
//			})
//		{
//			Checkbox(
//				checked = isOutdoorChecked,
//				onCheckedChange = { state ->
//					isOutdoorChecked = state
//				})
//			Text("Outdoor")
//		}

//	}
//}
