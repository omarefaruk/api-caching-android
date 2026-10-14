package com.example.apicachingapplication.feature_reading.presentation.quran_contributors

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicachingapplication.core.Constants
import com.example.apicachingapplication.feature_reading.domain.model.Contributor
import com.example.apicachingapplication.feature_reading.domain.use_case.contributor_use_cases.GetContributorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    private val getContributorUseCase: GetContributorUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf<Contributor?>(null)
    val state: State<Contributor?> = _state
    private val contributorRole: String? = savedStateHandle.get<String>(Constants.PARAM_CONTRIBUTOR_ROLE)

    init {
        contributorRole?.let { getContributor(it) }
    }
    private fun getContributor(contributorRole: String) {
        viewModelScope.launch {
           _state.value = getContributorUseCase(contributorRole)
        }
    }
}

