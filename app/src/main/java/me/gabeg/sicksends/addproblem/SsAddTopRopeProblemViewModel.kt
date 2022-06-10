package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.toprope.SsTopRopeProblem
import javax.inject.Inject

/**
 * Add top rope problem view model.
 */
@HiltViewModel
class SsAddTopRopeProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTopRopeProblem)
	: SsAddProblemViewModel<SsTopRopeProblem>(savedStateHandle, problem)
