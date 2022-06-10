package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.boulder.SsBoulderProblem
import javax.inject.Inject

/**
 * Add boulder problem view model.
 */
@HiltViewModel
class SsAddBoulderProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsBoulderProblem)
	: SsAddProblemViewModel<SsBoulderProblem>(savedStateHandle, problem)
