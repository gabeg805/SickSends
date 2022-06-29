package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.sport.SsSportProblem
import me.gabeg.sicksends.shared.SsSharedSportDataStore
import javax.inject.Inject

/**
 * Add sport problem view model.
 */
@HiltViewModel
class SsAddSportProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsSportProblem,
	dataStore : SsSharedSportDataStore)
	: SsAddGenericProblemViewModel<SsSportProblem>(savedStateHandle, problem, dataStore)
