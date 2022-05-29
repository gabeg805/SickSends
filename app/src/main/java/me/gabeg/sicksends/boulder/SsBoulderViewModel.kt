package me.gabeg.sicksends.boulder

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.problem.SsGenericProblemViewModel
import javax.inject.Inject

/**
 * Boulder view model.
 */
@HiltViewModel
class SsBoulderViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	repo : SsBoulderRepository)
		: SsGenericProblemViewModel<SsBoulderProblem>(savedStateHandle, repo)
