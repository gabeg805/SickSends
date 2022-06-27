package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.db.toprope.SsTopRopeProblem
import me.gabeg.sicksends.shared.SsSharedTopRopeDataStore
import javax.inject.Inject

/**
 * Add top rope problem view model.
 */
@HiltViewModel
class SsAddTopRopeProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTopRopeProblem,
	dataStore : SsSharedTopRopeDataStore)
	: SsAddProblemViewModel<SsTopRopeProblem>(savedStateHandle, problem, dataStore)
