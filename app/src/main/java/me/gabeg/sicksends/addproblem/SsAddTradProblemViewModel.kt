package me.gabeg.sicksends.addproblem

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.gabeg.sicksends.db.trad.SsTradProblem
import javax.inject.Inject

/**
 * Add trad problem view model.
 */
@HiltViewModel
class SsAddTradProblemViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	problem : SsTradProblem)
	: SsAddProblemViewModel<SsTradProblem>(savedStateHandle, problem)
