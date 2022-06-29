package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.boulder.SsBoulderProblem
import me.gabeg.sicksends.shared.SsSharedBoulderDataStore
import javax.inject.Inject

/**
 * Add boulder problem view model.
 */
@HiltViewModel
class SsAddBoulderProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsBoulderProblem,
	dataStore : SsSharedBoulderDataStore)
	: SsAddGenericProblemViewModel<SsBoulderProblem>(savedStateHandle, problem, dataStore)
