package me.gabeg.sicksends.db.trad

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.db.generic.SsGenericProblemViewModel
import javax.inject.Inject

/**
 * Trad view model.
 */
@HiltViewModel
class SsTradViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	repo : SsTradRepository)
		: SsGenericProblemViewModel<SsTradProblem>(savedStateHandle, repo)
