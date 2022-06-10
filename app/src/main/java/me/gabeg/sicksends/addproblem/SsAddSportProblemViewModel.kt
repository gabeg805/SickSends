package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.sport.SsSportProblem
import javax.inject.Inject

/**
 * Add sport problem view model.
 */
@HiltViewModel
class SsAddSportProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsSportProblem)
	: SsAddProblemViewModel<SsSportProblem>(savedStateHandle, problem)
