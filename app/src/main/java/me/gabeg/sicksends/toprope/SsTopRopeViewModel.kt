package me.gabeg.sicksends.toprope

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.problem.SsGenericProblemViewModel
import javax.inject.Inject

/**
 * Top rope view model.
 */
@HiltViewModel
class SsTopRopeViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	repo : SsTopRopeRepository)
		: SsGenericProblemViewModel<SsTopRopeProblem>(savedStateHandle, repo)
