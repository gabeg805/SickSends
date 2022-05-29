package me.gabeg.sicksends.sport

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.problem.SsGenericProblemViewModel
import javax.inject.Inject

/**
 * Sport view model.
 */
@HiltViewModel
class SsSportViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	repo : SsSportRepository)
		: SsGenericProblemViewModel<SsSportProblem>(savedStateHandle, repo)
