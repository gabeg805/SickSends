package me.gabeg.sicksends.addproblem.trad

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.addproblem.generic.SsAddGenericProblemViewModel
import me.gabeg.sicksends.db.trad.SsTradProblem
import me.gabeg.sicksends.shared.SsSharedTradDataStore
import javax.inject.Inject

/**
 * Add trad problem view model.
 */
@HiltViewModel
class SsAddTradProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTradProblem,
	dataStore : SsSharedTradDataStore)
	: SsAddGenericProblemViewModel<SsTradProblem>(savedStateHandle, problem, dataStore)
